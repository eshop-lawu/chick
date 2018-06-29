package com.lawu.chick.repository.param;

/**
 * @Description
 * @author zhangrc
 * @date 2018年4月26日
 */
public class CleannessParam {
	
	private Long id;
	
	private Byte type;
	
	private int cleanness;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * @return the cleanness
	 */
	public int getCleanness() {
		return cleanness;
	}

	public void setCleanness(int cleanness) {
		this.cleanness = cleanness;
	}
	
	

}
