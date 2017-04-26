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
import com.goldgov.origin.modules.file.api.RpcFileService;
import com.goldgov.origin.modules.file.service.File;
import com.goldgov.origin.modules.file.service.FileService;

@RpcService
public class RpcFileServiceImpl implements RpcFileService.Iface{

	@Autowired
	private FileService fileService;

	@Override
	public void addFile(String serviceName, RpcFile file, ByteBuffer bytes) throws TException {
		fileService.addFile(serviceName, new ProxyFile(file), new ByteArrayInputStream(bytes.array()));
	}

	@Override
	public void deleteFile(String serviceName, List<String> ids) throws TException {
		fileService.deleteFile(serviceName, ids.toArray(new String[0]));
	}

	@Override
	public RpcFile getFile(String serviceName, String fileID) throws TException {
		File file = fileService.getFile(serviceName, fileID);
		return new ProxyFile(file).toRpcFile();
	}

	@Override
	public ByteBuffer getFileContent(String serviceName, String fileID) throws TException {
		InputStream fileContent = fileService.getFileContent(serviceName, fileID);
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
		return byteBuffer;
	}

	@Override
	public List<RpcFile> listFile(String serviceName, String relationID) throws TException {
		List<File> fileFile = fileService.listFile(serviceName, relationID);
		List<RpcFile> resultList = new ArrayList<>();
		for (File file : fileFile) {
			resultList.add(new ProxyFile(file).toRpcFile());
		}
		return null;
	}
	

}
