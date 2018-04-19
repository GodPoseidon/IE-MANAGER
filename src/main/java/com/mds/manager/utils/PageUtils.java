package com.mds.manager.utils;

import java.util.List;

import org.springframework.util.StringUtils;

public class PageUtils {

	private int countNum; // 总条数

	private int page = 1; // 当前页，默认1

	private int countPage; // 总页数

	private int limit = 10; // 每页显示多少条，默认10

	private List<?> list; // 每页显示的数据集合
	
	private Object queryParames;	//查询参数
	
	private String order;			//排序参数
	
	public Object getQueryParames() {
		return queryParames;
	}

	public void setQueryParames(Object queryParames) {
		this.queryParames = queryParames;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public PageUtils() {}
	
	public PageUtils(String page) {
		if (!StringUtils.isEmpty(page)) {
			this.page = Integer.parseInt(page);
		}
	}

	public PageUtils(List<?> list) {
		this.list = list;
		setCountNum(list.size());
	}
	
	public PageUtils(List<?> list, String page) {
		this.list = list;
		if (!StringUtils.isEmpty(page)) {
			this.page = Integer.parseInt(page);
		}
		setCountNum(list.size());
	}

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
		if (countNum%limit<=0) {
			setCountPage(countNum / limit);
		}else {
			setCountPage(countNum / limit +1);
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
		setCountNum(list.size());
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	
}
