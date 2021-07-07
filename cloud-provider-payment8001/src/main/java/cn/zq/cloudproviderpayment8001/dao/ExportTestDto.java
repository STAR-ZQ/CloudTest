package cn.zq.cloudproviderpayment8001.dao;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ExportTestDto {
    @Excel(name = "id", orderNum = "0")
    private Integer id;
    @Excel(name = "name", orderNum = "1")
    private String name;

    public ExportTestDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
