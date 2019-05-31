package com.xiuxiuxiu.web;


import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.service.ArticleService;
import com.xiuxiuxiu.service.ManagerService;
import com.xiuxiuxiu.utility.MyMD5Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class RepairPersonController {

	@Resource
	ManagerService managerService;

	 @Resource
	    ArticleService articleService;
    @RequestMapping("/person")
    public String index() {
        return "redirect:/manager/person";
    }

    @RequestMapping("/manager/person")
    public String list(Model model,HttpSession session) {
    	if(session.getAttribute("administrator")==null) {
    		model.addAttribute("err", "登陆信息已过期，请重新登录！");
    		return "redirect:/manager";
    	}
    	List<Manager> managerList=managerService.getManagerList();
    	model.addAttribute("managerList", managerList);
        
        return "/manager/RepairPerson";
    }

    @RequestMapping("/manager/AddPerson")
	public String delete(Model model,@RequestParam("name") String name,
			@RequestParam("password1") String password1,@RequestParam("password2") String password2,
			@RequestParam("accesslist") String access_level,@RequestParam("phone_number") String phone_number
			,@RequestParam("email") String email,@RequestParam("address") String address,HttpSession session) {
    	if(session.getAttribute("administrator")==null) {
    		model.addAttribute("err", "登陆信息已过期，请重新登录！");
    		return "redirect:/manager";
    	}
    	System.out.println("name => "+name);
    	System.out.println("password1 => "+password1);
    	System.out.println("password2 => "+password2);
    	System.out.println("address => "+address);
    	System.out.println("access_level => "+access_level);
    	System.out.println("phone_number => "+phone_number);
    	System.out.println("email => "+email);
    	if (!password1.equals(password2)) {
			model.addAttribute("err", "两次密码不同！");
			System.out.println("两次密码不同！");
		}  else if (!isMobileNO(phone_number)) {
			model.addAttribute("err", "手机号格式错误！");
			System.out.println("手机号格式错误！");
		} else if (name.length() < 2 || name.length() > 12) {
			model.addAttribute("err", "昵称长度范围在“2~12”之间");
			System.out.println("昵称长度范围在“2~12”之间");
		} else if(managerService.findManagerByPhoneNumber(phone_number)!=null){
			model.addAttribute("err", "该手机号已经注册过！");
			System.out.println("该手机号已经注册过！");

		} else if(!access_level.equals("1") && !access_level.equals("2") ){

			model.addAttribute("err", "权限等级请输入1或2");
			System.out.println("权限等级请输入1或2");
		}else{
    	Manager manager=new Manager();
    	manager.setName(name);
    	manager.setAccessLevel(Integer.parseInt(access_level));
    	manager.setAddress(address);
    	manager.setPhoneNumber(phone_number);
    	manager.setEmail(email);
    	
    	String encryptedPassword = null;   
		try {   
			encryptedPassword = MyMD5Util.getEncryptedPwd(password2);   
			System.out.println("加密后的密码："+encryptedPassword);
			manager.setPassword(encryptedPassword);
			
		} catch (NoSuchAlgorithmException e) {   
			model.addAttribute("err", "加密算法在当前环境中不可用，注册失败！");
			System.out.println("加密算法在当前环境中不可用，注册失败！");
			e.printStackTrace();   
			return list(model,session);
		} catch (UnsupportedEncodingException e) {   
			model.addAttribute("err", "密码包含不支持的字符编码，注册失败！");
			System.out.println("密码包含不支持的字符编码，注册失败！");
			e.printStackTrace();   
			return list(model,session);
		}
    	
    	
    	
    	try {
    		managerService.save(manager);
			model.addAttribute("message", "添加成功!");
			System.out.println("添加成功");
		} catch (Exception e) {
			model.addAttribute("err", "抱歉，由于数据库原因，添加失败");
			System.out.println("抱歉，由于数据库原因，添加失败");
			e.printStackTrace();
		}
    	
		}
		return list(model,session);
	}
    
    @RequestMapping("/manager/UpdatePerson")
	public String update(Model model,@RequestParam("id") int id,@RequestParam("name") String name,
			@RequestParam("password1") String password1,@RequestParam("password2") String password2,
			@RequestParam("accesslist") String access_level,@RequestParam("phone_number") String phone_number
			,@RequestParam("email") String email,@RequestParam("address") String address,HttpSession session){
    	if(session.getAttribute("administrator")==null) {
    		model.addAttribute("err", "登陆信息已过期，请重新登录！");
    		return "redirect:/manager";
    	}
    	System.out.println("id => "+id);
    	System.out.println("name => "+name);
    	System.out.println("password1 => "+password1);
    	System.out.println("password2 => "+password2);
    	System.out.println("address => "+address);
    	System.out.println("access_level => "+access_level);
    	System.out.println("phone_number => "+phone_number);
    	System.out.println("email => "+email);
    	if (!password1.equals(password2)) {
			model.addAttribute("err", "两次密码不同！");
			System.out.println("两次密码不同！");
		}  else if (!isMobileNO(phone_number)) {
			model.addAttribute("err", "手机号格式错误！");
			System.out.println("手机号格式错误！");
		} else if (name.length() < 2 || name.length() > 12) {
			model.addAttribute("err", "昵称长度范围在“2~12”之间");
			System.out.println("昵称长度范围在“2~12”之间");
		} else if(managerService.findManagerByPhoneNumber(phone_number)!=null){
			model.addAttribute("err", "该手机号已经注册过！");
			System.out.println("该手机号已经注册过！");
		} else if(!access_level.equals("1") && !access_level.equals("2") ){
			model.addAttribute("err", "权限等级请输入1或2");
			System.out.println("权限等级请输入1或2");
		}else{
    	Manager manager=managerService.findManagerById(id);
    	manager.setName(name);
    	manager.setPassword(password2);
    	manager.setAccessLevel(Integer.parseInt(access_level));
    	manager.setAddress(address);
    	manager.setPhoneNumber(phone_number);
    	manager.setEmail(email);
    	try {
    		managerService.edit(manager);
			model.addAttribute("message", "修改成功!");
			System.out.println("修改成功");
		} catch (Exception e) {
			model.addAttribute("err", "抱歉，由于数据库原因，修改失败");
			System.out.println("抱歉，由于数据库原因，修改失败");
			e.printStackTrace();
		}
		}
		return list(model,session);
	}
    
    @RequestMapping("/manager/DeletePerson")
	public String update(Model model,@RequestParam("id") int id,HttpSession session){
    	if(session.getAttribute("administrator")==null) {
    		model.addAttribute("err", "登陆信息已过期，请重新登录！");
    		return "redirect:/manager";
    	}
    	System.out.println("id => "+id);
    //这里还需要判断改管理员是否发表过文章，否则不能删除
    	if(articleService.findArticleById(id)!=null);
    		model.addAttribute("err", "该管理发表过文章，不能删除，请先删除文章");
    	managerService.delete(id);
    	return list(model,session);
	}
	/*
	 * 判断手机号格式
	 */
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/*
	 * 判断邮箱格式
	 */
	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}

