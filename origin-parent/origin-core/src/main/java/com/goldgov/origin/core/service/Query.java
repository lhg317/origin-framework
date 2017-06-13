package com.goldgov.origin.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 查询对象，用于存放分页信息及查询结果。一般分页查询的条件对象都需要继承本类，否则框架无法自动进行分页操作。
 * @author LiuHG
 * @version 1.0
 * @param <T>
 */
public class Query<T>  {

	public static final String QUERY_PREFIX = "query";
	
	private List<T> resultList = new ArrayList<T>();
	
	public final static int DEFAULT_PAGE_SIZE = 15;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int currentPage;
	private long count;
	private int maxPage;
	private int minPage = 1;
	private int firstResult = 0;
	
	public Query(){}
	
	public Query(Collection<? extends T> list){
		resultList.addAll(list);
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public void setFirstResult(int firstResult){
		this.firstResult = firstResult;
	}
	public int getFirstResult(){
		return firstResult;
	}
	
	public int getMinPage(){
		return minPage;
	}
	public void setMinPage(int minPage){
		this.minPage = minPage;
	}
	
//	public void next(){
//		currentPage++;
//		if(currentPage > maxPage) {
//		    currentPage = maxPage;
//		}
//	}
//	
//	public void previous(){
//		currentPage--;
//		if(currentPage < minPage) {
//		    currentPage = minPage;
//		}
//	}
	
	/**
	 * 分页信息计算，会将计算后的分页信息设置到本对象中相关属性值中。
	 * @param count 数据总数
	 */
	public void calculate(long count){
		setCount(count);
//		if(pageSize <= 0 )pageSize = PAGE_SIZE;
		int pageSize = getPageSize();
		int minPage = getMinPage();
		maxPage = (int)Math.max((count + pageSize - 1) / pageSize,1);
		setMaxPage(maxPage);
		int currentPage = getCurrentPage();
		if(currentPage > maxPage) {
		    currentPage = maxPage;
		}else if(currentPage < minPage) {
			currentPage = minPage;
		}
		
		setFirstResult((currentPage - 1)*pageSize);
	}
	
	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
