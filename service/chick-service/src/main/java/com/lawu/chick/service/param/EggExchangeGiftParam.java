package com.lawu.chick.service.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeGiftParam {

    @ApiModelProperty(value = "礼品id", required = true)
    private Long giftId;

    @ApiModelProperty(value = "收货人姓名", required = false)
    private String name;

    @ApiModelProperty(value = "收货人手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "收货人地址", required = false)
    private String address;

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
