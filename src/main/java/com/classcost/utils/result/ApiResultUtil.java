package com.classcost.utils.result;


@SuppressWarnings("all")
public class ApiResultUtil {
	
    /**
     * 封装快速返回结果
     */
public static  ApiResult fastResult(ApiResult apiResult,Integer result){
       if (result!=-1){
           apiResult.setCode(ApiCode.SUCCESS);
           apiResult.setMsg(ApiCode.SUCCESS_MSG);
       }else {
           apiResult.setCode(ApiCode.FAIL);
           apiResult.setMsg(ApiCode.FAIL_MSG);
       }
       return apiResult;
   }
    public static <T>  ApiResult fastResult(ApiResult apiResult, T t){
        if (t!=null){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
            apiResult.setResult(t);
        }else {
            apiResult.setCode(ApiCode.FAIL);
            apiResult.setMsg(ApiCode.FAIL_MSG);
        }
        return apiResult;
    }


    /**
     * 简单封装成功集合
     * @param flag 成功/失败
     * @param data 返回的数据
     */
    public static <T> void fastResultHandler(ApiResult apiResult, boolean flag, Integer stateCode, String msg, T data){
        if(flag){ //设置成功信息
            if(data != null){
                apiResult.setCode(ApiCode.SUCCESS); //状态码
                apiResult.setMsg(ApiCode.SUCCESS_MSG);
                apiResult.setResult(data);
            }
            apiResult.setCode(ApiCode.SUCCESS); //状态码
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
        }else{ //设置失败信息
            apiResult.setCode(stateCode);
            apiResult.setMsg(msg);
        }
    }

}
