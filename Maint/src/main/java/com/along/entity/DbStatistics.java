package com.along.entity;


import lombok.Data;

@Data
public class DbStatistics {

	private int statisticsId;
	private String statisticsName;
	private Integer statisticsNumber; //配件总数
	private Integer statisticsEmp;//配件使用
	private Integer statisticsSyu;//配件剩余
	private int supplierId; //供应商
	private String supplierName; //供应商
	private Integer pid; //父ID
	private String isdelete;



	
	
	
	

}
