package com.classcost.mapper;

import com.classcost.entity.ClassTotal;

public interface ClassTotalMapper {
    int deleteByPrimaryKey(Long classCostId);

    int insert(ClassTotal record);

    int insertSelective(ClassTotal record);

    ClassTotal selectByPrimaryKey(Long classCostId);

    int updateByPrimaryKeySelective(ClassTotal record);

    int updateByPrimaryKey(ClassTotal record);
    
    /**
     * 根据班级号查是否存在
     */
    int selectByCountClaNum(String classNum);
    
    /**
     * 根据班级号,查询记录
     */
    ClassTotal selectByClaNum(String classNum);
    
    /**
     * 根据班级号更新
     */
    int updateByClassNumKeySelective(ClassTotal record);
    
    
    
}