package com.lawu.chick.repository.param;

/**
 * 处理缓存中小鸡饱腹值递减
 * @author lihj
 * @date 2018/4/27
 */
public class ChickDealWithParam {
    /**
     * 小鸡编号
     */
    private String num;

    /**
     * 用户编号
     */
    private String memberNum;

    /**
     * 限制值(最小或最大)
     */
    private int limitVal;
    /**
     * 值
     */
    private int val;
    /**
     * 饥饿饱腹值
     */
    private int hungerVal;
    
    private int minFull;
    
    //private int status;
    
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getLimitVal() {
        return limitVal;
    }

    public void setLimitVal(int limitVal) {
        this.limitVal = limitVal;
    }

	public int getMinFull() {
		return minFull;
	}

	public void setMinFull(int minFull) {
		this.minFull = minFull;
	}

	public int getHungerVal() {
		return hungerVal;
	}

	public void setHungerVal(int hungerVal) {
		this.hungerVal = hungerVal;
	}
    
}
