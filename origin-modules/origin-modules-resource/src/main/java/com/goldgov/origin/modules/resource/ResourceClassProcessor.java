package com.goldgov.origin.modules.resource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.origin.core.cache.Cache;
import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.modules.resource.service.ResourceOperate;

@Component
public class ResourceClassProcessor implements InitializingBean{

	private String RESOURCE_PATTERN = "/**/*.class";
	
	private String[] packagesToScan;
	
	@Autowired
	private Cache cache;
	
	@Value("${scan-base-package}")
	private String defaultBasePackage;
	
	public ResourceClassProcessor(){
	}
	
	public ResourceClassProcessor(String[] packagesToScan){
		this.packagesToScan = packagesToScan;
	}

	public void afterPropertiesSet() throws Exception {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		CacheHolder.init(cache);
		//FIXME
		if(packagesToScan == null){
			packagesToScan = new String[]{defaultBasePackage};
		}
		
		for (String pkg : packagesToScan) {
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
			Resource[] resources = null;
			try {
				resources = resourcePatternResolver.getResources(pattern);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
			for (Resource resource : resources) {
				MetadataReader reader = null;
				try {
					reader = readerFactory.getMetadataReader(resource);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				if (resource.isReadable()) {
					ClassMetadata classMetadata = reader.getClassMetadata();
					if(classMetadata.isAbstract() || classMetadata.isInterface()){
						continue;
					}
					processProtectedResource(classMetadata);
				}
			}
		}
	}
	
	public void processProtectedResource(ClassMetadata classMetadata){
		Class<? extends Object> clazz;
		try {
			clazz = Class.forName(classMetadata.getClassName());
		} catch (ClassNotFoundException e) {
			//TODO i18n
			throw new RuntimeException("类未找到："+classMetadata.getClassName(), e);
		}
		
		ModuleResource resourceAnno = clazz.getAnnotation(ModuleResource.class);
		Controller controller = clazz.getAnnotation(Controller.class);
		RequestMapping requestMappingAnno = clazz.getAnnotation(RequestMapping.class);
		if(resourceAnno != null && controller != null){
			com.goldgov.origin.modules.resource.service.Resource resource = new com.goldgov.origin.modules.resource.service.Resource();
			resource.setResourceName(resourceAnno.name());
			if("".equals(resourceAnno.code())){
				resource.setResourceCode(clazz.getName());
			}else{
				resource.setResourceCode(resourceAnno.code());
			}
			
			if(requestMappingAnno != null){
				//FIXME 
				resource.setResourceCode(requestMappingAnno.value()[0]);
			}
			
			Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(clazz);
			List<ResourceOperate> resourceOperateList = new ArrayList<ResourceOperate>();
			for (Method method : allDeclaredMethods) {
				RequestMapping actionRequestMapping = method.getAnnotation(RequestMapping.class);
				ModuleOperating moduleOperating = method.getAnnotation(ModuleOperating.class);
				if(actionRequestMapping != null && moduleOperating != null){
					ResourceOperate resourceOperate = new ResourceOperate();
					resourceOperate.setOperateName(moduleOperating.name());
					if("".equals(moduleOperating.code())){
						resourceOperate.setOperateCode(method.getName());
					}else{
						resourceOperate.setOperateCode(moduleOperating.code());
					}
					resourceOperate.setOperateType(moduleOperating.type());
					resourceOperateList.add(resourceOperate);
				}
			}
			
			resource.setResourceOperateList(resourceOperateList.toArray(new ResourceOperate[0]));
			ResourceContext.addResource(resource);
		}
	}

}
