package com.woniuxy.entities;

import java.util.Date;

import com.woniuxy.annotations.ColumnNameAnn;
import com.woniuxy.annotations.ObjectAnno;

public class Shopping {
	@ColumnNameAnn("shoppingId")
	private int shId;
	@ColumnNameAnn("cusId")
	private int cusId;
	@ColumnNameAnn("goodsId")
	private int gid;
	@ColumnNameAnn("shoppingCount")
	private int shCount;
	@ColumnNameAnn("addTimes")
	private Date addTime;
	@ColumnNameAnn("modifyTimes")
	private Date modifyTime;
	@ObjectAnno({"goodsName","goodsPrice","goodsCount","goodsImg"})
	private Goods goods;

	/**
	 * 
	 */
	public Shopping() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param shId
	 * @param cusId
	 * @param gid
	 * @param shCount
	 * @param addTime
	 * @param modifyTime
	 */
	public Shopping(int shId, int cusId, int gid, int shCount, Date addTime, Date modifyTime) {
		super();
		this.shId = shId;
		this.cusId = cusId;
		this.gid = gid;
		this.shCount = shCount;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
	}
	/**
	 * @param shId
	 * @param cusId
	 * @param gid
	 * @param shCount
	 * @param addTime
	 * @param modifyTime
	 * @param goods
	 */
	public Shopping(int shId, int cusId, int gid, int shCount, Date addTime, Date modifyTime, Goods goods) {
		super();
		this.shId = shId;
		this.cusId = cusId;
		this.gid = gid;
		this.shCount = shCount;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
		this.goods = goods;
	}
	/**
	 * @return the shId
	 */
	public int getShId() {
		return shId;
	}
	/**
	 * @param shId the shId to set
	 */
	public void setShId(int shId) {
		this.shId = shId;
	}
	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}
	/**
	 * @param cusId the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	/**
	 * @return the gid
	 */
	public int getGid() {
		return gid;
	}
	/**
	 * @param gid the gid to set
	 */
	public void setGid(int gid) {
		this.gid = gid;
	}
	/**
	 * @return the shCount
	 */
	public int getShCount() {
		return shCount;
	}
	/**
	 * @param shCount the shCount to set
	 */
	public void setShCount(int shCount) {
		this.shCount = shCount;
	}
	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * @return the goods
	 */
	public Goods getGoods() {
		return goods;
	}
	/**
	 * @param goods the goods to set
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "Shopping [shId=" + shId + ", cusId=" + cusId + ", gid=" + gid + ", shCount=" + shCount + ", addTime="
				+ addTime + ", modifyTime=" + modifyTime + ", goods=" + goods + "]";
	}
	
	 
	
}
