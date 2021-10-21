package com.zq.stream.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author zhangqing
 * @description
 * @Date 2021/10/9 15:11
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class TestExportDTO {
    @Excel(name = "测试", width = 20)
    private BigDecimal num;

    @ExcelCollection(name = "")
    private List<TestExportDetailDTO> detailList;

}
