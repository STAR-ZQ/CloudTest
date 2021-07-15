package cn.zq.cloudproviderpayment8001.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
