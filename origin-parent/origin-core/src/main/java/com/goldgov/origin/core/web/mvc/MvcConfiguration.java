package com.goldgov.origin.core.web.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.goldgov.origin.core.web.freemarker.FreeMarkerAttribute;
import com.goldgov.origin.core.web.freemarker.PuzzleFreeMarkerView;
import com.goldgov.origin.core.web.interceptor.WebInterceptor;
import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.AuditRecordHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.LocaleChangeHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.TokenCheckHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.ValidationHandler;
import com.goldgov.origin.core.web.messages.ClassPathMessageSource;
import com.goldgov.origin.core.web.mvc.ViewController.ViewMapping;

@Configuration
public class MvcConfiguration  extends WebMvcConfigurerAdapter implements BeanPostProcessor{//,ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	@Value("${server.welcome-page:}")
	private String welcomePage;
	
	@Value("${url.error:/error}")
	private String urlError;
	
	@Autowired(required=false)
	private List<FreeMarkerAttribute> freeMarkerAttributes;
	
	@Autowired(required=false)
	private ViewController viewController;
	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////		System.out.println(converters.size());
//	}
//
//	@Override
//	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
////		System.out.println(converters.size());
//	}
	
//	@Override
//	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
//		returnValueHandlers.add(new HandlerMethodReturnValueHandler() {
//			@Override
//			public boolean supportsReturnType(MethodParameter returnType) {
//				return returnType.getMethod().getReturnType() == User.class;
////				return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
////						returnType.hasMethodAnnotation(ResponseBody.class));
//			}
//			
//			@Override
//			public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
//					NativeWebRequest webRequest) throws Exception {
//				mavContainer.setRequestHandled(true);
//				HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
//				response.getWriter().write("is mine !");
//			}
//		});
//	}
	
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.favorPathExtension(true);
//		TransactionProxyFactoryBean a;
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		argumentResolvers.add(new ModelQueryResolver());
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<IRequestHandler> requestHadnler = new ArrayList<>();
		requestHadnler.add(new RequestHolderHandler());
		requestHadnler.add(new LocaleChangeHandler());
		requestHadnler.add(new AuditRecordHandler());
//		requestHadnler.add(new FreemarkModelHandler());
		requestHadnler.add(new TokenCheckHandler());
		requestHadnler.add(new ValidationHandler());
		
		WebInterceptor webInterceptor = new WebInterceptor();
		webInterceptor.setRequestHandlers(requestHadnler);
		registry.addInterceptor(webInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("logout");
		registry.addViewController(welcomePage).setViewName("main");
		registry.addViewController(urlError).setViewName("error");
		
		if(!"".equals(welcomePage)){
			registry.addViewController("/").setViewName(welcomePage);
		}
		
		if(viewController != null){
			List<ViewMapping> mappingViews = viewController.mappingView();
			if(mappingViews != null){
				for (ViewMapping viewMapping : mappingViews) {
					registry.addViewController(viewMapping.getMappingPath()).setViewName(viewMapping.getViewName());
				}
			}
		}
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		if(arg0 instanceof FreeMarkerViewResolver){
			FreeMarkerViewResolver viewResolver = (FreeMarkerViewResolver)arg0;
			viewResolver.setViewClass(PuzzleFreeMarkerView.class);
			
			
			if(freeMarkerAttributes != null){
				Map<String,Object> attributes = new HashMap<>();
				for (FreeMarkerAttribute freeMarkerAttribute : freeMarkerAttributes) {
					freeMarkerAttribute.attributesMap(attributes);
				}
				if(attributes.size() > 0){
					viewResolver.setAttributesMap(attributes);
				}
			}
			
		}
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		return arg0;
	}
	
	@Bean
	public MessageSource messageSource(){
		ClassPathMessageSource messageSource = new ClassPathMessageSource();
		messageSource.setBasename("/**/messages");
		return messageSource;
	}
	
	@Bean
	public CookieLocaleResolver localeResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("clientLanguage");
		localeResolver.setCookieMaxAge(100000);
		localeResolver.setCookiePath("/");
//		localeResolver.setDefaultLocale("zh_CN");
		return localeResolver;
	}
	
	@Bean
	public ExceptionHandler exceptionHandler(){
		return new ExceptionHandler();
	}

	
	
	
//	protected Map<String, MediaType> getDefaultMediaTypes() {
//		Map<String, MediaType> defaultMediaTypes = super.getDefaultMediaTypes();
//		defaultMediaTypes.put("json", "application/json");
//		return defaultMediaTypes;
//	}
	

	
	@SuppressWarnings("rawtypes")
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager contentNegotiationManager){
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setOrder(2);
		viewResolver.setContentNegotiationManager(contentNegotiationManager);
		
		List<View> defaultViews = new ArrayList<View>();
		MappingJackson2JsonView jackson2JsonView = new MappingJackson2JsonView();
		ObjectMapper objectMapper = jackson2JsonView.getObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		
		final TSerializer serializer = new TSerializer(new TSimpleJSONProtocol.Factory());
		SimpleModule module = new SimpleModule();  
		module.addSerializer(TBase.class, new JsonSerializer<TBase>(){
			public void serialize(TBase value, JsonGenerator jgen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
		        try {
					jgen.writeRawValue(serializer.toString(value));
				} catch (TException e) {
					//FIXME
					throw new RuntimeException(e);
				} 
				
			}});
		
		objectMapper.registerModule(module); 
		defaultViews.add(jackson2JsonView);
		viewResolver.setDefaultViews(defaultViews);
		return viewResolver;
	}

//@Override
//public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
//	TomcatEmbeddedServletContainer tomcatContainer = ((TomcatEmbeddedServletContainer)event.getEmbeddedServletContainer());
//	Tomcat tomcat = tomcatContainer.getTomcat();
//	tomcat.addUser("liuhg", "1111111");
//	tomcat.addRole("liuhg", "ACTUATOR");
//}

//	@Bean
//	public FreeMarkerConfigurer freeMarkerConfigurer(){
//		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//		freeMarkerConfigurer.setTemplateLoaderPath("classpath:com");
//		Properties settings = new Properties();
//		settings.put("defaultEncoding", "UTF-8");
//		settings.put("url_escaping_charset", "UTF-8");
//		freeMarkerConfigurer.setFreemarkerSettings(settings);
//		return freeMarkerConfigurer;
//	}
//	
//	@Bean
//	public FreeMarkerViewResolver freeMarkerViewResolver(){
//		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
//		freeMarkerViewResolver.setOrder(1);
//		freeMarkerViewResolver.setContentType("text/html; charset=utf-8");
//		freeMarkerViewResolver.setCache(true);
//		freeMarkerViewResolver.setViewClass(FreeMarkerView.class);
//		return freeMarkerViewResolver;
//	}
	
}
