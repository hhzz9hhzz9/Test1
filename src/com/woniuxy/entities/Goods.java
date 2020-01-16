package com.woniuxy.entities;

import com.woniuxy.annotations.ColumnNameAnn;
import com.woniuxy.annotations.ObjectAnno;

public class Goods {
	@ColumnNameAnn("goodsId")
	private int gid;
	@ColumnNameAnn("typeId")
	private int gtid;
	@ColumnNameAnn("supplierId")
	private int sid;

	@ColumnNameAnn("goodsCode")
	private String gno;
	@ColumnNameAnn("goodsName")
	private String gname;
	@ColumnNameAnn("goodsCount")
	private int gcount;
	@ColumnNameAnn("goodsPrice")
	private float price;
	@ColumnNameAnn("goodsImg")
	private String img;
	@ObjectAnno({"typeId","typeName"})
	private GoodsType goodsType;
	@ObjectAnno({"supplierId","supplierName"})
	private Supplier supplier;

	/**
	 * 
	 */
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param gid
	 * @param gtid
	 * @param sid
	 * @param gno
	 * @param gname
	 * @param gcount
	 * @param price
	 * @param img
	 * @param goodsType
	 * @param supplier
	 */
	public Goods(int gid, int gtid, int sid, String gno, String gname, int gcount, float price, String img,
			GoodsType goodsType, Supplier supplier) {
		super();
		this.gid = gid;
		this.gtid = gtid;
		this.sid = sid;
		this.gno = gno;
		this.gname = gname;
		this.gcount = gcount;
		this.price = price;
		this.img = img;
		this.goodsType = goodsType;
		this.supplier = supplier;
	}
	
	

	/**
	 * @param gid
	 * @param sid
	 * @param gno
	 * @param gname
	 * @param price
	 * @param img
	 */
	public Goods(int gid,int gtid, int sid, String gno, String gname, float price, String img) {
		super();
		this.gid = gid;
		this.gtid = gtid;
		this.sid = sid;
		this.gno = gno;
		this.gname = gname;
		this.price = price;
		this.img = img;
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
	 * @return the goodsType
	 */
	public GoodsType getGoodsType() {
		return goodsType;
	}
	/**
	 * @param goodsType the goodsType to set
	 */
	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	/**
	 * @return the gno
	 */
	public String getGno() {
		return gno;
	}
	/**
	 * @param gno the gno to set
	 */
	public void setGno(String gno) {
		this.gno = gno;
	}
	/**
	 * @return the gname
	 */
	public String getGname() {
		return gname;
	}
	/**
	 * @param gname the gname to set
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}
	/**
	 * @return the gcount
	 */
	public int getGcount() {
		return gcount;
	}
	/**
	 * @param gcount the gcount to set
	 */
	public void setGcount(int gcount) {
		this.gcount = gcount;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", goodsType=" + goodsType + ", supplier=" + supplier + ", gno=" + gno + ", gname="
				+ gname + ", gcount=" + gcount + ", price=" + price + ", img=" + img + "]";
	}
	
	
	
	
	
}
