package com.goldgov.origin.modules.file.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.file.api.RpcFile;
import com.goldgov.origin.modules.file.api.RpcFileFragmentService;
import com.goldgov.origin.modules.file.service.File;
import com.goldgov.origin.modules.file.service.FileFragmentService;
import com.goldgov.origin.modules.file.service.ImgCompress;
import com.goldgov.origin.modules.file.service.ProxyFile;

@RpcService("RpcFileService")
public class RpcFileServiceImpl implements RpcFileFragmentService.Iface{

	@Autowired
	private FileFragmentService fileService;
	
	private static final int ALL = 6;
	private static final int WIDTH = 2;
	private static final int HEIGHT = 4;

	@Override
	public String addFile(RpcFile file, ByteBuffer bytes) throws TException {
		fileService.addFile(new ProxyFile(file), new ByteArrayInputStream(bytes.array()));
		return file.getFileID();
	}

	@Override
	public void deleteFile(List<String> ids) throws TException {
		fileService.deleteFile(ids.toArray(new String[0]));
	}

	@Override
	public RpcFile getFile(String fileID) throws TException {
		File file = fileService.getFile(fileID);
		return new ProxyFile(file).toRpcFile();
	}

	@Override
	public ByteBuffer getFileContent(String fileID) throws TException {
		InputStream fileContent = fileService.getFileContent(fileID);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int copyCount = 0;
		try {
			copyCount = FileCopyUtils.copy(fileContent, byteArrayOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteBuffer byteBuffer = ByteBuffer.allocate(copyCount);
		byteBuffer.put(byteArrayOutputStream.toByteArray());
		byteBuffer.flip();
		return byteBuffer;
	}

	@Override
	public List<RpcFile> listFile(String groupID) throws TException {
		List<File> fileFile = fileService.listFile(groupID);
		List<RpcFile> resultList = new ArrayList<>();
		for (File file : fileFile) {
			resultList.add(new ProxyFile(file).toRpcFile());
		}
		return resultList;
	}

	@Override
	public String createEmptyFileFragment(RpcFile file, int fragmentTotal) throws TException {
		fileService.createFileFragment(new ProxyFile(file), fragmentTotal);
		return file.getFileID();
	}

	@Override
	public String createFileFragment(RpcFile file, int fragmentTotal, ByteBuffer bytes) throws TException {
		fileService.createFileFragment(new ProxyFile(file), fragmentTotal, bytes.array());
		return file.getFileID();
	}

	@Override
	public void addFileFragment(String fileID, int fragment, ByteBuffer bytes) throws TException {
		fileService.addFileFragment(fileID, fragment, bytes.array());
	}

	@Override
	public void completeFileFragment(String fileID,int fragmentTotal) throws TException {
		fileService.completeFileFragment(fileID,fragmentTotal);
	}

	@Override
	public ByteBuffer getFileFragmentContent(String fileID, long startIndex) throws TException {
		byte[] fragmentContent = fileService.getFileFragmentContent(fileID, startIndex);
		if(fragmentContent == null){
			return null;
		}
		ByteBuffer byteBuffer = ByteBuffer.allocate(fragmentContent.length);
		byteBuffer.put(fragmentContent);
		byteBuffer.flip();
		return byteBuffer;
	}
	
	@Override
	public ByteBuffer getImage(String fileID,int width,int height) throws TException {
		int hasWidthHeight = hasWidthHeight(width, height);
		if(hasWidthHeight == 0){
			return this.getFileContent(fileID);
		}
		String file_px = fileID;
		if(hasWidthHeight == WIDTH){
			file_px += "_w" + width;
		}else if(hasWidthHeight == HEIGHT){
			file_px += "_h" + height;
		}else if(hasWidthHeight == ALL){
			file_px += "_w" + width + "_h" + height;
		}
		ByteBuffer byteBuffer = null;
		try{
			byteBuffer = this.getFileContent(file_px);
		}catch(Exception e){
			//do nothing
		}
		if(byteBuffer == null){
			InputStream fileContent = fileService.getFileContent(fileID);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int copyCount = 0;
			
			ImgCompress imgCompress = new ImgCompress(fileContent, byteArrayOutputStream);
			try {
				if(hasWidthHeight == ALL){
					imgCompress.resize(width, height);
				}else if(hasWidthHeight == HEIGHT){
					imgCompress.resizeByHeight(height);
				}else if(hasWidthHeight == WIDTH){
					imgCompress.resizeByWidth(width);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			copyCount = byteArrayOutputStream.size();
			
			byte[] bytes = byteArrayOutputStream.toByteArray();
			byteBuffer = ByteBuffer.allocate(copyCount);
			byteBuffer.put(bytes);
			byteBuffer.flip();
			
			if(file_px != null){
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
				fileService.addFileToStorage(file_px, inputStream);
			}
		}
		return byteBuffer;
	}
	
	private static int hasWidthHeight(int width,int height){
		int result = 0;
		if(width > 0){
			result = WIDTH | result;
		}
		if(height > 0){
			result = HEIGHT | result;
		}
		return result;
	}
	
}
