package org.crm.model;

import java.util.UUID;

public class Orders {
	private String orderid=null;
	private String uid=null;
	private String items=null;
	
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Orders [orderid=" + orderid + ", uid=" + uid + ", items=" + items + "]";
	}
	public Orders(String uid, String items) {
		super();
		this.orderid = UUID.randomUUID().toString().replace("-", "");
		this.uid = uid;
		this.items = items;
	}
	
}
