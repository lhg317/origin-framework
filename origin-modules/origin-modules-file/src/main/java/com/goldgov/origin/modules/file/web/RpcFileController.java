package com.goldgov.origin.modules.file.web;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.origin.modules.file.api.RpcFile;
import com.goldgov.origin.modules.file.api.RpcFileService;

@Controller
@RequestMapping("/file")
public class RpcFileController {

	@Autowired
	@Qualifier("rpcFileService.Client")
	private RpcFileService.Iface fileService;
	
	@RequestMapping("/addFile")
	public String addFile(RpcFile file) throws TException{
		file.setFileID("lhg");
		InputStream fileContent = null;
		try {
			fileContent = new FileInputStream("D:\\lucene.html");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		fileService.addFile("default",file,byteBuffer);
		return "forward:/user/listUser";
	}
}
