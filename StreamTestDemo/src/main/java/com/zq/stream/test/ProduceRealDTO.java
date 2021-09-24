package com.zq.stream.test;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProduceRealDTO {
    private String fabId;
    private String fabChgId;
    private Integer proPrice;
    private BigDecimal fabOt;
    private Long pulse;
}
