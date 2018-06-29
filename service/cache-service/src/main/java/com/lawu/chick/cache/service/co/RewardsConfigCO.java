package com.lawu.chick.cache.service.co;

import java.io.Serializable;

/**
 * @author meishuquan
 * @date 2018/6/15.
 */
public class RewardsConfigCO implements Serializable {

    private static final long serialVersionUID = 832152972980090660L;

    private String productNum;

    private Integer productCount;

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
