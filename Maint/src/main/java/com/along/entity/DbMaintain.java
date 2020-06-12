package com.along.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DbMaintain {
	  private int id;
	  private String sitemainId;	//站点编号
	  private String sitemainName;	//站点名称
	  private String maintainId;	//维护情况ID
	  private String maintainName;	//维护情况
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date faultDate;		//故障日期
	  private String faultTypeId;
	  private String faultTypeName;//故障类型
	  
	  private String faultother;//其他配件
	  private String dtusupplierId;		//DTU供应商ID
	  private String dtusupplierName;	//DTU供应商名称
	  private String rtusupplierId;		//RTU供应商ID
	  private String rtusupplierName;	//RTU供应商名称
	  private String card; //电话卡号
	  private String faultContent;//故障内容
	  private String resolvent; //处理方法
	  private String maintainImg;	//现场图片1
	  private String maintainImgs;	//现场图片2
	  private String maintainManId; //维护人员ID
	  private String maintainMan;	//维护人员名称
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date maintainDate;	//维护日期
	  private String situations;	
	  private String faultUserId;	//故障提交人ID
	  private String faultUserName; //故障提交人
	  private String remark; //备注
	  
	 

 
}


