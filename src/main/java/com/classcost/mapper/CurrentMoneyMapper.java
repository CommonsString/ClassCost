package com.classcost.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.classcost.entity.CurrentMoney;

public interface CurrentMoneyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CurrentMoney record);

    int insertSelective(CurrentMoney record);

    CurrentMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CurrentMoney record);

    int updateByPrimaryKeyWithBLOBs(CurrentMoney record);

    int updateByPrimaryKey(CurrentMoney record);
    
    /**
     * 查询总数
     */
    int selectByCountAll(String classNum);
    
    /**
     * 查询总数,按照时间降序排序
     */
    List<CurrentMoney> selectByAllData(@Param("classNum")String classNum/*,
    									@Param("pager") Pager<?> pager*/);
    
}