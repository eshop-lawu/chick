package com.lawu.chick.service.bo;

/**
 * 仓库库存货品
 * @author lihj <br>
 * @date 2018年4月27日<br>
 *
 */
public class InventoryInfoBO {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 商品编号
	 */
	private String productNum;

	/**
	 * 所属用户编号
	 */
	private String memberNum;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 类型，0小鸡，1小鸡道具
	 */
	private Byte type;

	/**
	 * 愉悦值
	 */
	private Integer joyfulVal;

	/**
	 * 愉悦值是否首次使用生效
	 */
//	private Boolean isJoyfulFirst;
	/**
	 * 愉悦值增加次数上限(0为不限制)
	 */
	private Integer joyfulLimit;

	/**
	 * 成长值
	 */
	private Integer growthVal;

	/**
	 * 成长值是否首次使用生效
	 */
//	private Boolean isGrowthFirst;
	/**
	 * 成长值增加次数上限(0为不限制)
	 */
	private Integer growthLimit;

	/**
	 * 饱腹值
	 */
	private Integer fullVal;

	/**
	 * 饱腹值是否首次使用生效
	 */
//	private Boolean isFullFirst;
	/**
	 * 饱腹值增加次数上限(0为不限制)
	 */
	private Integer fullLimit;
	/**
	 * 日使用限制0无限制
	 */
//	private int dayUsageLimit;
	
	/**
	 * 保持清洁度时间（单位：天）
	 */
	private Integer keepCleanTime;
	
	/**
	 * 天试用清洁保持时间增加次数上限(0为不限制)
	 */
	private Integer cleanLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getJoyfulVal() {
		return joyfulVal;
	}

	public void setJoyfulVal(Integer joyfulVal) {
		this.joyfulVal = joyfulVal;
	}

	public Integer getGrowthVal() {
		return growthVal;
	}

	public void setGrowthVal(Integer growthVal) {
		this.growthVal = growthVal;
	}

	public Integer getFullVal() {
		return fullVal;
	}

	public void setFullVal(Integer fullVal) {
		this.fullVal = fullVal;
	}

	public Integer getJoyfulLimit() {
		return joyfulLimit;
	}

	public void setJoyfulLimit(Integer joyfulLimit) {
		this.joyfulLimit = joyfulLimit;
	}

	public Integer getGrowthLimit() {
		return growthLimit;
	}

	public void setGrowthLimit(Integer growthLimit) {
		this.growthLimit = growthLimit;
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
	
}
