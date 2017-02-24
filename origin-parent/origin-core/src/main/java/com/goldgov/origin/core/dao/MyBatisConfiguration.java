package com.goldgov.origin.core.dao;

import java.beans.Introspector;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import com.goldgov.origin.core.dao.mybatis.PagingInterceptor;

@Configurable
//@Import(C3p0DataSourceConfig.class)
public class MyBatisConfiguration {

	@Bean("mapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer scannerConfig = new MapperScannerConfigurer();
		//FIXME basePackage
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		scannerConfig.setBasePackage(bundle.getString("mybatis.base-package"));
		scannerConfig.setNameGenerator(new MyBatisMapperNameGenerator());
		scannerConfig.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		scannerConfig.setAnnotationClass(Mapper.class);
		return scannerConfig;
	}
	
	@Bean("sqlSessionFactoryBean")
	public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource,VendorDatabaseIdProvider provider){
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDatabaseIdProvider(provider);
		sessionFactory.setDataSource(dataSource);
		
		sessionFactory.setPlugins(new Interceptor[]{new PagingInterceptor()});
		
//		/*此时SqlSessionFactoryBean尚未进行afterPropertiesSet()方法，因此无法进行setResultMap的设置*/
//		try {
//			Configuration configuration = sessionFactory.getObject().getConfiguration();
//			List<ResultMapping> resultMappingList = new ArrayList<>();
//			ResultMap resultMap = new ResultMap.Builder(configuration, "user", User.class, resultMappingList, true)
//			        .discriminator(null)
//			        .build();
//			configuration.getResultMaps();
//			configuration.addResultMap(resultMap);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return sessionFactory;
	}
	
	@Bean("vendorDatabaseIdProvider")
	public VendorDatabaseIdProvider vendorDatabaseIdProvider(){
		VendorDatabaseIdProvider provider = new VendorDatabaseIdProvider();
		Properties p = new Properties();
		p.put("SQL Server", "sqlserver");
		p.put("DB2", "db2");
		p.put("Oracle", "oracle");
		p.put("MySQL", "mysql");
		p.put("H2", "h2");
		provider.setProperties(p);
		return provider;
	}
	
	public static final class MyBatisMapperNameGenerator implements BeanNameGenerator {

		@Override
		public String generateBeanName(BeanDefinition arg0,
				BeanDefinitionRegistry arg1) {
			String shortClassName = ClassUtils.getShortName(arg0.getBeanClassName());
			Class<?> mappingClass = null;
			try {
				mappingClass = Class.forName(arg0.getBeanClassName());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("class not found",e);
			}
			Mapper annos = AnnotationUtils.findAnnotation(mappingClass, Mapper.class);
			if("".equals(annos.value())){
				return Introspector.decapitalize(shortClassName);
			}
			
	        return annos.value();
		}
	}
}
