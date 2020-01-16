package com.woniuxy.entities;

import java.util.List;

public class PageBean<T> {
	private int totalRows;// 总行数
	private int pageSize;// 每页显示条数
	private int currentPage;// 当前页码
	private int pages;// 总页数
	private List<T> data;// 数据

	/**
	 * 
	 */
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param totalRows
	 * @param pageSize
	 * @param currentPage
	 * @param pages
	 * @param data
	 */
	public PageBean(int totalRows, int pageSize, int currentPage, int pages, List<T> data) {
		super();
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.pages = pages;
		this.data = data;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return 每页显示条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param 每页显示条数 to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return 当前页码
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param 当前页码 to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * @return 总页数
	 */
	public int getPages() {
		this.pages = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
		return pages;
	}

}
