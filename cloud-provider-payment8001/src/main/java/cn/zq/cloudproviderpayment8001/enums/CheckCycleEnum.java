package cn.zq.cloudproviderpayment8001.enums;

public enum CheckCycleEnum {
    MONTH(1, "月度"),
    QUARTER(2, "季度"),
    YEAR(3, "年度")
    ;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    CheckCycleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
