package com.lawu.chick.service.param;

import java.math.BigDecimal;

import com.lawu.chick.service.enums.ProductTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品保存参数
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@ApiModel(description = "商品保存参数")
public class ProductSaveParam {
    
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径", required = true)
    private String imgPath;

    /**
     * 商品介绍
     */
    @ApiModelProperty(value = "商品介绍", required = true)
    private String intro;
    
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价", required = true)
    private BigDecimal originalPrice;
    
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;
    
    /**
     * 愉悦值
     */
    @ApiModelProperty(value = "愉悦值", required = true)
    private Integer joyfulVal;

    /**
     * 愉悦值增加次数上限(0为不限制)
     */
    @ApiModelProperty(value = "愉悦值增加次数上限(0为不限制)", required = true)
    private Integer joyfulLimit;

    /**
     * 成长值
     */
    @ApiModelProperty(value = "成长值", required = true)
    private Integer growthVal;

    /**
     * 成长值增加次数上限(0为不限制)
     */
    @ApiModelProperty(value = "成长值增加次数上限(0为不限制)", required = true)
    private Integer growthLimit;

    /**
     * 饱腹值
     */
    @ApiModelProperty(value = "饱腹值", required = true)
    private Integer fullVal;

    /**
     * 饱腹值增加次数上限(0为不限制)
     */
    @ApiModelProperty(value = "饱腹值增加次数上限(0为不限制)", required = true)
    private Integer fullLimit;
    
    /**
     * 保持清洁度时间（单位：天）
     */
    @ApiModelProperty(value = "保持清洁度时间（单位：天）", required = true)
    private Integer keepCleanTime;

    /**
     * 清洁保持时间增加次数上限(0为不限制)
     */
    @ApiModelProperty(value = "清洁保持时间增加次数上限(0为不限制)", required = true)
    private Integer cleanLimit;
    
    /**
     * 是否在商店显示
     */
    @ApiModelProperty(value = "是否在商店显示", required = true)
    private Boolean show;
    
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", required = true, notes = "CHICK-小鸡|CHICKEN_PROPS-小鸡道具")
    private ProductTypeEnum type;
    
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

}