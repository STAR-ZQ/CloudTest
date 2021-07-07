package com.zq.stream;

import java.io.Serializable;

public class ListDto implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Long sameId;

    @Override
    public String toString() {
        return "ListDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", sameId=" + sameId +
                '}';
    }

    @Transactional(name = "zhangsan", age = 12)
    public void methodTest(String name){
        System.out.println("测试反射方法："+name);
    }

    public ListDto() {
        System.out.println("ListDto无参构造方法");
    }

    public ListDto(String name, Integer age) {
        System.out.println("有参构造方法名称：" + name + "\t年龄：" + age);
    }

    public Long getSameId() {
        return sameId;
    }

    public void setSameId(Long sameId) {
        this.sameId = sameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
