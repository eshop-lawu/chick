package com.lawu.chick.service.bo;

import com.lawu.chick.service.enums.ProductTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月25日
 */
public class InventoryBO {
	
	/**
	 * 库存id
	 */
	private Long id;
	
	/**
	 * 商品图片
	 */
	private String productImg;
	
	/**
	 * 商品介绍
	 */
	private String intro;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品数量
	 */
	private Integer quantity;

	@ApiModelProperty(value = "饱腹值")
	private Integer fullVal;
	@ApiModelProperty(value = "饱腹值是否首次使用有效")
	private Boolean isFullFirst;
	@ApiModelProperty(value = "成长值")
	private Integer growthVal;
	@ApiModelProperty(value = "成长值是否首次使用有效")
	private Boolean isGrowthFirst;
	@ApiModelProperty(value = "愉悦值")
	private Integer joyfulVal;
	@ApiModelProperty(value = "愉悦值是否首次使用有效")
	private Boolean isJoyfulFirst;
	
	private ProductTypeEnum type;
	
	/**
	 * @return the type
	 */
	public ProductTypeEnum getType() {
		return type;
	}
	public void setType(ProductTypeEnum type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getFullVal() {
		return fullVal;
	}
	public void setFullVal(Integer fullVal) {
		this.fullVal = fullVal;
	}
	public Boolean getIsFullFirst() {
		return isFullFirst;
	}
	public void setIsFullFirst(Boolean isFullFirst) {
		this.isFullFirst = isFullFirst;
	}
	public Integer getGrowthVal() {
		return growthVal;
	}
	public void setGrowthVal(Integer growthVal) {
		this.growthVal = growthVal;
	}
	public Boolean getIsGrowthFirst() {
		return isGrowthFirst;
	}
	public void setIsGrowthFirst(Boolean isGrowthFirst) {
		this.isGrowthFirst = isGrowthFirst;
	}
	public Integer getJoyfulVal() {
		return joyfulVal;
	}
	public void setJoyfulVal(Integer joyfulVal) {
		this.joyfulVal = joyfulVal;
	}
	public Boolean getIsJoyfulFirst() {
		return isJoyfulFirst;
	}
	public void setIsJoyfulFirst(Boolean isJoyfulFirst) {
		this.isJoyfulFirst = isJoyfulFirst;
	}
	
}
