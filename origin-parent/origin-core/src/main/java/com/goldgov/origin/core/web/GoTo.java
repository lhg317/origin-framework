package com.goldgov.origin.core.web;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;
import com.goldgov.origin.core.web.token.ITokenGenerator;
import com.goldgov.origin.core.web.token.UuidTokenGenerator;
import com.goldgov.origin.core.web.token.WebToken;

/**
 * 路径跳转辅助类，用于<code>Controller</code>做路径跳转用
 * @author LiuHG
 * @version 1.0
 */
public class GoTo {
	
	private Class<? extends Object> controllerClass;
	private String modelBasePath;

	public static final String FORWARD_PREFIX = "forward:";
	public static final String REDIRECT_PREFIX = "redirect:";
	
	private ITokenGenerator tokenGenerator;
	
	/**
	 * 构造一个无基础路径的对象
	 */
	public GoTo(){}
	
	/**
	 * 通过一个在类头标注了@RequestMapping的注解类的实例构造
	 * @param controller
	 */
	public GoTo(Object controller){
		Assert.notNull(controller);
		controllerClass = controller.getClass();
		
		RequestMapping annotation = controllerClass.getAnnotation(RequestMapping.class);
		if(annotation != null){
			//FIXME
			String[] paths = annotation.value();
			this.modelBasePath = clearEndSeparator(paths[0]);
		}else{
			throw new RuntimeException(controllerClass + "必须是一个在类头标注了@RequestMapping的注解类");
		}
	}
	
	/**
	 * 通过一个在类头标注了@RequestMapping的注解类的Class构造
	 * @param controllerClass
	 */
	public GoTo(Class<?> controllerClass){
		Assert.notNull(controllerClass);
		this.controllerClass = controllerClass;
		
		RequestMapping annotation = controllerClass.getAnnotation(RequestMapping.class);
		if(annotation != null){
			//FIXME
			String[] paths = annotation.value();
			this.modelBasePath = clearEndSeparator(paths[0]);
		}else{
			throw new RuntimeException(controllerClass + "必须是一个在类头标注了@RequestMapping的注解类");
		}
	}

	/**
	 * 通过一个给定的基础路径构造
	 * @param basePath 基础路径，全路径以“/”开头
	 */
	public GoTo(String basePath){
		Assert.notNull(basePath);
		this.modelBasePath = clearEndSeparator(basePath);
	}
	
	/**
	 * 以redirect方式跳转到其他路径
	 * @param path 预跳转的路径，如果构造本对象时使用了非空构造器，则根据指定的基础路径拼接<code>path</code>参数作为完整跳转路径。
	 * @return 跳转路径
	 */
	public String sendRedirect(String path){
		return REDIRECT_PREFIX + formatPath(path);
	}
	
	/**
	 * 以forward方式跳转到其他路径
	 * @param path 预跳转的路径，如果构造本对象时使用了非空构造器，则根据指定的基础路径拼接<code>path</code>参数作为完整跳转路径。
	 * @return 跳转路径
	 */
	public String sendForward(String path){
		return FORWARD_PREFIX + formatPath(path);
	}
	
	/**
	 * 以页面方式跳转路径，即路径不带任何forward或redirect前缀。
	 * @param path 预跳转的路径。如果构造本对象时使用了非空构造器，则根据指定的基础路径拼接<code>path</code>参数作为完整跳转路径，
	 * 但当以<code>Object</code>和<code>Class</code>为构造器参数时，实际是获取<code>PAGE_BASE_PATH</code>字段值，如果没有声明该字段，则以模块访问路径为页面的基础路径。<br>
	 * 对于<code>PAGE_BASE_PATH</code>字段的修饰符必须包含“public static”，例如：
	 * <p><blockquote><pre>
	 * public static final String PAGE_BASE_PATH = "moduleName/web/pages/";
	 * </pre></blockquote><p>
	 * @return 页面跳转路径
	 */
	public String sendPage(String path){
		if(controllerClass != null){
			try {
				Field pageBasePathField = controllerClass.getDeclaredField("PAGE_BASE_PATH");
				try {
					String pageBasePathValue = (String) pageBasePathField.get(null);
					return endWithSeparator(pageBasePathValue) + clearStartSeparator(path);
				} catch (Exception e) {
					throw new RuntimeException("无法尝试从PAGE_BASE_PATH字段中获取值。请确认该值为一个String类型对象，且必须“public static”并且不为null",e);
				}
			} catch (SecurityException e) {
				throw new RuntimeException("无法尝试从" + controllerClass + "中获取字段对象",e);
			} catch (NoSuchFieldException e) {
				return path;
			}
		}else{
			return path;
		}
	}
	
	/**
	 * 
	 * @param path
	 * @param e
	 * @return
	 */
	public String sendPage(String path,Throwable e){
		HttpServletRequest request = RequestHolder.getRequest();
		request.setAttribute(Keys.ORIGIN_EXCEPTION, e);
		processToken(request);
		
		return sendPage(path);
	}
	
	public String sendPage(String path,String errorMsg){
		HttpServletRequest request = RequestHolder.getRequest();
		request.setAttribute(Keys.ORIGIN_EXCEPTION, new RuntimeException(errorMsg));
		processToken(request);
		
		return sendPage(path);
	}

	private void processToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object tokenName = session.getAttribute(WebToken.DEFAULT_TOKEN_NAME);
		if(tokenName != null){
			String tokenValue = request.getParameter((String)tokenName);
			if(tokenValue != null){
				session.setAttribute((String)tokenName, getTokenGenerator().generate());
			}
		}
	}
	
	private String formatPath(String path){
		path = startWithSeparator(path);
		if(modelBasePath != null){
			return modelBasePath + path;
		}
		return path;
	}
	
	public String clearEndSeparator(String path){
		return path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
	}
	
	public String clearStartSeparator(String path){
		return path.startsWith("/") ? path.substring(1) : path;
	}
	
	public String startWithSeparator(String path){
		return path.startsWith("/") ? path : "/" + path;
	}
	
	public String endWithSeparator(String path){
		return path.endsWith("/") ? path : path + "/";
	}

	public ITokenGenerator getTokenGenerator() {
		if(tokenGenerator == null){
			tokenGenerator = new UuidTokenGenerator();
		}
		return tokenGenerator;
	}

	public void setTokenGenerator(ITokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}
	
}
