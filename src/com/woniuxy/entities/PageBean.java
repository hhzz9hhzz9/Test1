package com.woniuxy.entities;

import java.util.List;

public class PageBean<T> {
	private int totalRows;// ������
	private int pageSize;// ÿҳ��ʾ����
	private int currentPage;// ��ǰҳ��
	private int pages;// ��ҳ��
	private List<T> data;// ����

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
	 * @return ÿҳ��ʾ����
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param ÿҳ��ʾ���� to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return ��ǰҳ��
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param ��ǰҳ�� to set
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
	 * @return ��ҳ��
	 */
	public int getPages() {
		this.pages = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
		return pages;
	}

}
