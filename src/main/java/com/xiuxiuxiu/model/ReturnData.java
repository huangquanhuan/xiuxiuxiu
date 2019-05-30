package com.xiuxiuxiu.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.istack.FinalArrayList;

/**
 * 返回数据实体类
 * @param <T>
 */
public class ReturnData <T> {
	//数据总条数
    private Integer total;
    //数据集合
    private List<T> rows = new ArrayList<T>();
    
    public ReturnData(final Integer sum,final List<T> datas) {
    	total = sum;
    	rows = datas;
    }
    public List<T> getRows() {
		return rows;
	}
    public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
    public Integer getTotal() {
		return total;
	}
    public void setTotal(Integer total) {
		this.total = total;
	}
}