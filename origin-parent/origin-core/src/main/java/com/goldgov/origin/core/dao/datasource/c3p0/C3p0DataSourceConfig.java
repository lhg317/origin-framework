package com.goldgov.origin.core.dao.datasource.c3p0;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@ConfigurationProperties(prefix = C3p0DataSourceConfig.PREFIX)
//@EnableTransactionManagement /*启用了@EnableAutoConfiguration不需要单独启用@EnableTransactionManagement*/
public class C3p0DataSourceConfig extends DataSourceConfig {

	public static final String PREFIX = "datasource.c3p0";

	private int maxPoolSize;
	private int minPoolSize;
	private int initialPoolSize;
	private int maxIdleTime;

	@Bean("dataSource")
	public DataSource dataSource() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(getDriverClassName());
		} catch (PropertyVetoException e) {
			throw e;
		}
		dataSource.setJdbcUrl(getUrl());
		dataSource.setUser(getUsername());
		dataSource.setPassword(getPassword());
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setInitialPoolSize(initialPoolSize);
		dataSource.setMaxIdleTime(maxIdleTime);
		
//		DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(getUrl());
		return dataSource;

		// return DataSourceBuilder.create()
		// .type(ComboPooledDataSource.class)
		// .url(getUrl())
		// .driverClassName(getDriverClassName())
		// .username(getUsername())
		// .password(getPassword())
		// .build();
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}
}
