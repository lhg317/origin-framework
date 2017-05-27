package com.goldgov.origin.core.utils;


import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * 时限文件，当文件到达指定的期限后自动删除。如果文件正在被引用，则再下一次的时候尝试删除。
 * @author LiuHG
 * @version 1.0
 */
public final class TimedFile {

	private static final int PERIOD = 1000;
	
	private static TimedFile timedFile = new TimedFile();
	
	private Vector<FileEntity> fileVector = new Vector<FileEntity>();
	
	private Timer timer;
	
	private RefreshStateTask refreshStateTask;
	
	private TimedFile(){}
	
	public static TimedFile getInstance(){
		return timedFile;
	}
	
	public File createFile(String pathname,long timedSeconds){
		FileEntity fileEntity = new FileEntity(pathname,timedSeconds);
		processTimed(fileEntity); 
		return fileEntity;
	}

	public File createFile(String parent, String child,long timedSeconds){
		FileEntity fileEntity = new FileEntity(parent,child,timedSeconds);
		processTimed(fileEntity);
		return fileEntity;
	}
	
	private void processTimed(FileEntity fileEntity) {
		fileVector.add(fileEntity);
		if(refreshStateTask == null || refreshStateTask.isCancel()){
			timer = new Timer("Timed File Thread",true);
			refreshStateTask = new RefreshStateTask();
			timer.schedule(refreshStateTask, 0, PERIOD);
		}
	}
	
	class RefreshStateTask extends TimerTask{
		
		private boolean cancel = true;
		
		@Override
		public void run() {
			cancel = false;
			for (int i = 0; i < fileVector.size(); i++) {
				FileEntity file = fileVector.get(i);
				Date expiredDate = file.getExpiredDate();
				if(expiredDate.before(new Date())){
					if(!file.exists() || file.delete()){
						fileVector.remove(i);
					}
				}
			}
			if(fileVector.size() == 0){
				cancel = super.cancel();
				timer.cancel();
				timer = null;
			}
		}
		
		boolean isCancel(){
			return cancel;
		}
	}
	
	public class FileEntity extends File{

		private static final long serialVersionUID = 834909444145610083L;

		private Date expiredDate;
		
		FileEntity(String parent, String child,long timedSeconds) {
			super(parent, child);
			calculateExpiredDate(timedSeconds);
		}

		FileEntity(String pathname,long timedSeconds) {
			super(pathname);
			calculateExpiredDate(timedSeconds);
		}

		FileEntity(URI uri,long timedSeconds) {
			super(uri);
			calculateExpiredDate(timedSeconds);
		}

		Date getExpiredDate() {
			return expiredDate;
		}
		
		private void calculateExpiredDate(long timedSeconds) {
			long timeMillis = System.currentTimeMillis() + timedSeconds *1000;
			expiredDate = new Date(timeMillis);
		}
		
	}
	
}
