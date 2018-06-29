package com.lawu.chick.repository.param;
/**  
 * 饱腹值递减
 * @author lihj
 * @date 2018年4月26日
 */
public class ChickFullDecreaseParam {
	private Long id;
	private int decreaseFullVal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getDecreaseFullVal() {
		return decreaseFullVal;
	}
	public void setDecreaseFullVal(int decreaseFullVal) {
		this.decreaseFullVal = decreaseFullVal;
	}
	
}
