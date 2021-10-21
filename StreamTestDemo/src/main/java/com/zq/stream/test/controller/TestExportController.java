package com.zq.stream.test.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.google.common.collect.Lists;
import com.zq.stream.dto.TestExportDTO;
import com.zq.stream.dto.TestExportDetailDTO;
import com.zq.stream.test.context.StrategyContext;
import com.zq.stream.test.util.ExcelExportUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@RestController
public class TestExportController {
    @Autowired
    private StrategyContext context;

    @RequestMapping("/testMethod")
    public String testMethod(String code) {
        System.out.println("ces=====");
        return context.testMethod(code).testMethod();
    }

    @PostMapping("/testExport")
    public void test(HttpServletResponse response) {
        try {
            // 设置响应输出的头类型
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 设置下载的Excel名称，以当前时间为文件后缀，DateUtil为Hutool工具包里的工具类
            String fileName = "user" + System.currentTimeMillis() + ".xls";
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            // excel信息部分
            List<TestExportDTO> exportList = new LinkedList<>();
            //此处为了演示，手动输入的数据，没有从数据库查数据，实际开发中从数据库中查数据
            //创建ReportInfoExcel中List<StaffExcel>对象
            TestExportDTO dto = new TestExportDTO();
            dto.setNum(BigDecimal.valueOf(11l));
            TestExportDetailDTO detailDTO = new TestExportDetailDTO();
            detailDTO.setUserName("张三");
            detailDTO.setNowTime(new Date());
            detailDTO.setWeight(BigDecimal.valueOf(98l));
            TestExportDetailDTO detailDTO1 = new TestExportDetailDTO();
            detailDTO1.setUserName("张三1");
            detailDTO1.setNowTime(new Date());
            detailDTO1.setWeight(BigDecimal.valueOf(18l));
            TestExportDetailDTO detailDTO2 = new TestExportDetailDTO();
            detailDTO2.setUserName("张三1");
            detailDTO2.setNowTime(new Date());
            detailDTO2.setWeight(BigDecimal.valueOf(18l));
            List<TestExportDetailDTO> detailDTOS = Lists.newArrayList();
            detailDTOS.add(detailDTO);
            detailDTOS.add(detailDTO1);
            detailDTOS.add(detailDTO2);
            dto.setDetailList(detailDTOS);

            TestExportDTO dto1 = new TestExportDTO();
            dto1.setNum(BigDecimal.TEN);
            dto1.setDetailList(detailDTOS);
            exportList.add(dto);
            exportList.add(dto1);

            ExportParams exportParams = new ExportParams();
            exportParams.setAddIndex(true);
            // 设置sheet得名称
            exportParams.setSheetName("员工报表1");

            Map<String, Object> map = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            map.put("title", exportParams);
            // 模版导出对应得实体类型，即包含了List的对象
            map.put("entity", TestExportDTO.class);
            // sheet中要填充得数据
            map.put("data", exportList);

            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(map);
            //创建excel文件的方法
            Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
            //通过response输出流直接输入给客户端
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
