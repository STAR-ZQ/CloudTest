package com.zq.stream.test.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.google.common.collect.Lists;
import com.zq.stream.dto.TestExportDTO;
import com.zq.stream.dto.TestExportDetailDTO;
import com.zq.stream.test.utils.ExcelExportUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.collections.Maps;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/excel")
public class ExcelTitleCustomController {
//    public static void main(String[] args) {
//        // 表头定义 可以将表头配置在数据库中，然后在代码里动态生成表头
//        // 这里只是展示如何用代码生成表头
//        List<ExcelExportEntity> columnList = new ArrayList<ExcelExportEntity>();
//        ExcelExportEntity colEntity1 = new ExcelExportEntity("序号", "id");
//        colEntity1.setNeedMerge(true);
//        columnList.add(colEntity1);
//
//        ExcelExportEntity colEntity2 = new ExcelExportEntity("班级", "class");
//        colEntity2.setNeedMerge(true);
//        columnList.add(colEntity2);
//
//        ExcelExportEntity yhxxGroup = new ExcelExportEntity("用户信息", "yhxx");
//        List<ExcelExportEntity> yyxxList = new ArrayList<ExcelExportEntity>();
//        yyxxList.add(new ExcelExportEntity("姓名", "name"));
//        yyxxList.add(new ExcelExportEntity("年龄", "age"));
//        yyxxList.add(new ExcelExportEntity("性别", "sex"));
//        yhxxGroup.setList(yyxxList);
//        columnList.add(yhxxGroup);
//
//        // 数据拉取 一般需要从数据库中拉取
//        // 这里是手动模拟数据
//        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < 10; i++) {
//            Map<String, Object> values = new HashMap<>();
//            values.put("id", i);
//            values.put("class", "班级" + i);
//
//            List<Map<String, Object>> yhxxList = new ArrayList<Map<String, Object>>();
//            Map<String, Object> yhxxMap = new HashMap<String, Object>();
//            yhxxMap.put("name", "姓名" + i);
//            yhxxMap.put("age", "年龄" + i);
//            yhxxMap.put("sex", "性别" + i);
//            yhxxList.add(yhxxMap);
//
//            values.put("yhxx", yhxxList);
//            dataList.add(values);
//        }
//
//        // 定义标题和sheet名称
//        ExportParams exportParams = new ExportParams("班级信息", "人员数据");
//        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, columnList, dataList);
//        // 导入到本地目录，如果需要从浏览器导出，参看上一篇文章
//        FileOutputStream fos  = null;
//        try {
//            fos = new FileOutputStream("C:\\Users/mi/班级信息.xls");
//            workbook.write(fos);
//            workbook.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @PostMapping("/test")
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

            List<ExcelExportEntity> columnList = new ArrayList<ExcelExportEntity>();
            ExcelExportEntity colEntity1 = new ExcelExportEntity("序号", "id");
            colEntity1.setNeedMerge(true);
            columnList.add(colEntity1);

            ExcelExportEntity colEntity2 = new ExcelExportEntity("班级", "class");
            colEntity2.setNeedMerge(true);
            columnList.add(colEntity2);

            ExcelExportEntity yhxxGroup = new ExcelExportEntity("用户信息", "yhxx");
            List<ExcelExportEntity> yyxxList = new ArrayList<ExcelExportEntity>();
            yyxxList.add(new ExcelExportEntity("姓名", "name"));
            yyxxList.add(new ExcelExportEntity("年龄", "age"));
            yyxxList.add(new ExcelExportEntity("性别", "sex"));
            yhxxGroup.setList(yyxxList);
            columnList.add(yhxxGroup);

            ExportParams exportParams = new ExportParams();
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

    @PostMapping("/test1")
    public void test1(HttpServletResponse response) {
        try {
            // 设置响应输出的头类型
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 设置下载的Excel名称，以当前时间为文件后缀，DateUtil为Hutool工具包里的工具类
            String fileName = "user" + System.currentTimeMillis() + ".xls";
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            // excel信息部分
            List<TestExportDTO> exportList = new LinkedList<>();
            //此处为了演示，手动输入的数据，没有从数据库查数据，实际开发中从数据库中查数据
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
            // 设置sheet得名称
            exportParams.setSheetName("员工报表1");

            //key:原始列头   value：修改后的列头
            Map<String,String> ignoreMap = Maps.newHashMap();
            ignoreMap.put("测试","测试1");
            ignoreMap.put("细码","细码");
            ignoreMap.put("重量","重量");


            Map<String, Object> map = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            map.put("title", exportParams);
            // 模版导出对应得实体类型，即包含了List的对象
            map.put("entity", TestExportDTO.class);
            // sheet中要填充得数据
            map.put("data", exportList);
            //展示的列头
            map.put("ignore", ignoreMap);

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
