package org.crm.ctrls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.crm.model.Item;
import org.crm.model.User;
import org.crm.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import utils.PageData;

@Controller
@RequestMapping(value="/do")
public class MainCtrl {
	@Resource(name="MainService")
	MainService service;
	@Resource
	HttpServletRequest request;
	@ResponseBody
	@RequestMapping("/showAll")
	public List<Item> showAll()throws Exception {
		List<Item> list=new ArrayList<Item>();
		list=service.executAll();
		return list;
	}
	@ResponseBody
	@RequestMapping("/showCart")
	public List<Item> showByStatus(HttpServletRequest req)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String uid=pd.getString("uid");

		List list=new ArrayList();
		String rs=service.showca(uid);
		
		if(rs==null){
			return null;
		}else{
			rs=rs.substring(rs.indexOf("=")+1, rs.length()-1);
			String s1 []=rs.split("@");
			String s2 []=new String [s1.length];
			String s3 []=new String [s1.length];
			
			for(int i=0;i<s1.length;i++){
				s2[i]=s1[i].split("~")[0];
				s3[i]=s1[i].split("~")[1];
				list.add(service.showcamess(s2[i]));
			}
			list.add(0,s3);
		}
		System.out.println(list.toString());
		return list;
	}
	@ResponseBody
	@RequestMapping("/showOrder")
	public String[][] showOrders(HttpServletRequest req)throws Exception {
		PageData pd=new PageData(req);
		String uid=pd.getString("uid");
		String rs [][]=service.showor(uid);
		if(rs.length==0){
			return null;
		}
		return rs;
	}
	@ResponseBody
	@RequestMapping("/login")
	public JSONObject doLogin(HttpServletRequest req )throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("username"),pd.getString("password")};
		System.out.println(temp[0]+"~"+temp[1]);
		Map map=new HashMap();
		map.put("uname", temp[0]);
		List lis=service.executByName(map);
		if(lis!=null){
			String t=lis.get(0).toString();
			String s=t;
			
			s=s.substring(s.indexOf("uid="),s.length());
			s=s.substring(s.indexOf("uid=")+4,s.indexOf(","));
			
			t=t.substring(t.indexOf("psd=")+4,t.indexOf(","));
			if(t.equals(temp[1])){
				jo.put("success", "true");
				jo.put("uid", s);
			}else{
				jo.put("success", "false");
			}
		}
		System.out.println(req.getLocalAddr());
		return jo;
	}
	@ResponseBody
	@RequestMapping("/changepsd")
	public JSONObject changepsd(HttpServletRequest req )throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid"),pd.getString("opsd"),pd.getString("npsd")};
		System.out.println(temp[0]+"~"+temp[1]+"~"+temp[2]);
		Map map=new HashMap();
		map.put("uid", temp[0]);
		List lis=service.executById(map);
		if(lis!=null){
			String t=lis.get(0).toString();
			t=t.substring(t.indexOf("psd=")+4,t.length()-1);
			if(t.equals(temp[1])){
				service.updatepsd(temp);
				jo.put("success", "修改密码成功！");
			}else{
				jo.put("success", "原始密码错误！");
			}
		}
		return jo;
	}
	@ResponseBody
	@RequestMapping("/reg")
	public JSONObject doReg(HttpServletRequest req )throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("username"),pd.getString("password")};
		System.out.println(temp[0]+"~"+temp[1]);
		Map map=new HashMap();
		map.put("uname", temp[0]);
		List lis=service.executByName(map);
		if(lis!=null){
			jo.put("success", "error");
			return jo;
		}
		
		User u =new User(temp[0],temp[1]);
		Boolean flag=service.addUser(u);
		if(flag){
			jo.put("success", "true");
			jo.put("uid", u.getUid());
		}else{
			jo.put("success", "false");
		}
		return jo;
	}
	@ResponseBody
	@RequestMapping("/messages")
	public JSONObject doMes(HttpServletRequest req )throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid")};
		
		Map map=new HashMap();
		map.put("uid", temp[0]);
		List lis=service.executAddressByUid(map);
		
		String r=lis.get(0).toString();
		r=r.substring(r.indexOf("=")+1,r.length()-1);
		jo.put("success", r);
		return jo;
	}
	@ResponseBody
	@RequestMapping("/chan")
	public void doChan(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		req.setCharacterEncoding("utf-8");
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid"),pd.getString("addr")};
		System.out.println(temp[0]+"~~"+temp[1]);
		User us=new User(temp[1]);
		us.setUid(temp[0]);
		Boolean f =service.updateOne(us);
		rsp.sendRedirect("../user.html");
		return ;
	}
	@ResponseBody
	@RequestMapping("/tocart")
	public void doTocart(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid"),pd.getString("sid"),pd.getString("count")};
		System.out.println(temp[0]+"~~"+temp[1]+"~~"+temp[2]);
		
		Boolean f =service.updateOne3(temp);
		rsp.sendRedirect("../more.html");
		return ;
	}
	@ResponseBody
	@RequestMapping("/addOrder")
	public void addOrder(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid"),pd.getString("ordermes")};
		Boolean f =service.addOr(temp);
		rsp.sendRedirect("../more.html");
		return ;
	}
	@ResponseBody
	@RequestMapping("/delCart")
	public void delCart(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid")};
		Boolean f =service.delCart(temp);
		rsp.sendRedirect("../more.html");
		return ;
	}
	@ResponseBody
	@RequestMapping("/delorder")
	public void delOrder(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("orderid")};
		Boolean f =service.delOrder(temp);
		rsp.sendRedirect("../more.html");
		return ;
	}
	@ResponseBody
	@RequestMapping("/tocart2")
	public void doTocart2(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid"),pd.getString("sid"),pd.getString("count")};
		System.out.println(temp[0]+"~~"+temp[1]+"~~"+temp[2]);
		
		Boolean f =service.updateOne2(temp);
		rsp.sendRedirect("../more.html");
		return ;
	}
	@ResponseBody
	@RequestMapping("/tocart3")
	public JSONObject doTocart3(HttpServletRequest req,HttpServletResponse rsp)throws Exception {
		JSONObject jo=new JSONObject();
		PageData pd=new PageData(req);
		String [] temp={pd.getString("uid"),pd.getString("sid"),pd.getString("count")};
		System.out.println(temp[0]+"~~"+temp[1]+"~~"+temp[2]);
		
		Boolean f =service.updateOne4(temp);
		rsp.sendRedirect("../cart.html");
		jo.put("success", "true");
		return jo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setService(MainService service) {
		this.service = service;
	}
}
