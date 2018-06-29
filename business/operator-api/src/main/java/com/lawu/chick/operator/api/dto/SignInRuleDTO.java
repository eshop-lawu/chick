package com.lawu.chick.operator.api.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.chick.service.enums.StatusEnum;

/**
 * @author zhangyong
 * @date 2018/4/26.
 */
public class SignInRuleDTO {
    private String productNum;

    private String productName;

    private List<SignRuleExtraDTO> extras;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtStart;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtEnd;

    private Long id;

    private StatusEnum status;

    private List<SignProductListDTO> products;

    private Integer productCount;

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SignRuleExtraDTO> getExtras() {
        return extras;
    }

    public void setExtras(List<SignRuleExtraDTO> extras) {
        this.extras = extras;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<SignProductListDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SignProductListDTO> products) {
        this.products = products;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
