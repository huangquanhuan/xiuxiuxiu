package com.xiuxiuxiu.service.impl;

import java.util.List;

import com.xiuxiuxiu.model.DemoExcel;
import com.xiuxiuxiu.repository.DemoExcelRepository;
import com.xiuxiuxiu.service.DemoExcelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DemoExcelServicempl implements DemoExcelService{
	
	private DemoExcelRepository demoExcelRepository;
    public List<DemoExcel> getDemoExcelList() {
		return demoExcelRepository.findAll();
       
    }
}
