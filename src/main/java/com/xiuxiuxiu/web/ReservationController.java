package com.xiuxiuxiu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.service.ActivityService;
import com.xiuxiuxiu.service.ComponentService;
import com.xiuxiuxiu.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@Autowired
	ActivityService activityService;
	
	@Autowired
	ComponentService componentService;
	
	@RequestMapping("/reservation")
	public String index() {
		return "redirect:/reservation/list";
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
		/*
		 * ComponentDAO componentDAO = new ComponentDAOImpl(); List<Component>
		 * components = componentDAO.getList(); request.setAttribute("components",
		 * components); Student student = (Student)
		 * request.getSession().getAttribute("name"); // 获取设备列表 Integer userId =
		 * student.getID(); EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
		 * List<Equipment> equipments = equipmentDAO.listEquipmentsByUser(userId);
		 * request.setAttribute("equipments", equipments);
		 */
		return "/reservation/reservationStep2";
	}
}
