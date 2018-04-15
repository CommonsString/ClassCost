package com.classcost.service.Impl;


import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classcost.entity.ClassTotal;
import com.classcost.entity.CurrentMoney;
import com.classcost.mapper.ClassTotalMapper;
import com.classcost.mapper.CurrentMoneyMapper;
import com.classcost.service.ClassCostService;
import com.classcost.utils.common.paramHandler;

@Service
public class ClassCostServiceImpl implements ClassCostService{
	//日志记录
	private static final Logger log = Logger.getLogger(ClassCostServiceImpl.class);

	@Autowired
	private CurrentMoneyMapper moneyMapper;

	@Autowired
	private ClassTotalMapper totalMapper;
	
	/**
	 * 返回所有收入/支出信息
	 */
	@SuppressWarnings({ "rawtypes"})
	@Override
	@Transactional 
	public List/*Pager<?>*/ findAllData(String classNum/*, Pager<?> pager*/) {
		
		//查询总数
		int total = this.moneyMapper.selectByCountAll(classNum);
		System.out.println(total + " 记录");
		
//		pager.setPageTotal(total);
		//查询集合
		List moneyList = this.moneyMapper.selectByAllData(classNum/*, pager*/);
		for(Object mo : moneyList){
			System.out.println(mo.toString());
		}
		log.info("泛型");
//		pager.setContent(moneyList);
		
		return moneyList;
	}

	
	
	
	/**
	 * 新增收入记录
	 */
	@Override
	@Transactional
	public boolean addIncomeInfo(CurrentMoney income) {
		//根据班号查询总额表,存在修改金额,不存在添加
		int isExist = this.totalMapper.selectByCountClaNum(income.getClassNum()); 
		if(isExist <= 0){
			//构建对象
			ClassTotal total = new ClassTotal();
			total.setClassNum(income.getClassNum());
			total.setTotal(income.getMoney());
			total.setChangeTime(new Date());
			//创建时间
			Date time = new Date();
			total.setChangeTime(time);
			income.setCreateTime(time);
			income.setCurrentTotal(income.getMoney());
			//添加记录
			int flag_m = this.moneyMapper.insertSelective(income);
			int flag_t = this.totalMapper.insertSelective(total);
			if(flag_m <= 0 && flag_t <=0) return false;
			return true;
		}else{
			//取出总额表记录
			ClassTotal total = this.totalMapper.selectByClaNum(income.getClassNum());
			//当前收入
			double currentIncome = income.getMoney();
			//总额表记录
			double currentTotal = total.getTotal();
			//计算
			double sum = currentIncome + currentTotal;
			//记录总额
			total.setTotal(sum);
			//记录当前总额
			income.setCurrentTotal(sum);
			//修改时间
			Date time = new Date();
			total.setChangeTime(time);
			income.setCreateTime(time);
			//修改记录
			int flag_t = this.totalMapper.updateByPrimaryKeySelective(total);
			int flag_m = this.moneyMapper.insertSelective(income);
			if(flag_t <= 0 && flag_m <=0) return false;
			return true;			
		}
	}




	/**
	 * 删除前,需要修改金额
	 * 根据记录ID,删除记录,非物理删除
	 */
	@Override
	@Transactional
	public boolean updateIncomeInfoDel(Long incomeId) {
		//前记录对象
		CurrentMoney changeMoney = this.moneyMapper.selectByPrimaryKey(incomeId);
		if(changeMoney == null) return false;
		//根据前记录对象,查询总额
		ClassTotal total = this.totalMapper.selectByClaNum(changeMoney.getClassNum());
System.err.println(changeMoney.toString());
		//待存对象
		CurrentMoney nowCurrentMoney = new CurrentMoney();
		//获取金额
		double lastMoney = changeMoney.getMoney();
System.out.println("收入记录： " + lastMoney);
		double totalMoney = total.getTotal();
System.out.println("记录的总额：  " + totalMoney);
		Double nowMoney = null;
		//判断金额的正负
		if(lastMoney > 0){
			//总额
			nowMoney = totalMoney - lastMoney;
System.out.println("删除收入记录,计算后的总额  " + nowMoney);
		}else{
			nowMoney = totalMoney + paramHandler.toPositive(lastMoney);
		}
		nowCurrentMoney.setCurrentTotal(nowMoney);
		nowCurrentMoney.setId(incomeId);
		nowCurrentMoney.setState("0");
		//删除时间
		nowCurrentMoney.setDeleteTime(new Date());
		int flag_m = this.moneyMapper.updateByPrimaryKeySelective(nowCurrentMoney);
		//总额表
//		ClassTotal total = new ClassTotal();
		total.setTotal(nowMoney);
		total.setChangeTime(new Date());
		int flag_t = this.totalMapper.updateByClassNumKeySelective(total);
		if(flag_m <= 0 || flag_t <= 0) return false;
		return true;
//		return false;
	}




	
	/**
	 * 根据Id,修改收入记录
	 */
	@Override
	@Transactional
	public boolean updateIncomeInfo(CurrentMoney income) {
		if(income != null){
			int flag = this.moneyMapper.updateByPrimaryKeySelective(income);
			if(flag <= 0) return false;
		}
		return true;
	}



	/**
	 * 新增支出记录
	 */
	@Override
	@Transactional
	public boolean addCostInfo(CurrentMoney income) {
		//根据班号查询总额表,存在修改金额,不存在添加
		int isExist = this.totalMapper.selectByCountClaNum(income.getClassNum()); 
		if(isExist <= 0){
			//构建对象
			ClassTotal total = new ClassTotal();
			total.setClassNum(income.getClassNum());
			//取相反数
			double negativeNum = paramHandler.toNegative(income.getMoney());
			total.setTotal(negativeNum);
System.out.println("相反数： " + negativeNum);
			total.setChangeTime(new Date());
			//创建时间
			Date time = new Date();
			total.setChangeTime(time);
			
			income.setMoney(negativeNum);
			income.setCreateTime(time);
			income.setCurrentTotal(negativeNum);
			//添加记录
			int flag_m = this.moneyMapper.insertSelective(income);
			int flag_t = this.totalMapper.insertSelective(total);
			if(flag_m <= 0 && flag_t <=0) return false;
			return true;
		}else{
			//取出总额表记录
			ClassTotal total = this.totalMapper.selectByClaNum(income.getClassNum());
			//当前支出
			double currentCost = paramHandler.toNegative(income.getMoney());
			//总额表记录
			double currentTotal = total.getTotal();
			//计算
			double sum = currentCost + currentTotal;
			//记录总额
			total.setTotal(sum);
			//记录当前总额
			income.setCurrentTotal(sum);
			//修改时间
			Date time = new Date();
			total.setChangeTime(time);
			income.setMoney(currentCost);
			income.setCreateTime(time);
			//修改记录
			int flag_t = this.totalMapper.updateByPrimaryKeySelective(total);
			int flag_m = this.moneyMapper.insertSelective(income);
			if(flag_t <= 0 && flag_m <=0) return false;
			return true;	
		}
	
	}




	@Override
	public Double findTotal(String classNum) {
		ClassTotal total = this.totalMapper.selectByClaNum(classNum);
		return total.getTotal();
	}
	
	
	
	
	
}
