package com.zq.stream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HSSFWorkbookTest {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("cell types");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        HSSFRow row = spreadsheet.createRow((short) 0);
        row.createCell(0).setCellValue("采购编号");
        row.createCell(1).setCellValue("采购周期");
        row.createCell(2).setCellValue("订单金额");
        row.createCell(3).setCellValue("实付金额");
        row.createCell(4).setCellValue("提交日期");
        row.createCell(5).setCellValue("预计到货日期");
        row.createCell(6).setCellValue("状态");
        row.createCell(7).setCellValue("EAS状态");
        row.createCell(8).setCellValue("所属店铺");
        row.createCell(9).setCellValue("所属供应商");
        row.createCell(10).setCellValue("确认收货日期");
        row.createCell(11).setCellValue("是否使用返利");
        row.createCell(12).setCellValue("生成方式");
        row.createCell(13).setCellValue("单据创建人");
        row.createCell(14).setCellValue("备注");
      for (int i = 1 ;i<=14;i++){
          row = spreadsheet.createRow((short) i);
          row.createCell(0).setCellValue(1);
          row.createCell(1).setCellValue(2);
          row.createCell(2).setCellValue(1);
          row.createCell(3).setCellValue(2);
          row.createCell(4).setCellValue(1);
          row.createCell(5).setCellValue(2);
          row.createCell(6).setCellValue(1);
          row.createCell(7).setCellValue(2);
          row.createCell(8).setCellValue(1);
          row.createCell(9).setCellValue(2);
          row.createCell(10).setCellValue(1);
          row.createCell(11).setCellValue(2);
          row.createCell(12).setCellValue(1);
          row.createCell(13).setCellValue(2);
          row.createCell(14).setCellValue(2);
      }
        spreadsheet.addMergedRegion(new CellRangeAddress(list.size()+1, list.size()+1, 12, 14));
        HSSFRow row1 = spreadsheet.createRow(list.size() + 1);
//        XSSFCell cell =row1.createCell(11);
//        cell.setCellValue("合计:");
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        row1.createCell(11).setCellValue("合计：");
        row1.createCell(12).setCellValue("订单总额："+sum);
//        HSSFRow row2 = spreadsheet.createRow(list.size() + 2);
////        XSSFCell cell2 =row2.createCell(12);
//        HSSFRow row3 = spreadsheet.createRow(list.size() + 3);
//        XSSFCell cell3 =row3.createCell(13);
//        spreadsheet.addMergedRegion(new CellRangeAddress(list.size()+2, list.size()+2, 12, 14));
//        spreadsheet.addMergedRegion(new CellRangeAddress(list.size()+3, list.size()+3, 12, 14));

        FileOutputStream out = new FileOutputStream(new File("typesofcells.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("typesofcells.xlsx written successfully");
    }
}
