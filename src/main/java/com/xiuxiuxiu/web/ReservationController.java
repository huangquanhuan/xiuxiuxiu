package com.xiuxiuxiu.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/myReservationList")
	public String myReservationList(Model model, HttpSession session) {
		List<Reservation> allReservations = reservationService.getReservationList();
		List<Reservation> reservations = new ArrayList<Reservation>();
		Student user = (Student) session.getAttribute("user");

		for (Reservation reservation : allReservations) {
			// 找出该用户对应的预约单
			if (reservation.getStudent().getId() == user.getId()) {
				String cutDetail = "详情:" + reservation.getDetail();
				if (reservation.getDetail() == null || reservation.getDetail().length() < 1) {
					cutDetail = "详情:未填写";
				} else if (cutDetail.length() > 14)
					cutDetail = cutDetail.substring(0, 13) + "...";

				reservation.setCutDetail(cutDetail);
				reservations.add(reservation);
			}
		}

		for (Reservation myReservation : reservations) {
			// 传递预约单对应图片url
			List<ReservationImgUrl> imgUrls = myReservation.getImgUrlList();
			for (ReservationImgUrl imgUrl : imgUrls) {
				// 注意！《配置到服务器时》注意检查预约单图片存储路径，然后在application.properties中修改虚拟路径对应的实际路径...
				// 已经设置一个虚拟路径对应实际路径的C:/Users/，所以进行路径剪裁
				// 例如一个url="C:\Users\10553\AppData\Local\Temp..."=>"10553\AppData\Local\Temp..."
				String cutUrl = imgUrl.getImg_url().substring(9);
				System.out.println(cutUrl);
				imgUrl.setImg_url(cutUrl);
			}
			myReservation.setImgUrlList(imgUrls);

		}

		model.addAttribute("reservations", reservations);
		return "/reservation/myReservationList";
	}

	@RequestMapping("/editMyReservation")
	public String editMyReservation(Model model, @RequestParam("reservationId") int reservationId) {
		Reservation reservation = reservationService.findReservationById(reservationId);
		model.addAttribute("reservation", reservation);

		// 获取所有活动场次列表
		List<Activity> activities = activityService.getActivityList();
		// 获取所有零件列表
		List<Component> components = componentService.getComponentList();
		// 获取所有设备列表
		List<Equipment> equipments = equipmentService.getEquipmentList();

		// 传递给页面的维修活动列表去除已选的活动
		Activity reservationActivity = reservation.getActivity();
		if (reservationActivity != null) {
			if (activities.remove(reservationActivity))
				System.out.println("activitys中移除一个activity");
		}

		// 传递给页面的零件列表去除已选的零件
		List<Component> reservationComponentList = reservation.getComponentList();
		for (Component component : reservationComponentList) {
			if (components.remove(component))
				System.out.println("components中移除一个component");
		}

		// 传递给页面的设备列表去除已选的设备
		Equipment reservationEquipment = reservation.getEquipment();
		if (reservationEquipment != null) {
			if (equipments.remove(reservationEquipment))
				System.out.println("equipments中移除一个equipment");
		}

		model.addAttribute("activities", activities);

		model.addAttribute("components", components);

		model.addAttribute("equipments", equipments);

		return "/reservation/myReservationEdit";
	}

	@RequestMapping("/cancelMyReservation")
	public String cancelMyReservation(Model model, @RequestParam("reservationId") int reservationId) {

		try {
			reservationService.delete(reservationId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "预约单已撤销！");
		return "redirect:/myReservationList";
	}

	@RequestMapping("/remarkMyReservation")
	public String remarkMyReservation(Model model, @RequestParam("reservationId") int reservationId,
			@RequestParam("remarkText") String remarkText) {

		Reservation reservation = reservationService.findReservationById(reservationId);
		reservation.setRemark(remarkText);
		reservationService.save(reservation);
		model.addAttribute("message", "评价已提交！");
		return "redirect:/myReservationList";
	}

	@RequestMapping("/feedbackMyReservation")
	public String feedbackMyReservation(Model model, @RequestParam("reservationId") int reservationId,
			@RequestParam("feedbackTxet") String feedbackTxet) {

		Reservation reservation = reservationService.findReservationById(reservationId);
		reservation.setFeedback(feedbackTxet);
		reservationService.save(reservation);
		model.addAttribute("message", "反馈已提交！");
		return "redirect:/myReservationList";
	}

//	我的预约单详情页整合到预约单列表页上去作为弹窗了
//	@RequestMapping("/myReservationDetail")
//	public String myReservationDetail(Model model, @RequestParam("reservationId") int reservationId) {
//		Reservation myReservation = reservationService.findReservationById(reservationId);
//		List<Component> componentList = myReservation.getComponentList();
//		String components = "";
//		for (Component component : componentList) {
//			components = components + "," + component.getName();
//		}
//		if (components.length()>1)
//			components=components.substring(1);// 去除第一个逗号
//		System.out.println(components);
//		model.addAttribute("components", components);
//
//		List<ReservationImgUrl> imgUrls = myReservation.getImgUrlList();
//		for (ReservationImgUrl imgUrl : imgUrls) {
//			// 注意！配置到服务器时注意检查预约单图片存储路径，然后在application.properties中修改虚拟路径对应的实际路径...
//			// 已经设置一个虚拟路径对应实际路径的C:/Users/，所以进行路径剪裁
//			// 例如一个url="C:\Users\10553\AppData\Local\Temp..."=>"10553\AppData\Local\Temp..."
//			String cutUrl = imgUrl.getImg_url().substring(9);
//			System.out.println(cutUrl);
//			imgUrl.setImg_url(cutUrl);
//		}
//		model.addAttribute("imgUrls", imgUrls);
//
//		model.addAttribute("reservation", myReservation);
//		return "/reservation/myReservationList";
//	}

	@RequestMapping("/reservation/step1")
	public String reservationStep1() {
		return "/reservation/reservationStep1";
	}

	@RequestMapping("/reservation/step2")
	public String reservationStep2(Model model) {
		// 获取所有活动场次列表
		List<Activity> activities = activityService.getActivityList();
		model.addAttribute("activities", activities);

		// 获取所有零件列表
		List<Component> components = componentService.getComponentList();
		model.addAttribute("components", components);

		// 获取所有设备列表
		List<Equipment> equipments = equipmentService.getEquipmentList();
		model.addAttribute("equipments", equipments);
		return "/reservation/reservationStep2";
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

			model.addAttribute("err", "<遇到问题>  预约失败！！");
			System.out.println("预约类型获取错误！！预约失败！");
			return "rereservation/reserveResult";
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		reservation.setApplicationTime(df.format(new Date())); // 设置申请提交时间

		// 处理需求零件的信息
		String[] neededComponentIdList = parameters.getParameterValues("neededComponents");
		if (neededComponentIdList != null) {
			List<Component> componentList = new ArrayList<Component>();
			for (String componentId : neededComponentIdList) {
				if (componentId.length() != 0) {
					int id = Integer.parseInt(componentId);
					componentList.add(componentService.findComponentById(id));
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
			// 表单没有传reservationId说明是新增预约单，否则为修改
			if (parameters.getParameter("reservationId") == null) {
				reservationService.save(reservation);

				// 存预约单-图片url地址对应关系到数据库
				for (ReservationImgUrl imgUrl : imgUrlList) {
					imgUrlService.save(imgUrl);
				}
				model.addAttribute("message", "预约成功！");
				System.out.println("预约成功！");
			} else {
				reservationService.delete(Integer.parseInt(parameters.getParameter("reservationId")));
				reservationService.save(reservation);
				// 存预约单-图片url地址对应关系到数据库
				for (ReservationImgUrl imgUrl : imgUrlList) {
					imgUrlService.save(imgUrl);
				}

				model.addAttribute("message", "修改成功！");
				System.out.println("修改成功！");
			}
		} catch (Exception e) {
			System.out.println("预约失败！");
			model.addAttribute("message", "对不起，由于未知原因，预约失败！");
			e.printStackTrace();
			return "reservation/reserveResult";
		}

		return "reservation/reserveResult";
	}

	/**
	 * 根据表单输入的数据进行查询，表单在[appointedComponents.html]
	 * 
	 * @param model:         视图
	 * @param componentType: 零件类型,"all"表示全部
	 * @param activityId:    活动场次的id,"-1"表示全部
	 * @param state:         预约的状态 ,0: 未受理,1: 已受理未完成,2: 已完成,3: 全部
	 * @return 过滤后的视图
	 */
	@RequestMapping("/reservation/componentSearch")
	public String componentSearch(Model model,
			@RequestParam(value = "componentType", required = false, defaultValue = "all") String componentType,
			@RequestParam(value = "activityId", required = false, defaultValue = "-1") Integer activityId,
			@RequestParam(value = "state", required = false, defaultValue = "3") Integer state) {
		int ALL_STATE = 3, ALL_ACTIVITY = -1, DOOR_ACTIVITY = -2;
		List<Reservation> reservations = reservationService.getReservationList();
		List<Reservation> filteredList = new ArrayList<Reservation>();
		// 根据条件过滤
		for (Reservation reservation : reservations) {
			if(activityId != ALL_ACTIVITY) {
				if (activityId == DOOR_ACTIVITY&&reservation.getActivity()!=null) continue;
				else if (activityId == DOOR_ACTIVITY && reservation.getActivity() == null) {}
				else {
					if (reservation.getActivity()==null) continue;
					if (reservation.getActivity().getId() != activityId) continue;
				}
			}
			if (state != ALL_STATE && reservation.getState() != state)
				continue;
			if ("all".equals(componentType)) {
				filteredList.add(reservation);
				continue;
			}
			List<Component> filteredComponents = reservation.getComponentList().stream()
					.filter(c -> componentType.equals(c.getName())).collect(Collectors.toList());
			reservation.setComponentList(filteredComponents);
			filteredList.add(reservation);
		}
		// 计算总数
		int componentNum = 0,personNum=0;
		for (int i = 0; i < filteredList.size(); i++) {
			Reservation r = filteredList.get(i);
			componentNum+=r.getComponentList().size();
			personNum++;
		}
		model.addAttribute("componentNum", componentNum);
		model.addAttribute("personNum", personNum);
		model.addAttribute("viewComponents", filteredList);
		// 设置过滤条件的显示
		String activityFilter;
		if (activityId ==ALL_ACTIVITY )  activityFilter = "全部";
		else if (activityId ==DOOR_ACTIVITY )  activityFilter = "上门服务";
		else {
			activityFilter= activityService.findActivityById(activityId).getPlace();
		}
		String stateFilter = state==0?"已受理":(state==1?"已受理未完成":(state==2?"已完成":"全部"));
		String componentFilter = componentType;
		model.addAttribute("activityFilter", activityFilter);
		model.addAttribute("stateFilter", stateFilter);
		model.addAttribute("componentFilter", componentFilter);
		return "/reservation/appointmentComponentSearch";
	}

	/**
	 * 返回预约人员的视图
	 * 
	 * @param model: 视图
	 * @return
	 */
	@RequestMapping("/reservation/appointmentHome")
	public String appointmentHome(Model model) {
		List<Activity> activities = activityService.getActivityList();
		model.addAttribute("activities", activities);
		return "/reservation/appointmentHome";
	}

	/**
	 * 返回预约零件的视图
	 * 
	 * @param model: 视图
	 * @return
	 */
	@RequestMapping("/reservation/appointedComponents")
	public String appointedComponents(Model model) {
		List<Activity> activities = activityService.getActivityList();
		model.addAttribute("activities", activities);
		model.addAttribute("components", componentService.getComponentList());
		return "/reservation/appointedComponents";
	}
}
