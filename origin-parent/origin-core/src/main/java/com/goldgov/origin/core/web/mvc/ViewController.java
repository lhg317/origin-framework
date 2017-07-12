package com.goldgov.origin.core.web.mvc;

import java.util.List;

/**
 * 视图控制器，负责定义路径与视图的映射关系
 * @author LiuHG
 * @version 1.0
 */
public interface ViewController {
	
	List<ViewMapping> mappingView();

	public class ViewMapping{
		private String mappingPath;
		private String viewName;
		
		public ViewMapping(String mappingPath, String viewName) {
			this.mappingPath = mappingPath;
			this.viewName = viewName;
		}
		
		public String getMappingPath() {
			return mappingPath;
		}
		public String getViewName() {
			return viewName;
		}
	}
}
