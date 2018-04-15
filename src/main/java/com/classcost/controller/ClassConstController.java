package com.classcost.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classcost.entity.CurrentMoney;
import com.classcost.service.ClassCostService;
import com.classcost.utils.common.paramHandler;
import com.classcost.utils.result.ApiCode;
import com.classcost.utils.result.ApiResult;
import com.classcost.utils.result.ApiResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "班费公式管理")
@RequestMapping("api/v1/classcost")
@RestController
public class ClassConstController {
	
	private static final Logger log = Logger.getLogger(ClassConstController.class);
	
	
	@Autowired
	private ClassCostService costService;
	
	
	
	/**
	 * 返回总额
	 */
    @SuppressWarnings("rawtypes")
	@ApiOperation("返回总额")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "classNum", value = "班级号", required = true, dataType = "String"),
    })
    @GetMapping("/findTotal")
	public ApiResult findTotal(String classNum/*, Pager pager*/){
		ApiResult apiResult = new ApiResult(); //返回对象
		Double result = null;
        try{
        	result = this.costService.findTotal(classNum/*, pager*/);
            if(result != null){
            	log.info("返回数据集合");
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, result); //数据的封装
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_search_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
        	log.info("查询数据异常！");
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_search_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;    	
	}	
	

	
	
    /**
     *  功能描述：返回所有收入支出记录，按照时间排序
     *  参数：无
     *  返回：成功/失败
     *  时间：4
     */
    @SuppressWarnings("rawtypes")
	@ApiOperation("返回所有收入/支出信息")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "classNum", value = "班级号", required = true, dataType = "String"),
    })
    @GetMapping("/findAllData")
	public ApiResult findAllData(String classNum/*, Pager pager*/){
		ApiResult apiResult = new ApiResult(); //返回对象
		List result = null;
        try{
        	result = this.costService.findAllData(classNum/*, pager*/);
            if(result != null){
            	log.info("返回数据集合");
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, result); //数据的封装
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_search_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
        	log.info("查询数据异常！");
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_search_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;    	
	}
    
    
    /**
     *  功能描述：新增收入记录
     *  参数：CurrentIncome
     *  返回：成功/失败
     *  时间：4
     */
    @SuppressWarnings("rawtypes")
	@ApiOperation("新增收入记录")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "classNum", value = "班级号", required = true, dataType = "String"),
        @ApiImplicitParam(name = "money", value = "转入金额", required = true, dataType = "Double"),
        @ApiImplicitParam(name = "name", value = "经办人姓名", required = true , dataType = "String"),
        @ApiImplicitParam(name = "ctime", value = "时间", required = false , dataType = "Date"),
        @ApiImplicitParam(name = "details", value = "备注", required = false , dataType = "String")
    })
    @PostMapping("/addIncome")
	public ApiResult addIncomeInfo(CurrentMoney income, String ctime){
    	income.setTime(paramHandler.toDate(ctime));
System.out.println(income.toString());
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
        	boolean result = this.costService.addIncomeInfo(income);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;   	
	} 
    
	
    
    /**
     *  功能描述：修改支出/收入记录
     *  参数：CurrentIncome
     *  返回：成功/失败
     *  时间：4
     */
    @SuppressWarnings("rawtypes")
	@ApiOperation("修改支出/收入记录")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "记录id", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "name", value = "经办人姓名", required = false , dataType = "String"),
        @ApiImplicitParam(name = "time", value = "时间", required = false , dataType = "String"),
        @ApiImplicitParam(name = "details", value = "备注", required = false , dataType = "String")
    })
//    @PostMapping("/updateIncome")
	public ApiResult updateIncomeInfo(CurrentMoney income){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
//            boolean result = this.schoolClassService.updateClassType(classTypeId, classTypeName);
        	boolean result = this.costService.updateIncomeInfo(income);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_update_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_update_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;		
	}     
	
    
    /**
     *  功能描述：删除---支出/收入记录
     *  参数：CurrentIncome
     *  返回：成功/失败
     *  时间：4
     */
    @SuppressWarnings("rawtypes")
	@ApiOperation("删除---支出/收入记录")
    @ApiImplicitParam(name = "incomeId", value = "记录id", required = true, dataType = "Long")
    @PostMapping("/delBothInfo")
    public ApiResult updateIncomeInfoDel(Long incomeId){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
        	boolean result = this.costService.updateIncomeInfoDel(incomeId);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_delete_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;    	
    } 
    
    
    
    
    /**
     *  功能描述：新增支出记录
     *  参数：CurrentMoney
     *  返回：成功/失败
     *  时间：4
     */
    @SuppressWarnings("rawtypes")
	@ApiOperation("新增支出记录")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "classNum", value = "班级号", required = true, dataType = "String"),
        @ApiImplicitParam(name = "money", value = "支出金额", required = true, dataType = "Double"),
        @ApiImplicitParam(name = "name", value = "经办人姓名", required = true , dataType = "String"),
        @ApiImplicitParam(name = "ctime", value = "时间", required = false  , dataType = "String"),
        @ApiImplicitParam(name = "details", value = "备注", required = false , dataType = "String")
    })
    @PostMapping("/addCost")
	public ApiResult addCostInfo(CurrentMoney income, String ctime){
    	income.setTime(paramHandler.toDate(ctime));
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
        	boolean result = this.costService.addCostInfo(income);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;   	
	} 
    
    
    
}

