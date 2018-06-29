package com.lawu.chick.service.param;

import java.util.Date;

import com.lawu.chick.service.enums.RedpacketSendRecordStatusEnum;


/**
 * @author meishuquan
 * @date 2018/4/27.
 */
public class RedpacketSendRecordParam {

    private Long id;

    private Long eggExchangeRecordId;

    private String userNum;

    private String mchBillno;

    private Integer totalAmount;

    private RedpacketSendRecordStatusEnum statusEnum;

    private Date rcvTime;

    private Date refundTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEggExchangeRecordId() {
        return eggExchangeRecordId;
    }

    public void setEggExchangeRecordId(Long eggExchangeRecordId) {
        this.eggExchangeRecordId = eggExchangeRecordId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public RedpacketSendRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(RedpacketSendRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Date getRcvTime() {
        return rcvTime;
    }

    public void setRcvTime(Date rcvTime) {
        this.rcvTime = rcvTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
}
