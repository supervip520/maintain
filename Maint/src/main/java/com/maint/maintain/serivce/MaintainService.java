package com.maint.maintain.serivce;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbMainLog;
import com.along.entity.DbMaintain;
import com.along.entity.DbStatistics;
import com.maint.dict.mapper.IDbStatisticsMapper;
import com.maint.dict.service.StatisticsService;
import com.maint.maintain.mapper.IDbMaintainMapper;

@Service 
public class MaintainService   {
	
	@Autowired
	IDbMaintainMapper maintainMapper;
	
	@Autowired
	IDbStatisticsMapper statisMapper;
	
	@Autowired
	StatisticsService statisticsService;
	

			/*根据站点编号查询维护信息 */
		public	List<DbMaintain> findMaintainBySiteId(Map<String, Object> map)
				{
					return this.maintainMapper.findMaintainBySiteId(map);
				}
				/*新增维护情况*/
		public	boolean insertMaintain(DbMaintain maintain)
				{
					return this.maintainMapper.insertMaintain(maintain);
				}
				/*根据站点编号查询维护信息 */
		public	List<DbMaintain> exportExcelMain(String siteId)
				{
					return this.maintainMapper.exportExcelMain(siteId);
				}
		public	boolean addFault(DbMaintain maintain)
				{
					return this.maintainMapper.addFault(maintain);
				}
		
		public 	boolean delMaintain(Integer id) {
			
			DbMaintain maintain = maintainMapper.MainfindByid(id);
			String faultTypeId = maintain.getFaultTypeId();
			if(null!=faultTypeId)
			{
				String[] split = faultTypeId.split(",");
				for (int i = 0; i < split.length; i++) {
					DbStatistics statistics = statisMapper.findStatisticsById(Integer.parseInt(split[i]));
					Integer number = statistics.getStatisticsNumber();
					Integer emp = statistics.getStatisticsEmp();
					Integer sy=emp-1;//配件使用
					Integer pjsy=number-sy;//配件剩余
					statistics.setStatisticsNumber(number);
					statistics.setStatisticsEmp(sy);
					statistics.setStatisticsSyu(pjsy);
					statisticsService.updateStatistics(statistics);
					}
				
				}
			
			return this.maintainMapper.delMaintain(id);
			
		}
		//新增维护日志
		public 	boolean addMainLog(DbMainLog mainlog) {
			
			return this.maintainMapper.addMainLog(mainlog);
		}
		//统计当天维护情况
		public List<DbMaintain> findDay(){
			return this.maintainMapper.findDay();
		}
		
		
		//查询统计信息
		public List<DbMainLog> findDayLog(){
			return this.maintainMapper.findDayLog();
		}
		
		//根据时间查询统计
		public List<DbMainLog> findDayByDate(String startDate,String endDate){
			
			return this.maintainMapper.findDayByDate(startDate, endDate);
		}
		
		//查询当天数据是否重复
		public	Integer  findDayBySiteId(String siteId) {
			return this.maintainMapper.findDayBySiteId(siteId);
		}
}
