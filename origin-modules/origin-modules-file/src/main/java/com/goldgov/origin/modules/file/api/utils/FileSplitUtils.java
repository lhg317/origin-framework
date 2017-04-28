package com.goldgov.origin.modules.file.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 为了方便使用文件分片传输，对文件进行分片的工具类
 * @author LiuHG
 * @version 1.0.0
 */
public class FileSplitUtils {
	
	private static int BUFFER_SIZE = 4096;
	
	/**
	 * 将文件根据指定的字节数进行分片，分片结束后不会关闭输入流，当最终文件分片结束后，需要手动关闭。
	 * @param is
	 * @param bytes
	 * @return
	 */
	public static byte[] fileSplit(InputStream is,int bytes){
		int count = -1;
		byte[] buffer = new byte[bytes > BUFFER_SIZE ? BUFFER_SIZE : bytes];
		ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes);
		int total = 0;
		try {
			while((count = is.read(buffer)) != -1){
				bos.write(buffer, 0, count);
				total += count;
				if(total == bytes){
					return bos.toByteArray();
				}
				if(bytes - total < BUFFER_SIZE){
					buffer = new byte[bytes - total];
				}
			}
			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("文件分片时发生IO错误");
		}finally{
			try {
				bos.close();//其实对于ByteArrayOutputStream不需要关闭，因为它只是个数组缓存
			} catch (IOException e) {}
		}
	}
	
	public static long fileSplitNum(long total,int bytes){
		return (total/bytes) + (total%bytes == 0 ? 0:1);
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		FileInputStream fileInputStream = new FileInputStream(new File("D:\\tomcat-webservice所需JARS.rar"));
//		int count = 0;
//		long bytes = 0;
//		byte[] fileSplit = null;
//		while(fileSplit == null || fileSplit.length == 1048576){
//			fileSplit = fileSplit(fileInputStream,1048576);
//			bytes += fileSplit.length;
//			System.out.println((fileSplit.length == 1048576) + "\t" + (++count));
//		}
//		System.out.println("total = " + bytes);
//		
//		System.out.println(fileSplitNum(20575219,1048576));
//		try {
//			fileInputStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
