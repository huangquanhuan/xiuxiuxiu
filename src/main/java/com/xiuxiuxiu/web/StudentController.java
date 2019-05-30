package com.xiuxiuxiu.web;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.service.ActivityService;
import com.xiuxiuxiu.service.EquipmentService;
import com.xiuxiuxiu.service.ReservationService;
import com.xiuxiuxiu.service.StudentService;
import com.xiuxiuxiu.service.impl.StudentServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class StudentController {

	@Resource
	StudentServiceImpl studentService;
	@Resource
	ReservationService reservationService;
	@Resource
	ActivityService activityService;
	@Resource
	EquipmentService equipmentService;

	@RequestMapping("/")
	public String index() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String inex(Model model) {
		List<Activity> activityList = activityService.getActivityList();
		model.addAttribute("activityList", activityList);
		return "home/index";
	}
	
	@RequestMapping("/findALL")
    @ResponseBody
    public List<Student> findAll(){
        List< Student> list = studentService.findAll();
        return list;
    }
	
//	@RequestMapping("/getAll")
//	@ResponseBody
//    public ReturnData<Student> findAllNoQuery(Mode mode,@RequestParam(value="offset",defaultValue="0") Integer offset,
//    		@RequestParam(value="limit",defaultValue="5") Integer limit) {
//		int sum=studentService.findAll().size();
//		Page<Student> datas = studentService.findAll(offset, limit);
//		List<Student> stuDatas = datas.getContent(); 
//		return new ReturnData<Student>(sum,stuDatas);
//    }
	
	@RequestMapping("/getAll2")
	@ResponseBody
    public Map<Integer, List<Student>> findAllNoQuery2(Mode mode,@RequestParam(value="offset",defaultValue="0") Integer offset,
    		@RequestParam(value="limit",defaultValue="5") Integer limit) {
    	System.out.println(offset +"\n\n"+limit);
    	int sum=studentService.findAll().size();
		Page<Student> datas = studentService.findAll(offset, limit);
		List<Student> stuDatas = datas.getContent();
		Map<Integer, List<Student>> ans = new HashMap<Integer, List<Student>>();
		ans.put(sum, stuDatas);
		return ans;
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<Activity> activityList = activityService.getActivityList();
		model.addAttribute("activityList", activityList);
		
		List<Reservation> reservationsList = reservationService.getReservationList();
		int reservationCount = reservationsList.size();
		model.addAttribute("reservationCount", reservationCount);
		int serviceEquipmentCount = 0;
		for(Reservation reservation:reservationsList) {
			if(reservation.getEquipment()!=null)
				serviceEquipmentCount++;
		}
		model.addAttribute("serviceEquipmentCount", serviceEquipmentCount);
		model.addAttribute("reservationCount", reservationCount);
		return "home/HomePage";
	}

	@RequestMapping("/student/edit")
	public String edit(HttpSession session, HttpServletRequest request) {

		String changeName = request.getParameter("name");
		String changeStudentId = request.getParameter("studentId");
		String changeEmail = request.getParameter("email");
		String changeAddress = request.getParameter("address");
		Student user = (Student) session.getAttribute("user");
		user.setName(changeName);
		user.setStudentId(changeStudentId);
		user.setEmail(changeEmail);
		user.setAddress(changeAddress);

		studentService.edit(user);
		return "redirect:/home";
	}

	@RequestMapping("/student/delete")
	public String delete(int id) {
		studentService.delete(id);
		return "redirect:/home";
	}

	@RequestMapping("/student/login")
	public String login(Model model, @RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("password") String password, HttpSession session) {

		Student student = studentService.findStudentByPhoneNumber(phoneNumber);

		if (student == null) {
			model.addAttribute("err", "抱歉，该账号不存在！");
			System.out.println("登陆账号不存在！");
		} else if (student.getPhoneNumber().length() < 1) {
			model.addAttribute("err", "请输入登录名！");
		} else if (student.getPassword().length() < 1) {
			model.addAttribute("err", "请输入密码！");
		} else if (!student.getPassword().equals(password)) {
			// 登陆失败
			System.out.println("真实密码：" + student.getPassword());
			System.out.println("输入密码：" + password);
			model.addAttribute("err", "密码错误！");
			System.out.println("登陆密码错误！");
		} else {
			// 登陆成功
			session.setAttribute("user", student);
			System.out.println("获取用户设备列表：");
			for (Equipment eq : student.getEquipmentList()) {
				System.out.println(eq.getEquipmentName());
			}
		}

		return "redirect:/home";
	}

	@RequestMapping("student/register")
	public String register(Model model, @RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("passWord") String passWord,
			@RequestParam("passWord2") String passWord2, @RequestParam("address") String address, @RequestParam("email") String email,@RequestParam("code") String code,HttpSession session) {
		System.out.println("昵称:" + name);
		System.out.println("号码:" + phoneNumber);
		System.out.println("地址:" + address);
		System.out.println("邮箱:" + email);
		System.out.println("验证码" +code);
		System.out.println("密码:" + passWord);
		System.out.println("确认密码:" + passWord2);
		if (!passWord.equals(passWord2)) {
			model.addAttribute("err", "两次密码不同！");
			System.out.println("两次密码不同！");
		}else if (!code.equals(session.getAttribute("email"))) {
			model.addAttribute("err", "验证码错误！");
			System.out.println("验证码错误！");
		}  else if (!isMobileNO(phoneNumber)) {
			model.addAttribute("err", "手机号格式错误！");
			System.out.println("手机号格式错误！");
		} else if (name.length() < 2 || name.length() > 12) {
			model.addAttribute("err", "昵称长度范围在“2~12”之间");
			System.out.println("昵称长度范围在“2~12”之间");
		} else {
			Student student = new Student();
			student.setName(name);
			student.setAddress(address);
			student.setEmail(email);
			student.setPassword(passWord);
			student.setPhoneNumber(phoneNumber);
			student.setAccessLevel(0);
			studentService.save(student);
			System.out.println("注册成功");
		}
		return "redirect:/home";
	}

	@RequestMapping("student/exit")
	public String exit(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}

	@RequestMapping("/student/equipmentEdit")
	public String equipmentEdit(Model model, HttpSession session, @RequestParam("equipmentId") int equipmentId,
			@RequestParam("equipmentName") String equipmentName) {
		Student user = (Student) session.getAttribute("user");

		Equipment equipment = new Equipment();
		equipment.setEquipmentName(equipmentName);
		equipment.setId(equipmentId);
		equipment.setStudent(user);

		equipmentService.edit(equipment);
		model.addAttribute("message", "编辑设备成功");
		

		// 更新页面的session
		user = studentService.findStudentById(user.getId());
		session.setAttribute("user", user);
		System.out.println("更新前端的session\n获取用户设备列表：");
		for (Equipment eq : user.getEquipmentList()) {
			System.out.println(eq.getEquipmentName());
		}
		return "redirect:/home";
	}

	@RequestMapping("/student/equipmentDelete")
	public String equipmentDelete(Model model, HttpSession session, @RequestParam("equipmentId") int equipmentId) {
		equipmentService.delete(equipmentId);
		model.addAttribute("message", "删除设备成功");

		// 更新页面的session
		Student user = (Student) session.getAttribute("user");
		user = studentService.findStudentById(user.getId());
		session.setAttribute("user", user);
		System.out.println("更新前端的session\n获取用户设备列表：");
		for (Equipment eq : user.getEquipmentList()) {
			System.out.println(eq.getEquipmentName());
		}
		return "redirect:/home";
	}

	@RequestMapping("student/addEquipment")
	public String addEquipment(Model model, HttpSession session, @RequestParam("equipmentName") String equipmentName) {
		
		Student user = (Student) session.getAttribute("user");

		Equipment equipment = new Equipment();
		equipment.setEquipmentName(equipmentName);
		equipment.setStudent(user);

		equipmentService.edit(equipment);
		model.addAttribute("message", "添加设备成功");
		
		// 更新页面的session
		user = studentService.findStudentById(user.getId());
		session.setAttribute("user", user);
		System.out.println("更新前端的session\n获取用户设备列表：");
		for (Equipment eq : user.getEquipmentList()) {
			System.out.println(eq.getEquipmentName());
		}
		return "redirect:/home";
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
