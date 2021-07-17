package cn.zq.cloudproviderpayment8001.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Payment implements Serializable {
    public Long id;
    private String serial;
    private String type;

    public Payment() {
        System.out.println("无参构造");
    }

    public void show() {
        System.out.println("测试反射无参Show()");
    }

    public void show(int i) {
        System.out.println("测试反射有参Show()：" + i);
    }
}
