package com.xiuxiuxiu.web;

import cn.afterturn.easypoi.excel.ExcelImportUtil;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import cn.afterturn.easypoi.util.PoiPublicUtil;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xiuxiuxiu.model.DemoExcel;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.web.ExcelUtiles;

import com.xiuxiuxiu.service.ReservationService;
import com.xiuxiuxiu.service.StudentService;
import com.xiuxiuxiu.web.ExcelUtiles;

@RestController
@RequestMapping("/Excel")
public class ExcelController {

	@Autowired
	private ReservationService reservationService;
	@Resource
	StudentService studentService;
	@GetMapping("/export")
	public void export(HttpServletResponse response) {
		System.out.println(1);
//        模拟从数据库获取需要导出的数据
//        List<ReservationList> resList = reservationService.getReservationList();
		List<DemoExcel> personList = new ArrayList<DemoExcel>();
		List<Reservation> reservationList = reservationService.getReservationList();

		for (Reservation reservation : reservationList) {
			personList.add(new DemoExcel(reservation));
		}
//         导出操作
		ExcelUtiles.exportExcel(personList, "预约人员名单", "什么名字", DemoExcel.class, "预约人员名单.xls", response);
//        ExcelUtiles.exportExcel(resList, "测试名", "什么名字", ReservationList.class, "测试.xls", response);

	}

	@PostMapping("/importExcel2")
	public void importExcel2(@RequestParam("file")MultipartFile file,HttpServletRequest request) {
		Student student = new Student();
		Reservation reservation = new Reservation();
		ImportParams importParams = new ImportParams();
		// 数据处理
		importParams.setHeadRows(1);
		importParams.setTitleRows(1);

		// 需要验证
		importParams.setNeedVerfiy(true);
//		new File("D:\\Users\\masgak\\Desktop\\预约人员名单.xls")
		try {
			ExcelImportResult<DemoExcel> result = ExcelImportUtil.importExcelMore(file.getInputStream(),
					DemoExcel.class, importParams);

			List<DemoExcel> successList = result.getList();
			for (DemoExcel demoExcel : successList) {
				//如果该手机号在数据库中
//				if(studentService.findStudentByPhoneNumber(demoExcel.getPhoneNum()).getName().equals(demoExcel.getName())) {
//					studentService.save(student);
//				}else {
				student.setName(demoExcel.getName());
				student.setAddress(demoExcel.getAddress());
				student.setEmail("");
				student.setPassword("import");
				student.setPhoneNumber(demoExcel.getPhoneNum());
				student.setAccessLevel(0);
				studentService.edit(student);
				reservation.setStudent(student);
				reservation.setPlace(demoExcel.getAddress());
				reservation.setRequiredTime(demoExcel.getAppointments());
				reservation.setState(demoExcel.getRepairState());
				reservation.setApplicationTime("");
				reservationService.edit(reservation);
			}
		} catch (Exception e) {
		}
	}
//	@PostMapping("excelImport.do")
//	public void excelImport(@RequestParam("file") MultipartFile file) {
//		ImportParams importParams = new ImportParams();
//		// 数据处理
//		IExcelDataHandler<DemoExcel> handler = new UserExcelHandler();
//		handler.setNeedHandlerFields(new String[] { "姓名" });// 注意这里对应的是excel的列名。也就是对象上指定的列名。
//		importParams.setDataHanlder(handler);
//
//		// 需要验证
//		importParams.setNeedVerfiy(true);
//
//		try {
//			ExcelImportResult<DemoExcel> result = ExcelImportUtil.importExcelMore(file.getInputStream(), DemoExcel.class,
//					importParams);
//
//			List<DemoExcel> successList = result.getList();
//			List<DemoExcel> failList = result.getFailList();
//
//			log.info("是否存在验证未通过的数据:" + result.isVerfiyFail());
//			log.info("验证通过的数量:" + successList.size());
//			log.info("验证未通过的数量:" + failList.size());
//
//			for (DemoExcel user : successList) {
//				log.info("成功列表信息:ID=" + user.getId() + user.getName() + "-"
//						+ new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
//			}
//			for (DemoExcel user : failList) {
//				log.info("失败列表信息:" + user.getName());
//			}
//		} catch (IOException e) {
//			log.error(e.getMessage(), e);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
//	}
}
