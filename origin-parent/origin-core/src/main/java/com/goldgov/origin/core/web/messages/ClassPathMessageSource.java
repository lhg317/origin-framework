package com.goldgov.origin.core.web.messages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

/**
 * 根据配置的表达式来检索指定位置下所有资源文件（*.properties），用于系统国际化使用。<br>
 * 被扫描到的properties文件必须带有语言后缀，如：message_zh_CN.properties
 * @author LiuHG
 * @version 1.0
 */
public class ClassPathMessageSource extends ReloadableResourceBundleMessageSource implements InitializingBean{

	private String RESOURCE_PATTERN = "/*.properties";
	
	private String[] basenames;

	private Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void setBasename(String basename) {
		this.basenames = new String[]{basename};
	}

	@Override
	public void setBasenames(String... basenames) {
		this.basenames = basenames;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		super.setResourceLoader(new PathMatchingResourcePatternResolver());设置此类无效，因为在父类调用的方法中未使用真正解析classpath的那个方法
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		
		List<String> messageFiles = new ArrayList<String>();
		for (String pkg : basenames) {
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
			+ ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
			Resource[] resources = null;
			try {
				resources = resourcePatternResolver.getResources(pattern);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			for (Resource resource : resources) {
				String path = null;
				if(ResourceUtils.isFileURL(resource.getURL())){
					path = resource.getFile().getAbsolutePath();
					//FIXME
					String resourceFilePath = new File(this.getClass().getResource("/").getFile()).getPath();
					path = StringUtils.replace(path, resourceFilePath, "");
					path = StringUtils.replace(path, "\\", "/");
					
					int index = path.indexOf("/classes");
					if(index != -1){
						path = path.substring(index + "/classes".length(),path.length());
					}
					if(logger.isInfoEnabled()){
						logger.info("path:" + resourceFilePath + "\t\r\nresourceFilePath:" + path);
					}
				}else if(ResourceUtils.isJarURL(resource.getURL())){
					URL url = resource.getURL();
					String[] pathFragments = url.getPath().split("[!]");
					if(pathFragments.length < 2){
						throw new RuntimeException("无法识别文件在jar中的位置：" + url);
					}
					path = pathFragments[pathFragments.length - 1];
				}else{
					throw new RuntimeException("无法识别文件位置：" + resource.getURL());
				}
				
				
				Locale[] availableLocales = Locale.getAvailableLocales();
				boolean localeHit = false;
				for (Locale locale : availableLocales) {
					String localeStr = locale.toString();
					String suffix = "_" + localeStr + ".properties";
					if(path.endsWith(suffix)){
						path = new String(path.toCharArray(),0,path.length() - suffix.length());
						path = ResourceUtils.CLASSPATH_URL_PREFIX + path;
						if(!messageFiles.contains(path)){
							messageFiles.add(path);
						}
						localeHit = true;
						break;
					}
				}
				
				if(!localeHit){
					throw new RuntimeException("资源文件语言识别失败：" + resource.getFile().getPath());
				}
			}
		}
		if(messageFiles.size() > 0){
			super.setBasenames(messageFiles.toArray(new String[0]));
		}
		
	}
	
//	public static void main(String[] args) {
//		String resourceFilePath = "C:\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv01\\properties";
//		String path = "C:\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv01\\installedApps\\test-ljzl-ans01Cell01\\dygl_war.ear\\dygl.war\\WEB-INF\\classes\\com\\goldgov\\gtiles\\core\\messages\\GTilesMessage_en_US.properties";
//		path = StringUtils.replace(path, resourceFilePath, "");
//		path = StringUtils.replace(path, "\\", "/");
//		
//		int index = path.indexOf("/WEB-INF/classes");
//		if(index != -1){
//			path = path.substring(index + "/WEB-INF/classes".length(),path.length());
//		}
//		System.out.println(path);
//	}

//	public static void main(String[] args) {
//		Locale[] availableLocales = Locale.getAvailableLocales();
//		int i = 0;
//		for (Locale locale : availableLocales) {
//			System.out.println((++i) + "\t" + locale + "\t" + locale.getDisplayLanguage());
//		}
//		
//	}
}
