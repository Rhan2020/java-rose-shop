package org.crm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.crm.dao.DaoSupport;
import org.crm.model.Item;
import org.crm.model.Orders;
import org.crm.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import utils.PageData;

@Service("MainService")
public class MainService {
	@Resource(name="daoSupport")
	private DaoSupport dao;

	public List<Item> executAll()throws Exception {
		List list=dao.selectAll("Item.select_all");
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}

	public void setDao(DaoSupport dao) {
		this.dao = dao;
	}
	public boolean addUser(User us)throws Exception {
		int a=(int) dao.save("User.insert", us);
		if(a==1){
			return true;
		}
		return false;
	}

	public List<User>  executByName(Map map) throws Exception {
		List list=(List) dao.findForList("User.select_name",map);
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}
	public List<User>  executById(Map map) throws Exception {
		List list=(List) dao.findForList("User.select_idpsd",map);
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}

	public List<User> executAddressByUid(Map map) throws Exception {
		// TODO Auto-generated method stub
		List list=(List) dao.findForList("User.select_id",map);
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}
	public List<Item> showcamess(String sid) throws Exception {
		// TODO Auto-generated method stub
		Map map =new HashMap();
		map.put("sid", sid);
		List list=(List) dao.findForList("Item.select_name",map);
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}
	public boolean deleteById(Map map) throws Exception {
		int a=(int) dao.save("Customer.delete", map);
		if(a==1){
			return true;
		}
		return false;
	}

	
	
	public PageData showOne(String id) throws Exception{
		PageData pd1 = (PageData)dao.findForObject("Customer.select_one", id);
		return pd1;
	}

	public boolean updateOne(User u) throws Exception {
		int a=(int) dao.save("User.update", u);
		if(a==1){
			return true;
		}
		return false;
	}
	
	public boolean updateOne2(String[] u) throws Exception {
		Map map =new HashMap();
		map.put("uid", u[0]);
		List list=(List) dao.findForList("User.select_cart",map);
		String te=list.get(0).toString();
		te=te.substring(te.indexOf("=")+1,te.length()-1);
		if(te.contains(u[1])){
			String yo=te.substring(te.indexOf(u[1])+u[1].length()+1,te.length());
			String io="";
			te=te.substring(0,te.indexOf(u[1])+u[1].length());
			io=yo.split("@")[0];
			yo=yo.substring(yo.indexOf("@")+1, yo.length());
			io=String.valueOf((Integer.parseInt(io)+Integer.parseInt(u[2])));
			yo=te+"~"+io+"@"+yo;
			te=yo;
		}else{
			te=te+u[1]+"~"+u[2]+"@";
		}
		User u1=new User();
		u1.setUid(u[0]);
		u1.setCart_item(te);
		int a=(int) dao.save("User.update2", u1);
		if(a==1){
			return true;
		}
		return false;
	}
	public boolean updateOne3(String[] u) throws Exception {
		Map map =new HashMap();
		map.put("uid", u[0]);
		List list=(List) dao.findForList("User.select_cart",map);
		String te=list.get(0).toString();
		te=te.substring(te.indexOf("=")+1,te.length()-1);
		if(te.contains(u[1])){
			String yo=te.substring(te.indexOf(u[1])+u[1].length()+1,te.length());
			String io="";
			te=te.substring(0,te.indexOf(u[1])+u[1].length());
			io=yo.split("@")[0];
			yo=yo.substring(yo.indexOf("@")+1, yo.length());
			io=String.valueOf((Integer.parseInt(io)-Integer.parseInt(u[2])));
			yo=te+"~"+io+"@"+yo;
			te=yo;
		}else{
			te=te+u[1]+"~"+u[2]+"@";
		}
		User u1=new User();
		u1.setUid(u[0]);
		u1.setCart_item(te);
		int a=(int) dao.save("User.update2", u1);
		if(a==1){
			return true;
		}
		return false;
	}
	public boolean addOr(String[] u) throws Exception {
		Map map =new HashMap();
		map.put("uid", u[0]);
		map.put("items", u[1]);
		Orders o=new Orders(u[0],u[1]);
		map.put("orderid", o.getOrderid());
		int a=(int) dao.save("Orders.insert", map);
		
		User u1=new User();
		u1.setUid(u[0]);
		u1.setCart_item("");
		int b=(int) dao.save("User.update2", u1);
		if(a==1&&b==1){
			System.out.println("成功提交订单，并清空购物车");
			return true;
		}
		return false;
	}
	public boolean delCart(String[] u) throws Exception {
		User u1=new User();
		u1.setUid(u[0]);
		u1.setCart_item("");
		int b=(int) dao.save("User.update2", u1);
		if(b==1){
			System.out.println("成功清空购物车");
			return true;
		}
		return false;
	}
	public boolean delOrder(String[] u) throws Exception {
		Map map =new HashMap();
		map.put("orderid", u[0]);
		int b=(int) dao.delete("Orders.delete", map);
		if(b==1){
			System.out.println("删除订单成功！");
			return true;
		}
		return false;
	}
	public boolean updateOne4(String[] u) throws Exception {
		Map map =new HashMap();
		map.put("uid", u[0]);
		List list=(List) dao.findForList("User.select_cart",map);
		String te=list.get(0).toString();
		te=te.substring(te.indexOf("=")+1,te.length()-1);
		if(te.contains(u[1])){
			String yo=te.substring(te.indexOf(u[1])+u[1].length()+1,te.length());
			String io="";
			te=te.substring(0,te.indexOf(u[1])+u[1].length());
			io=yo.split("@")[0];
			yo=yo.substring(yo.indexOf("@")+1, yo.length());
			te=yo;
		}else{
			te=te;
		}
		User u1=new User();
		u1.setUid(u[0]);
		u1.setCart_item(te);
		int a=(int) dao.save("User.update2", u1);
		if(a==1){
			return true;
		}
		return false;
	}
	public boolean updatepsd(String[] u) throws Exception {
		
		User u1=new User();
		u1.setUid(u[0]);
		u1.setPsd(u[2]);
		int a=(int) dao.save("User.update7", u1);
		if(a==1){
			return true;
		}
		return false;
	}
	public String showca(String uid) throws Exception {
		Map map =new HashMap();
		map.put("uid", uid);
		List list=(List) dao.findForList("User.select_cart",map);
		String te=list.get(0).toString();
		return te;
	}
	public String[][] showor(String uid) throws Exception {
		Map map =new HashMap();
		map.put("uid", uid);
		List list=(List) dao.findForList("Orders.select_name",map);
		String l [][] =new String [list.size()][2];
		for(int c=0;c<list.size();c++){
			String tem=list.get(c).toString();
			tem=tem.substring(tem.indexOf("orderid=")+8,tem.length());
			tem=tem.substring(0,tem.indexOf(","));
			
			Map m=new HashMap();
			m.put("orderid", tem);
			List li=(List) dao.findForList("Orders.select_id",m);
			String tt=li.get(0).toString();
			tt=tt.substring(tt.indexOf("items=")+6,tt.length()-1);
			System.out.println(tt);
			String s1 []=tt.split("@");
			String s2 []=new String [s1.length];//商品ID
			String s3 []=new String [s1.length];//数量
			
			for(int i=0;i<s1.length;i++){
				List lu=null;
				s2[i]=s1[i].split("~")[0];
				s3[i]=s1[i].split("~")[1];
				lu=showcamess(s2[i]);
				l[c][i]=lu.toString().replace("=", ":").substring(2, lu.toString().length()-2)+","+s3[i]+""+","+tem+"";
			}
			
		}
		return l;
	}

}
