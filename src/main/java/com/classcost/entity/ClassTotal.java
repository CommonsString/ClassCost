package com.classcost.entity;

import java.util.Date;

public class ClassTotal {
    private Long classCostId;

    private String classNum;

    private Double total;

    private String state;

    private Date changeTime;

    public Long getClassCostId() {
        return classCostId;
    }

    public void setClassCostId(Long classCostId) {
        this.classCostId = classCostId;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum == null ? null : classNum.trim();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}