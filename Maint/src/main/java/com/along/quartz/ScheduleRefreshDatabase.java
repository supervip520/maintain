package com.along.quartz;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.along.entity.DbMainLog;
import com.along.entity.DbMaintain;
import com.along.entity.DbSiteinformation;
import com.maint.maintain.serivce.MaintainService;
import com.maint.site.service.SiteinformationService;
 
/**
 * 从数据库定时更新任务
 **/
@Configuration
@EnableScheduling
@Component
public class ScheduleRefreshDatabase {
	
	@Autowired
	MaintainService mainservice; 
	@Autowired
	SiteinformationService siteService;
	
  
    @Scheduled(cron = "0 30 20 * * ?")
    public void scheduled() throws SchedulerException {
    	
    	List<DbMaintain> findDay = this.mainservice.findDay();
    	DbMainLog log=null;
    	if(null!=findDay) {
    	for (DbMaintain dbMaintain : findDay) {
    		log=new DbMainLog();
    		DbSiteinformation site = siteService.getSiteByidSiteid(dbMaintain.getSitemainId());
    		log.setSitemainId(dbMaintain.getSitemainId());
			log.setSitemainName(site.getSiteName());
			log.setMaintainManId(dbMaintain.getMaintainManId());
			log.setMaintainManName(dbMaintain.getMaintainMan());
			log.setMaintainDate(dbMaintain.getMaintainDate());
			log.setRemark(dbMaintain.getRemark());
			log.setResolvent(dbMaintain.getResolvent());
			Integer count = mainservice.findDayBySiteId(log.getSitemainId());
			if(count==0) {
				mainservice.addMainLog(log);
			}
		}
    	}
    }
    
}
 