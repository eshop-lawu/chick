package com.lawu.chick.service.param;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public class SignExtraParam {
    private String userNum;

    private Date gmtStart;

    private Date gmtEnd;

    private Integer signDay;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

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

    public Integer getSignDay() {
        return signDay;
    }

    public void setSignDay(Integer signDay) {
        this.signDay = signDay;
    }
}
