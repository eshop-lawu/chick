package com.lawu.chick.service.bo;

/**
 * @author zhangyong
 * @date 2018/6/4.
 */
public class ChickenCleanessBO {

    private Long id;

    private Integer cleannessVal;

    private String memberNum;

    private  String num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCleannessVal() {
        return cleannessVal;
    }

    public void setCleannessVal(Integer cleannessVal) {
        this.cleannessVal = cleannessVal;
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
