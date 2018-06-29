package com.lawu.chick.repository.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class ChickenInHouseDOView implements Serializable{
    private static final long serialVersionUID = 2601130682410124142L;

    private Long id;

    private String memberNum;

    private  String num;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
