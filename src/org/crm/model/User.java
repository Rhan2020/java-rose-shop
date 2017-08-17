package org.crm.model;

import java.util.UUID;

public class User {
	private String uid =null;
	private String uname =null;
	private String psd =null;
	private String order=null;
	private String cart_item=null;
	private String address=null;
	
	public User(){
		this.uid=UUID.randomUUID().toString().replace("-", "");
	}
	
	public User(String uname, String psd) {
		super();
		this.uid=UUID.randomUUID().toString().replace("-", "");
		this.uname = uname;
		this.psd = psd;
	}

	public User(String addr) {
		super();
		this.address = addr;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCart_item() {
		return cart_item;
	}
	public void setCart_item(String cart_item) {
		this.cart_item = cart_item;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", psd=" + psd + ", order=" + order + ", cart_item="
				+ cart_item + ", address=" + address + "]";
	}
	
	
	
}
