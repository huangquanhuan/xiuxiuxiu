package com.xiuxiuxiu.web;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.xiuxiuxiu.model.DemoExcel;
import com.xiuxiuxiu.repository.DemoExcelRepository;
import com.xiuxiuxiu.service.DemoExcelService;
import com.xiuxiuxiu.web.ExcelUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
 
@RestController
@RequestMapping("/Excel")
public class ExcelController {
 
    @Autowired
    private DemoExcelRepository demoExcelRepository;
 
    @Autowired
    private DemoExcelService demoService;
 
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        System.out.println(1);
//        模拟从数据库获取需要导出的数据
        List<DemoExcel> personList = demoExcelRepository.findAll();
//         导出操作
        ExcelUtiles.exportExcel(personList, "测试名", "什么名字", DemoExcel.class, "测试.xls", response);
 
    }
 
    @PostMapping("/importExcel2")
    public void importExcel2(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
 
        // 需要验证
        importParams.setNeedVerfiy(true);
 
        try {
            ExcelImportResult<DemoExcel> result = ExcelImportUtil.importExcelMore(file.getInputStream(), DemoExcel.class,
                    importParams);
 
            List<DemoExcel> successList = result.getList();
            for (DemoExcel demoExcel : successList) {
              System.out.println(demoExcel);
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }
}