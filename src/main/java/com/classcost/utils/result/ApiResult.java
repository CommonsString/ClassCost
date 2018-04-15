package com.classcost.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {

	/**
	 * 返回码
	 */
	private int code;
	
	/**
	 * 返回信息描述
	 */
	private String msg;
	
	/**
	 * 返回的数据
	 */
	private T result;
	
	/**
	 * 其他
	 */
	private Object params;
	
	
	
}
