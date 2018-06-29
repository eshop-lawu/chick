package com.lawu.chick.operator.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.chick.service.enums.EggExchangeRecordStatusEnum;
import com.lawu.chick.service.enums.EggExchangeRecordTypeEnum;
import com.lawu.chick.service.enums.GiftTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/26.
 */
public class EggExchangeRecordDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "兑换编号")
    private String num;

    @ApiModelProperty(value = "兑换类型：REDPACKET--红包，GIFT--礼品")
    private EggExchangeRecordTypeEnum typeEnum;

    @ApiModelProperty(value = "兑换鸡蛋数量")
    private BigDecimal eggQuantity;

    @ApiModelProperty(value = "兑换金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "状态：PENDING--待处理，FAIL--发放失败，SENT--已发放，RECEIVED--已到账，REFUND--已退款")
    private EggExchangeRecordStatusEnum statusEnum;

    @ApiModelProperty(value = "收货人")
    private String name;

    @ApiModelProperty(value = "收货手机号")
    private String mobile;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "物流单号")
    private String expressNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "发放时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtSend;

    @ApiModelProperty(value = "兑换时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "类型描述")
    private String typeDes;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    @ApiModelProperty(value = "礼品类型：GOODS--实物，VIRTUAL_GOODS--虚拟物品")
    private GiftTypeEnum giftTypeEnum;

    @ApiModelProperty(value = "礼品类型描述")
    private String giftTypeDes;

    @ApiModelProperty(value = "礼品名称")
    private String giftName;

    @ApiModelProperty(value = "礼品图片")
    private String giftImgPath;

    @ApiModelProperty(value = "用户编号")
    private String memberNum;

    @ApiModelProperty(value = "微信昵称")
    private String wxNickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public EggExchangeRecordTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(EggExchangeRecordTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigDecimal getEggQuantity() {
        return eggQuantity;
    }

    public void setEggQuantity(BigDecimal eggQuantity) {
        this.eggQuantity = eggQuantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public EggExchangeRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(EggExchangeRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
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

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtSend() {
        return gmtSend;
    }

    public void setGmtSend(Date gmtSend) {
        this.gmtSend = gmtSend;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public GiftTypeEnum getGiftTypeEnum() {
        return giftTypeEnum;
    }

    public void setGiftTypeEnum(GiftTypeEnum giftTypeEnum) {
        this.giftTypeEnum = giftTypeEnum;
    }

    public String getGiftTypeDes() {
        return giftTypeDes;
    }

    public void setGiftTypeDes(String giftTypeDes) {
        this.giftTypeDes = giftTypeDes;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftImgPath() {
        return giftImgPath;
    }

    public void setGiftImgPath(String giftImgPath) {
        this.giftImgPath = giftImgPath;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName;
    }
}
