package com.book.core.model;

/**
 * 订单状态使用
 * @author liweihan
 *
 */
public class StatusVo {
	
	private int id; //状态码
	private String statusName;//状态描述
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	
}
