package com.lawu.chick.service.bo;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public class SignRecordListBO {


    private Date gmtStart;

    private Date gmtEnd;

    private String productNum;

    private String extras;

    private List<SignInRecordBO> recordBOS;

    private Integer count;

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public List<SignInRecordBO> getRecordBOS() {
        return recordBOS;
    }

    public void setRecordBOS(List<SignInRecordBO> recordBOS) {
        this.recordBOS = recordBOS;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
