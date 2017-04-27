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

@RpcService
public class RpcFileServiceImpl implements RpcFileFragmentService.Iface{

	@Autowired
	private FileFragmentService fileService;

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
	public List<RpcFile> listFile(String relationID) throws TException {
		List<File> fileFile = fileService.listFile(relationID);
		List<RpcFile> resultList = new ArrayList<>();
		for (File file : fileFile) {
			resultList.add(new ProxyFile(file).toRpcFile());
		}
		return null;
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
	

}
