package org.crm.model;

import java.util.UUID;

public class Item {
	private String sid =null;
	private String iname =null;
	private String imessages =null;
	private double iprice =0;
	private String img=null;
	
	public Item(){
		this.sid=UUID.randomUUID().toString().replace("-", "");
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getImessages() {
		return imessages;
	}
	public void setImessages(String imessages) {
		this.imessages = imessages;
	}
	public double getIprice() {
		return iprice;
	}
	public void setIprice(double iprice) {
		this.iprice = iprice;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Item [sid=" + sid + ", iname=" + iname + ", imessages=" + imessages + ", iprice=" + iprice + ", img="
				+ img + "]";
	}
	
	
}
