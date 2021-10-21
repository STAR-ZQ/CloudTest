package com.zq.stream.dto;

/**
 * @Author zhangqing
 * @description
 * @Date 2021/10/12 15:25
 * @Version 1.0
 */

import java.io.Serializable;
import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 学生类
 * @author mayi1203
 * @date 2020年5月8日
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 2131321500629905052L;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "学生年龄")
    private Integer age;

    @Excel(name = "语文成绩")
    private BigDecimal chineseScore;

    @Excel(name = "数学成绩")
    private BigDecimal mathScore;

}