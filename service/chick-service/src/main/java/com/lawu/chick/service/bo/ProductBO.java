package com.lawu.chick.service.bo;

import java.math.BigDecimal;

import com.lawu.chick.service.enums.ProductStatusEnum;
import com.lawu.chick.service.enums.ProductTypeEnum;

/**
 * 商品BO
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
public class ProductBO {
    
    /**
     * 主键
     */
    private Long id;

    /**
     * 商品编号
     */
    private String num;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 商品介绍
     */
    private String intro;

    /**
     * 价格
     */
    private BigDecimal price;
    
    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 愉悦值
     */
    private Integer joyfulVal;

    /**
     * 愉悦值增加次数上限(0为不限制)
     */
    private Integer joyfulLimit;

    /**
     * 成长值
     */
    private Integer growthVal;

    /**
     * 成长值增加次数上限(0为不限制)
     */
    private Integer growthLimit;

    /**
     * 饱腹值
     */
    private Integer fullVal;

    /**
     * 饱腹值增加次数上限(0为不限制)
     */
    private Integer fullLimit;
    
    /**
     * 保持清洁度时间（单位：天）
     */
    private Integer keepCleanTime;

    /**
     * 清洁保持时间增加次数上限(0为不限制)
     */
    private Integer cleanLimit;
    
    /**
     * 是否在商店显示
     */
    private Boolean show;
    
    /**
     * 类型
     */
    private ProductTypeEnum type;

    /**
     * 状态
     */
    private ProductStatusEnum status;

    private Integer dayUsageLimit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getJoyfulVal() {
        return joyfulVal;
    }

    public void setJoyfulVal(Integer joyfulVal) {
        this.joyfulVal = joyfulVal;
    }
    
    public Integer getJoyfulLimit() {
        return joyfulLimit;
    }

    public void setJoyfulLimit(Integer joyfulLimit) {
        this.joyfulLimit = joyfulLimit;
    }

    public Integer getGrowthVal() {
        return growthVal;
    }

    public void setGrowthVal(Integer growthVal) {
        this.growthVal = growthVal;
    }
    
    public Integer getGrowthLimit() {
        return growthLimit;
    }

    public void setGrowthLimit(Integer growthLimit) {
        this.growthLimit = growthLimit;
    }

    public Integer getFullVal() {
        return fullVal;
    }

    public void setFullVal(Integer fullVal) {
        this.fullVal = fullVal;
    }
    
    public Integer getFullLimit() {
        return fullLimit;
    }

    public void setFullLimit(Integer fullLimit) {
        this.fullLimit = fullLimit;
    }
    
    public Integer getKeepCleanTime() {
        return keepCleanTime;
    }

    public void setKeepCleanTime(Integer keepCleanTime) {
        this.keepCleanTime = keepCleanTime;
    }

    public Integer getCleanLimit() {
        return cleanLimit;
    }

    public void setCleanLimit(Integer cleanLimit) {
        this.cleanLimit = cleanLimit;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }

    public Integer getDayUsageLimit() {
        return dayUsageLimit;
    }

    public void setDayUsageLimit(Integer dayUsageLimit) {
        this.dayUsageLimit = dayUsageLimit;
    }
}