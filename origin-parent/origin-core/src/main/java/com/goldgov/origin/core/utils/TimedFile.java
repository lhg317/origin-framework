package com.goldgov.origin.core.utils;


import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * 时限文件，当文件到达指定的期限后自动删除。如果文件正在被引用，则在下一次检查过期文件的时候尝试删除。每秒检查一次<p>
 * 从此工具类删除的文件，可能在外面还会存留文件的空引用对象，因此在使用前需要使用exists()检查一下文件是否存在。
 * 此工具类请谨慎使用，因为如果定时不当，可能在需要使用前就会把文件删除掉了。
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
	
	/**
	 * 创建时限文件，通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例。
	 * 如果给定字符串是空字符串，那么结果是空抽象路径名。 
	 * @param pathname 路径名字符串 
	 * @param timedSeconds 文件被删除的延迟时间，单位：秒
	 * @return 时限文件，是{@link java.io.File File}的子类，因此有{@link java.io.File File}的所有特性。
	 */
	public File createFile(String pathname,long timedSeconds){
		FileEntity fileEntity = new FileEntity(pathname,timedSeconds);
		processTimed(fileEntity); 
		return fileEntity;
	}

	/**
	 * 创建时限文件，根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例。 <p>
	 * 如果 parent 为 null，则创建一个新的 File 实例，这与调用以给定 child 路径名字符串作为参数的单参数 File 构造方法效果一样。 <p>
	 * 否则，parent 路径名字符串用于表示目录，child 路径名字符串用于表示目录或文件。如果 child 路径名字符串是绝对路径名，
	 * 则用与系统有关的方式将它转换为一个相对路径名。如果 parent 是空字符串，则通过将 child 转换为抽象路径名，
	 * 并根据与系统有关的默认目录解析结果来创建新的 File 实例。否则，将每个路径名字符串转换为一个抽象路径名，
	 * 并根据父抽象路径名解析子抽象路径名。 
	 * 
	 * @param parent 父路径名字符串
	 * @param child 子路径名字符串
	 * @param timedSeconds 文件被删除的延迟时间，单位：秒
	 * @return 时限文件，是{@link java.io.File File}的子类，因此有{@link java.io.File File}的所有特性。
	 */
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
	
	class FileEntity extends File{

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
