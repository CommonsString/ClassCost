package com.classcost.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具集合
 */
public class paramHandler {

	/**
	 * 将正数转为负数
	 */
	public static double toNegative(double number){
		
		if(number > 0){
			return -number;
		}
		return number;
	}
	
	/**
	 * 返回绝对值
	 */
	public static double toPositive(double number){
		return Math.abs(number);
	}
	
	
	public static void main(String[] args) {
		
		String m = "2016-10-15";
		Date t = toDate(m);
		System.out.println(t);
	}
	
	
	/**
	 * 时间格式
	 */
	public static Date toDate(String time){
		Date t = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			t = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t;
	}
	
}
