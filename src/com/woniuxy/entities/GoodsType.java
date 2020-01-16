package com.woniuxy.entities;

import com.woniuxy.annotations.ColumnNameAnn;

public class GoodsType {
	@ColumnNameAnn("typeId")
	private int gtid;
	@ColumnNameAnn("typeCode")
	private String gtno;
	@ColumnNameAnn("typeName")
	private String gtname;

	/**
	 * 
	 */
	public GoodsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param gtid
	 * @param gtno
	 * @param gtname
	 */
	public GoodsType(int gtid, String gtno, String gtname) {
		super();
		this.gtid = gtid;
		this.gtno = gtno;
		this.gtname = gtname;
	}
	/**
	 * @return the gtid
	 */
	public int getGtid() {
		return gtid;
	}
	/**
	 * @param gtid the gtid to set
	 */
	public void setGtid(int gtid) {
		this.gtid = gtid;
	}
	/**
	 * @return the gtno
	 */
	public String getGtno() {
		return gtno;
	}
	/**
	 * @param gtno the gtno to set
	 */
	public void setGtno(String gtno) {
		this.gtno = gtno;
	}
	/**
	 * @return the gtname
	 */
	public String getGtname() {
		return gtname;
	}
	/**
	 * @param gtname the gtname to set
	 */
	public void setGtname(String gtname) {
		this.gtname = gtname;
	}
	@Override
	public String toString() {
		return "GoodsType [gtid=" + gtid + ", gtno=" + gtno + ", gtname=" + gtname + "]";
	}
	
	

}
