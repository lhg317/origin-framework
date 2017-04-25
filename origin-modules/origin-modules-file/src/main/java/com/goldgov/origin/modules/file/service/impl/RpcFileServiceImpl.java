package com.goldgov.origin.modules.file.service.impl;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.thrift.TException;

import com.goldgov.origin.modules.file.api.RpcFile;
import com.goldgov.origin.modules.file.api.RpcFileService;

public class RpcFileServiceImpl implements RpcFileService.Iface{

	@Override
	public void addFile(RpcFile file, ByteBuffer bytes) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(List<String> ids) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RpcFile getFile(String fileID) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer getFileContent(String fileID) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpcFile> listFile(String relationID) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
