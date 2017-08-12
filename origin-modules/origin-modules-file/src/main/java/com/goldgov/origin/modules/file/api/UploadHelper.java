package com.goldgov.origin.modules.file.api;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goldgov.origin.modules.file.api.exception.UploadLimitException;

@Component
public class UploadHelper {

	@Autowired
	@Qualifier("rpcFileFragmentService.Client")
	private RpcFileFragmentService.Iface fileService;
	
	/**
	 * 保存文件
	 * @param request
	 * @param uploadConfig
	 * @return 返回上传成功的附件ID
	 * @throws Exception
	 */
	public List<String> saveFile(HttpServletRequest request, UploadConfig uploadConfig)
			throws Exception {
		
		List<String> resultFileIDs = new ArrayList<>();
		
		String groupID = request.getParameter("groupID");
		if(groupID == null){
			groupID = UUID.randomUUID().toString();
		}
		resultFileIDs.add(groupID);
		
		if(ServletFileUpload.isMultipartContent(request)){
			
			 MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			 Iterator<String> iter = multiRequest.getFileNames();
			 int fileNum = multiRequest.getFileMap().size();
			 while(iter.hasNext()) {
	            
	                MultipartFile file = multiRequest.getFile(iter.next().toString());
	                if(file!=null && file.getSize() > 0)
	                {
	                	if(isLimited(uploadConfig,file,fileNum)){
	            			throw new UploadLimitException(uploadConfig.toString());
	            		}
	                	
	                	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            		int copyCount = FileCopyUtils.copy(file.getInputStream(), byteArrayOutputStream);
	            		ByteBuffer byteBuffer = ByteBuffer.allocate(copyCount);
	            		byteBuffer.put(byteArrayOutputStream.toByteArray());
	            		byteBuffer.flip();
	            		
	            		RpcFile rpcFile = new RpcFile();
	            		rpcFile.setGroupID(groupID);
	            		rpcFile.setCreateDate(System.currentTimeMillis());
	            		rpcFile.setFileSize(file.getSize());
	            		rpcFile.setFileName(file.getOriginalFilename());
	            		rpcFile.setFileType(file.getContentType());
	            		String fileID = fileService.addFile(rpcFile,byteBuffer);
	            		resultFileIDs.add(fileID);
	            		
//	            		文件分片上传示例，此处可用FileSplitUtils工具类
//	            		byteBuffer = ByteBuffer.allocate(1);
//	            		byteBuffer.put((byte)1);
//	            		byteBuffer.flip();
//	            		fileID = fileService.createFileFragment(rpcFile, 5, byteBuffer);
//	            		for (int i = 1; i < 5; i++) {
//	            			byteBuffer = ByteBuffer.allocate(1);
//		            		byteBuffer.put((byte)(1+i));
//		            		byteBuffer.flip();
//		            		fileService.addFileFragment(fileID, i, byteBuffer);
//						}
//	            		fileService.completeFileFragment(fileID,5);
	                }
			 }
        }
		return resultFileIDs;
	}
	
	/**
	 * 一次性输出文件
	 * 不会主动关闭输出流
	 * @param fileID
	 * @param outputStream
	 * @throws Exception
	 */
	public void writeFile(String fileID,OutputStream outputStream) throws Exception{
		ByteBuffer fileContent = fileService.getFileContent(fileID);
		byte[] fileFragmentBytes = fileContent.array(); 
		outputStream.write(fileFragmentBytes);
		fileContent.clear();
	}
	
	/**
	 * 分片输出文件
	 * 不会主动关闭输出流
	 * @param fileID
	 * @param outputStream
	 * @throws Exception
	 */
	public void writeFileFragment(String fileID,OutputStream outputStream) throws Exception{
		ByteBuffer byteBuffer = fileService.getFileFragmentContent(fileID,0);
		long currentSize = 0;
		while (byteBuffer != null) {
			byte[] fileFragmentBytes = byteBuffer.array(); 
			outputStream.write(fileFragmentBytes);
			currentSize += fileFragmentBytes.length;
			byteBuffer = fileService.getFileFragmentContent(fileID,currentSize);
		}
	}
	
	protected boolean isLimited(UploadConfig uploadConfig, MultipartFile file,int fileNum) {
		if(file.getSize() > uploadConfig.getSizeMax()){//附件大小超出限制
			return true;
		}
		if(!isContain(FilenameUtils.getExtension(file.getOriginalFilename()), uploadConfig.getFileExtension(),true) && 
				!isContain("*", uploadConfig.getFileExtension(),true)){//配置中不包含的附件扩展名
			return true;
		}
		if(uploadConfig.getUploadMax() > 0 && fileNum > uploadConfig.getUploadMax()){//配置中不包含的附件扩展名
			return true;
		}
		return false;
	}
	
	/**
	 * 检查指定字符串在数组中是否存在
	 * @param str
	 * @param strArray
	 * @param ignoreCase 比较时，是否忽略大小写
	 * @return 返回true表示存在，返回false表示不存在。如果str或strArray为null均返回false
	 */
	private boolean isContain(String str, String[] strArray,boolean ignoreCase){
		if(str == null || strArray == null || strArray.length == 0){
			return false;
		}
		for (String s : strArray) {
			if(!ignoreCase){
				if(s.equals(str)){
					return true;
				}
			}else{
				if(s.equalsIgnoreCase(str)){
					return true;
				}
			}
		}
		return false;
	}
}
