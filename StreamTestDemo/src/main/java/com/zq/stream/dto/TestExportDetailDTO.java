package com.zq.stream.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangqing
 * @description
 * @Date 2021/10/9 15:11
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@ExcelTarget("TestExportDTO")
public class TestExportDetailDTO {
    @Excel(name = "细码", width = 20)
    private Integer chica;
    @Excel(name = "姓名", width = 20)
    private String userName;
    @Excel(name = "重量", width = 20)
    private BigDecimal weight;
    @Excel(name = "时间", exportFormat = "yyyy-MM-dd", width = 20)
    private Date nowTime;
}
