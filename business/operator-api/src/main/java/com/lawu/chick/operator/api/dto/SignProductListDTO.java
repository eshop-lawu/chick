package com.lawu.chick.operator.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public class SignProductListDTO {
    @ApiModelProperty(value = "商品编号")
    private String num;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
