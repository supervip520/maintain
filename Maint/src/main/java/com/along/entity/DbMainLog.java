package com.along.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//维护日志
@Data
public class DbMainLog {
	
	private int id;
	
	private String sitemainId; //维护站点编号
	
	private String sitemainName;//维护站点名称
	
	private String resolvent;//处理情况
	
	private String maintainManId;
	 
	private String maintainManName; //维护人
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maintainDate; //维护日期
	
	private String remark; //备注


}
