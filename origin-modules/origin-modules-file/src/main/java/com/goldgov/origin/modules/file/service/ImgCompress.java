package com.goldgov.origin.modules.file.service;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImgCompress {

	private BufferedImage image;
	private OutputStream out;
	private int width;
	private int height;
	
	public ImgCompress(InputStream input,OutputStream output){
		try {
			image = ImageIO.read(input);
			out = output;
			height = image.getHeight();
			width = image.getWidth();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void resizeByWidth(int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(w, h);  
    }
    
    public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h);  
    }
    
    public void close() throws IOException{
    	out.close();
    }
	
	public void resize(int w,int h) throws IOException{
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(this.image, 0, 0, w, h, null);
		ImageIO.write(image, "jpg", out);
	}
	
	public static void main(String[] args) throws Exception {
		InputStream input = new FileInputStream("C:\\Users\\limonk\\Pictures\\238870.jpg");
		FileOutputStream output = new FileOutputStream("D:/press.jpg");
		ImgCompress imgCompress = new ImgCompress(input,output);
		imgCompress.resizeByHeight(300);
	}
	
}
