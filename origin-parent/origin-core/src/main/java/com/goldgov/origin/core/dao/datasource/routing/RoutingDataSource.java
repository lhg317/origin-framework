package com.goldgov.origin.core.dao.datasource.routing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.goldgov.origin.core.dao.DatabaseContextHolder;

public class RoutingDataSource extends AbstractRoutingDataSource{
	
	public RoutingDataSource(List<DataSource> dataSources){
		Map<Object, Object> targetDataSources = new HashMap<>();
		for (int i = 0; i < dataSources.size(); i++) {
			targetDataSources.put(DatabaseContextHolder.DATA_SOURCE_PREFIX + i, dataSources.get(i));
		}
		super.setTargetDataSources(targetDataSources);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.dataSourceName.get();
	}

}
