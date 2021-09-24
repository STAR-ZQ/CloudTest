package com.zq.stream.test;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RedisDto {
    private BigDecimal ot;
    private Long pulse;
    private BigDecimal ef;
    private BigDecimal avgSpeed;
    private Integer stopNum;
    private Long stopTime;
    private String speedSP;
    private Long upTimePulse;
    private BigDecimal upTimeOt;
    private Long onTime;
    private Long offTime;
    private Long time;
    private List<ProduceRealDTO> allDTO;
}
