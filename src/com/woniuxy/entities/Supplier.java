package com.woniuxy.entities;

import com.woniuxy.annotations.ColumnNameAnn;

public class Supplier {
	@ColumnNameAnn("supplierId")
	private int sid;
	@ColumnNameAnn("supplierCode")
	private String sno;
	@ColumnNameAnn("supplierName")
	private String sname;
	@ColumnNameAnn("supplierTel")
	private String tel;

	/**
	 * 
	 */
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param sid
	 * @param sno
	 * @param sname
	 * @param tel
	 */
	public Supplier(int sid, String sno, String sname, String tel) {
		super();
		this.sid = sid;
		this.sno = sno;
		this.sname = sname;
		this.tel = tel;
	}
	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
	/**
	 * @return the sno
	 */
	public String getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}
	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}
	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Supplier [sid=" + sid + ", sno=" + sno + ", sname=" + sname + ", tel=" + tel + "]";
	}
	
}
