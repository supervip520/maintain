package com.along.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DbSiteinformation {
	
	private int sid;
	private String siteId; //站点编号
	private String siteName;//站点名称
	private String jd;
	private String wd;
	private String riversName;//河流名称
	private String siteTypeName;//站点类型
	private String siteTypeId;//站点类型编号
	private String siteAddress;//站点地址
	private String remark;//备注
	private String equipment;//设备厂家
	private String maintainsId;//维护情况编号
	private String maintainsName;//维护情况
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maintainDate; //维护日期
	private String isdelete;
	


	
}	
