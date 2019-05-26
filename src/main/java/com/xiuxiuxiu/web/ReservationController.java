package com.xiuxiuxiu.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiuxiuxiu.model.*;
import com.xiuxiuxiu.service.*;

@Controller
public class ReservationController {

	/** 图片保存的位置（相对路径） */
	private static final String UPLOAD_DIRECTORY = "uploadedImages";

	@Autowired
	ReservationService reservationService;

	@Autowired
	ActivityService activityService;

	@Autowired
	ComponentService componentService;

	@Autowired
	EquipmentService equipmentService;

	@Autowired
	ImgUrlService imgUrlService;

	@RequestMapping("/myRservationList")
	public String myRservationList(Model model) {
		return "/reservation/myReservationList";
	}

	@RequestMapping("/reservation/list")
	public String reservationList(Model model) {
		List<Reservation> reservations = reservationService.getReservationList();
		System.out.println("reservation list => " + reservations);
		model.addAttribute("reservations", reservations);
		return "reservation/list";
	}

	@RequestMapping("/reservation/step1")
	public String reservationStep1() {
		return "/reservation/reservationStep1";
	}

	@RequestMapping("/reservation/step2")
	public String reservationStep2(Model model) {
		// 获取活动场次列表
		List<Activity> activities = activityService.getActivityList();
		model.addAttribute("activities", activities);

		// 获取零件列表
		List<Component> components = componentService.getComponentList();
		model.addAttribute("components", components);
//		Student student = (Student) request.getSession().getAttribute("name"); // 获取设备列表
//		Integer userId = student.getID();
		List<Equipment> equipments = equipmentService.getEquipmentList();
		model.addAttribute("equipments", equipments);
		return "/reservation/reservationStep2";
	}

	@RequestMapping("/reservation/addFieldService")
	public String addFieldService(Model model) {
		model.addAttribute("message", "success");
		return "/reservation/reservationResult";
	}

	@RequestMapping("/reservation/submit")
	public String reservationSubmit(Model model, HttpServletRequest request, HttpSession session) {

		Reservation reservation = new Reservation();
		Student student = (Student) session.getAttribute("user");

		MultipartHttpServletRequest parameters = ((MultipartHttpServletRequest) request);
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("issueImage");

		// 预约场次判断
		Activity activity = null;
		String activityId = parameters.getParameter("activity");
		if (activityId != null && Character.isDigit(activityId.charAt(0))) {
			activity = activityService.findActivityById(Integer.parseInt(parameters.getParameter("activity")));
		} else {
			activity = activityService.findActivityById(0);
		}
		reservation.setActivity(activity);

		// 选择的设备判断
		String equipmentId = parameters.getParameter("device");
		if (equipmentId != null && Character.isDigit(equipmentId.charAt(0))) {
			Equipment equipment = equipmentService.findEquipmentById(Integer.parseInt(equipmentId));
			reservation.setEquipment(equipment);
		}

		// 处理非图片部分的表单

		reservation.setState(0); // 设置状态0: 未完成
		reservation.setStudent(student);

		reservation.setDetail(parameters.getParameter("issueDetail"));
		reservation.setRepairType(parameters.getParameter("repairType"));

		reservation.setApplicationType(Integer.parseInt(parameters.getParameter("applyType"))); // 设置申请的类型（活动预约或上门维修）

		if (activity != null && reservation.getApplicationType() == 0) { // 如果为活动预约
			reservation.setRequiredTime(activity.getTime());
			reservation.setPlace(activity.getPlace());
		} else if (student != null && reservation.getApplicationType() == 1) { // 如果为上门维修预约
			reservation.setRequiredTime(parameters.getParameter("requiredTime"));
			reservation.setPlace(student.getAddress());
		} else {

			model.addAttribute("message", "<遇到问题>  预约失败！！");
			System.out.println("预约类型获取错误！！预约失败！");
			return "rereservation/reserveResult";
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		reservation.setApplicationTime(df.format(new Date())); // 设置申请提交时间

		// 处理需求零件的信息
		String neededComponents = parameters.getParameter("neededComponents");
		if (neededComponents != null) {
			System.out.println("该预约单选择的零件：" + neededComponents);
			String[] componentIdList = neededComponents.split(",");
			if (componentIdList != null) {
				List<Component> componentList = new ArrayList<Component>();
				for (String componentId : componentIdList) {
					if (componentId.length() != 0) {
						int id = Integer.parseInt(componentId);
						componentList.add(componentService.findComponentById(id));
						// 记得删除...
						System.out.println("该预约单选择的零件：" + componentService.findComponentById(id).getName());
					}
				}
				reservation.setComponentList(componentList);
			}
		}

		// 保存上传的图片文件（还未进行图片验证）
		BufferedOutputStream stream = null;
		List<ReservationImgUrl> imgUrlList = new ArrayList<ReservationImgUrl>();

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					byte[] bytesFile;
					bytesFile = file.getBytes();

					String filePath = request.getSession().getServletContext().getRealPath("./") + UPLOAD_DIRECTORY
							+ File.separator;
					String fileName = file.getOriginalFilename();
					File targetFile = new File(filePath);

					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}

					// 生成预约单-图片url对应类加入imgUrlList中
					ReservationImgUrl imgUrl = new ReservationImgUrl();
					imgUrl.setImg_url(filePath + fileName);
					imgUrl.setReservation(reservation);
					imgUrlList.add(imgUrl);

					// 向./uploadedImages/fileName 文件写入bytesFile；
					System.out.println("文件上传：" + filePath + fileName);
					stream = new BufferedOutputStream(new FileOutputStream(filePath + fileName));
					stream.write(bytesFile);
					stream.close();
				} catch (IOException e) {
					stream = null;
					e.printStackTrace();
					return "You failed to upload file => " + e.getMessage();
				}
			}
		}
		reservation.setImgUrlList(imgUrlList);
		try {
			reservationService.save(reservation);

			// 存预约单-图片url地址对应关系到数据库
			for (ReservationImgUrl imgUrl : imgUrlList) {
				imgUrlService.save(imgUrl);
			}
		} catch (Exception e) {
			System.out.println("预约失败！");
			model.addAttribute("message", "预约失败！");
			System.err.println(e);
			return "reservation/reserveResult";
		}
		model.addAttribute("message", "预约成功！");
		System.out.println("预约成功！");
		return "reservation/reserveResult";
	}

	@RequestMapping("/reservation/componentSearch")
	public String componentSearch(HttpServletRequest request,Model model) {
//		String name = Optional.ofNullable(request.getParameter("userName")).orElse("");
//        int applicationType = Integer.parseInt(Optional.ofNullable(request.getParameter("MethodTypeSelect")).orElse("-1")) ;
//        int activityId = Integer.parseInt(Optional.ofNullable(request.getParameter("activityID")).orElse("-1"));
//        String componentType = Optional.ofNullable(request.getParameter("componentsTypeSelect")).orElse("");
//        int reservationState = Integer.parseInt(Optional.ofNullable(request.getParameter("StateSelect")).orElse("-1"));  
//        List<Component> components = componentService.getComponentList();
//        model.addAttribute("viewComponents", components);
//        request.setAttribute("totalNum", Optional.ofNullable(components).map(u->u.size()).orElse(0));
		return "/reservation/appointmentComponentSearch";
	}

	@RequestMapping("/reservation/appointmentHome")
	public String appointmentHome(Model model) {
		List<Activity> activities = activityService.getActivityList();
		model.addAttribute("activities", activities);
		return "/reservation/appointmentHome";
	}

	@RequestMapping("/reservation/appointedComponents")
	public String appointedComponents(Model model) {
        List<Activity> activities = activityService.getActivityList();
        model.addAttribute("activities", activities);
        model.addAttribute("components", componentService.getComponentList());
		return "/reservation/appointedComponents";
	}
}
