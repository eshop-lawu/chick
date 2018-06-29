package com.lawu.chick.service.param;

/**
 * 商品订单购买参数
 * 
 * @author jiangxinjun
 * @createDate 2018年4月27日
 * @updateDate 2018年4月27日
 */
public class ProductOrderBuyParam {

    /**
     * 商品编号
     */
    private String productNum;

    /**
     * 用户编号
     */
    private String memberNum;

    /**
     * 数量
     */
    private Integer quantity;
    
    /**
     * 用户下单时的ip地址
     */
    private String spbillCreateIp;
    
    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }
    
}
