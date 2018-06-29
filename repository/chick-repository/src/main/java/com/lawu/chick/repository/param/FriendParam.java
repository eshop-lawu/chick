package com.lawu.chick.repository.param;


import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class FriendParam extends AbstractPageParam {

    private String memberNum;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }
}
