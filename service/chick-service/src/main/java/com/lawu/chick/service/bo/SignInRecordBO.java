package com.lawu.chick.service.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/4/27.
 */
public class SignInRecordBO {

    private Long id;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
