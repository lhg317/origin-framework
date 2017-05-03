package com.goldgov.origin.modules.file.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goldgov.origin.modules.file.api.RpcFile;
import com.goldgov.origin.modules.file.api.RpcFileFragmentService;
import com.goldgov.origin.modules.file.exception.UploadLimitException;
import com.goldgov.origin.modules.file.service.UploadConfig;
import com.goldgov.origin.modules.file.service.UploadConfigService;

@Controller
@RequestMapping("/file")
public class RpcFileController {

	private static final UploadConfig DEFAULT_UPLOAD_CONFIG = new UploadConfig();

	@Autowired(required=false)
	private UploadConfigService uploadConfigService;
	
	
	@Autowired
	@Qualifier("rpcFileFragmentService.Client")
	private RpcFileFragmentService.Iface fileService;
	
	@RequestMapping("/uploadFile")
	public @ResponseBody String[] uploadFile(HttpServletRequest request) throws TException{
		UploadConfig uploadConfig = getUploadConfig(request);
		
		List<String> resultFileIDs = new ArrayList<>();
		if(ServletFileUpload.isMultipartContent(request)){
			 MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			 Iterator<String> iter = multiRequest.getFileNames();
			 int fileNum = multiRequest.getFileMap().size();
			 while(iter.hasNext()) {
	            
	                MultipartFile file = multiRequest.getFile(iter.next().toString());
	                if(file!=null)
	                {
	                	if(isLimited(uploadConfig,file,fileNum)){
	            			throw new UploadLimitException(uploadConfig.toString());
	            		}
	                	
	                	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            		int copyCount = 0;
	            		try {
	            			copyCount = FileCopyUtils.copy(file.getInputStream(), byteArrayOutputStream);
	            		} catch (IOException e) {
	            			// TODO Auto-generated catch block
	            			e.printStackTrace();
	            		}
	            		ByteBuffer byteBuffer = ByteBuffer.allocate(copyCount);
	            		byteBuffer.put(byteArrayOutputStream.toByteArray());
	            		byteBuffer.flip();
	            		
	            		RpcFile rpcFile = new RpcFile();
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
		
		return resultFileIDs.toArray(new String[0]);
	}
	
	private boolean isLimited(UploadConfig uploadConfig, MultipartFile file,int fileNum) {
		if(file.getSize() > uploadConfig.getSizeMax()){//附件大小超出限制
			return true;
		}
		if(!isContain(FilenameUtils.getExtension(file.getName()), uploadConfig.getFileExtension(),true) && 
				!isContain("*", uploadConfig.getFileExtension(),true)){//配置中不包含的附件扩展名
			return true;
		}
		if(uploadConfig.getUploadMax() > 0 && fileNum > uploadConfig.getUploadMax()){//配置中不包含的附件扩展名
			return true;
		}
		return false;
	}

	@RequestMapping("/downloadFile")
	public @ResponseBody String downloadFile(@RequestParam("fileID") String fileID,HttpServletResponse response) throws TException{
		RpcFile file = fileService.getFile(fileID);
		if(file == null){
			//TODO 文件不存在
		}
		ByteBuffer fileContent = fileService.getFileContent(fileID);
		response.setContentType(file.getFileType());
		response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getFileName() +"\"");
		
		byte[] fileFragmentBytes = fileContent.array(); 
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(fileFragmentBytes);
			fileContent.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(fileContent.array());
	}
	
	@RequestMapping("/downloadFileFragment")
	public void downloadFileFragment(@RequestParam("fileID") String fileID,HttpServletResponse response) throws TException{
		RpcFile file = fileService.getFile(fileID);
		if(file == null){
			//TODO 文件不存在
		}
		ByteBuffer byteBuffer = fileService.getFileFragmentContent(fileID,0);
		long currentSize = 0;
		response.setContentType(file.getFileType());
		response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getFileName() +"\"");
		
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			while (byteBuffer != null) {
				byte[] fileFragmentBytes = byteBuffer.array(); 
				outputStream.write(fileFragmentBytes);
				currentSize += fileFragmentBytes.length;
				byteBuffer = fileService.getFileFragmentContent(fileID,currentSize);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private UploadConfig getUploadConfig(HttpServletRequest request){
		if(uploadConfigService == null){
			return DEFAULT_UPLOAD_CONFIG;
		}
		return uploadConfigService.getUploadConfig(request);
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
