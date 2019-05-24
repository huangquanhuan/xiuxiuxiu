package com.xiuxiuxiu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationController {

	@RequestMapping("/reservation/list")
	public String reservationList() {
		return "reservation/list";
	}
}
