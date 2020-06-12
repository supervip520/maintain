package com.maint.maintain.mapper;

import java.util.List;
import java.util.Map;

import com.along.entity.DbMainLog;
import com.along.entity.DbMaintain;


public interface IDbMaintainMapper {
  
		/*根据站点编号查询维护信息 */
		List<DbMaintain> findMaintainBySiteId(Map<String, Object> map);
  
		/*新增维护情况*/
		boolean insertMaintain(DbMaintain maintain);
	
		/*根据站点编号查询维护信息 */
		List<DbMaintain> exportExcelMain(String siteId);
		
		//故障提交
		boolean addFault(DbMaintain maintain);
		
		boolean delMaintain(Integer id);
		
		//新增维护日志
		boolean addMainLog(DbMainLog mainlog);
		
		DbMaintain MainfindByid(Integer id);
		
		
		//统计当天维护情况
		List<DbMaintain> findDay();
		
		//查询统计信息
		List<DbMainLog> findDayLog();
		
		//根据时间查询统计
		List<DbMainLog> findDayByDate(String startDate,String endDate);
		
		//查询当天数据是否重复
		Integer  findDayBySiteId(String siteId);
 
}

