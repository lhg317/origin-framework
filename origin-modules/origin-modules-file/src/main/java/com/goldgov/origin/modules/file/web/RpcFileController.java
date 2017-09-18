package com.goldgov.origin.modules.file.web;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goldgov.origin.modules.file.api.RpcFile;
import com.goldgov.origin.modules.file.api.RpcFileFragmentService;
import com.goldgov.origin.modules.file.api.UploadConfig;
import com.goldgov.origin.modules.file.api.UploadConfigService;
import com.goldgov.origin.modules.file.api.UploadHelper;
import com.goldgov.origin.modules.file.api.exception.NoPermissionException;

/**
 * 文件上传及下载基础控制器类，业务模块应该如果有上传和下载服务的功能应继承本类，
 * 而不是直接使用，这样便于做请求授权管理。
 * @author LiuHG
 * @version 1.0
 */
@Controller
@RequestMapping("/file")
public class RpcFileController {

	private static final UploadConfig DEFAULT_UPLOAD_CONFIG = new UploadConfig();

	@Autowired(required=false)
	private UploadConfigService uploadConfigService;
	
	@Autowired
	@Qualifier("rpcFileFragmentService.Client")
	private RpcFileFragmentService.Iface fileService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@RequestMapping("/uploadFile")
	public @ResponseBody String uploadFile(HttpServletRequest request) throws Exception{
		UploadConfig uploadConfig = getUploadConfig(request);
		
		if(!allowUpload(uploadConfig,request)){
			throw new NoPermissionException();
		}
		
		List<String> resultFileIDs = uploadHelper.saveFile(request, uploadConfig);
		
//		return resultFileIDs.toArray(new String[0]);
		return resultFileIDs.get(0);
	}

//	private List<String> saveFile(HttpServletRequest request, UploadConfig uploadConfig)
//			throws IOException, TException {
//		List<String> resultFileIDs = new ArrayList<>();
//		if(ServletFileUpload.isMultipartContent(request)){
//			 MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
//			 Iterator<String> iter = multiRequest.getFileNames();
//			 int fileNum = multiRequest.getFileMap().size();
//			 while(iter.hasNext()) {
//	            
//	                MultipartFile file = multiRequest.getFile(iter.next().toString());
//	                if(file!=null && file.getSize() > 0)
//	                {
//	                	if(isLimited(uploadConfig,file,fileNum)){
//	            			throw new UploadLimitException(uploadConfig.toString());
//	            		}
//	                	
//	                	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//	            		int copyCount = FileCopyUtils.copy(file.getInputStream(), byteArrayOutputStream);
//	            		ByteBuffer byteBuffer = ByteBuffer.allocate(copyCount);
//	            		byteBuffer.put(byteArrayOutputStream.toByteArray());
//	            		byteBuffer.flip();
//	            		
//	            		RpcFile rpcFile = new RpcFile();
//	            		rpcFile.setCreateDate(System.currentTimeMillis());
//	            		rpcFile.setFileSize(file.getSize());
//	            		rpcFile.setFileName(file.getOriginalFilename());
//	            		rpcFile.setFileType(file.getContentType());
//	            		String fileID = fileService.addFile(rpcFile,byteBuffer);
//	            		resultFileIDs.add(fileID);
//	            		
////	            		文件分片上传示例，此处可用FileSplitUtils工具类
////	            		byteBuffer = ByteBuffer.allocate(1);
////	            		byteBuffer.put((byte)1);
////	            		byteBuffer.flip();
////	            		fileID = fileService.createFileFragment(rpcFile, 5, byteBuffer);
////	            		for (int i = 1; i < 5; i++) {
////	            			byteBuffer = ByteBuffer.allocate(1);
////		            		byteBuffer.put((byte)(1+i));
////		            		byteBuffer.flip();
////		            		fileService.addFileFragment(fileID, i, byteBuffer);
////						}
////	            		fileService.completeFileFragment(fileID,5);
//	                }
//			 }
//        }
//		return resultFileIDs;
//	}
	
	protected boolean isLimited(UploadConfig uploadConfig, MultipartFile file,int fileNum) {
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
	public void downloadFile(@RequestParam("fileID") String fileID,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(!allowDownload(request)){
			throw new NoPermissionException();
		}
		
		RpcFile file = fileService.getFile(fileID);
		if(file == null){
			throw new FileNotFoundException(fileID);
		}
		String fileName = file.getFileName(); 
		
		response.setContentType(file.getFileType());
		response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(),"ISO-8859-1") +"\"");
		
		ServletOutputStream outputStream = response.getOutputStream();
		uploadHelper.writeFile(fileID, outputStream);
		
//		ByteBuffer fileContent = fileService.getFileContent(fileID);
//		byte[] fileFragmentBytes = fileContent.array(); 
//		ServletOutputStream outputStream = response.getOutputStream();
//		outputStream.write(fileFragmentBytes);
//		fileContent.clear();
		
	}
	
	@RequestMapping("/downloadFileFragment")
	public void downloadFileFragment(@RequestParam("fileID") String fileID,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(!allowDownload(request)){
			throw new NoPermissionException();
		}
		
		RpcFile file = fileService.getFile(fileID);
		if(file == null){
			throw new FileNotFoundException(fileID);
		}
		
		
		response.setContentType(file.getFileType());
		response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getFileName() +"\"");
		
		ServletOutputStream outputStream = response.getOutputStream();
		uploadHelper.writeFileFragment(fileID, outputStream);
//		long currentSize = 0;
//		ByteBuffer byteBuffer = fileService.getFileFragmentContent(fileID,0);
//		while (byteBuffer != null) {
//			byte[] fileFragmentBytes = byteBuffer.array(); 
//			outputStream.write(fileFragmentBytes);
//			currentSize += fileFragmentBytes.length;
//			byteBuffer = fileService.getFileFragmentContent(fileID,currentSize);
//		}
		
	}
	
	protected UploadConfig getUploadConfig(HttpServletRequest request){
		if(uploadConfigService == null){
			return DEFAULT_UPLOAD_CONFIG;
		}
		return uploadConfigService.getUploadConfig(request);
	}
	
	protected boolean allowUpload(UploadConfig uploadConfig, HttpServletRequest request){
		return true;
	}
	
	protected boolean allowDownload(HttpServletRequest request){
		return true;
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
	
	@RequestMapping("/downloadImage")
	public void downloadImage(@RequestParam("fileID") String fileID,@RequestParam(value="width",required=false,defaultValue="0") Integer width,@RequestParam(value="height",required=false,defaultValue="0") Integer height,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(!allowDownload(request)){
			throw new NoPermissionException();
		}
		
		RpcFile file = fileService.getFile(fileID);
		if(file == null){
			throw new FileNotFoundException(fileID);
		}
//		String fileName = file.getFileName(); 
		
		response.setContentType(file.getFileType());
//		response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(),"ISO-8859-1") +"\"");
		
		ServletOutputStream outputStream = response.getOutputStream();
		uploadHelper.writeImageFile(fileID, outputStream, width, height);
		
		
	}
}
