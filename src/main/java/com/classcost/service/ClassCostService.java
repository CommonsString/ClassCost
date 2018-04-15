package com.classcost.service;

import java.util.List;

import com.classcost.entity.CurrentMoney;

public interface ClassCostService {
	
	
	/**
	 * 返回总额
	 */
	Double findTotal(String classNum);
	
	/**
	 * 返回所有收入/支出信息
	 */
	@SuppressWarnings("rawtypes")
	List findAllData(String classNum/*, Pager<?> pager*/);
	
	
	/**
	 * 新增收入记录
	 */
	boolean addIncomeInfo(CurrentMoney income);
	
	/**
	 * 根据记录ID,删除记录,非物理删除
	 */
	boolean updateIncomeInfoDel(Long incomeId);
	
	/**
	 * 根据Id,修改收入记录
	 */
	boolean updateIncomeInfo(CurrentMoney income);
	
	/**
	 * 新增支出记录
	 */
	boolean addCostInfo(CurrentMoney income);
	
	
	

}
