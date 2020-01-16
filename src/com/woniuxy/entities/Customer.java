package com.woniuxy.entities;

import com.woniuxy.annotations.ColumnNameAnn;

public class Customer {
	@ColumnNameAnn("cusId")
	private int cid;
	@ColumnNameAnn("cusName")
	private String cname;
	@ColumnNameAnn("cusPwd")
	private String cpwd;
	@ColumnNameAnn("cusTel")
	private String ctel;
	@ColumnNameAnn("cusAddress")
	private String caddr;
	@ColumnNameAnn("cusStatus")
	private String cstatus;

	/**
	 * 
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param cid
	 * @param cname
	 * @param cpwd
	 * @param ctel
	 * @param caddr
	 * @param cstatus
	 */
	public Customer(int cid, String cname, String cpwd, String ctel, String caddr, String cstatus) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cpwd = cpwd;
		this.ctel = ctel;
		this.caddr = caddr;
		this.cstatus = cstatus;
	}
	
	/**
	 * @param cid
	 * @param cname
	 * @param ctel
	 * @param caddr
	 */
	public Customer(int cid, String cname, String ctel, String caddr) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.ctel = ctel;
		this.caddr = caddr;
	}
	
	/**
	 * @param cname
	 * @param cpwd
	 */
	public Customer(String cname, String cpwd) {
		super();
		this.cname = cname;
		this.cpwd = cpwd;
	}
	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * @return the cpwd
	 */
	public String getCpwd() {
		return cpwd;
	}
	/**
	 * @param cpwd the cpwd to set
	 */
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	/**
	 * @return the ctel
	 */
	public String getCtel() {
		return ctel;
	}
	/**
	 * @param ctel the ctel to set
	 */
	public void setCtel(String ctel) {
		this.ctel = ctel;
	}
	/**
	 * @return the caddr
	 */
	public String getCaddr() {
		return caddr;
	}
	/**
	 * @param caddr the caddr to set
	 */
	public void setCaddr(String caddr) {
		this.caddr = caddr;
	}
	/**
	 * @return the cstatus
	 */
	public String getCstatus() {
		return cstatus;
	}
	/**
	 * @param cstatus the cstatus to set
	 */
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", cpwd=" + cpwd + ", ctel=" + ctel + ", caddr=" + caddr
				+ ", cstatus=" + cstatus + "]";
	}
	
	
}
