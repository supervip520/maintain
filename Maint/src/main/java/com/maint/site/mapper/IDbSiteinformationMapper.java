package com.maint.site.mapper;

import java.util.List;
import java.util.Map;

import com.along.entity.DbSiteinformation;

public interface IDbSiteinformationMapper {
		
	List<DbSiteinformation> findsiteAll(Map<String, Object> map);
	
	List<DbSiteinformation> findsitesearch();
	
	/*站点录入*/
	int addSite(DbSiteinformation site);
	
	DbSiteinformation getSiteByidSiteid(String siteId);
	
	//地图查询
	List<DbSiteinformation> GDAll(String siteTypeName);
	
	//站点信息修改
	boolean updateSiteMain(DbSiteinformation site);
	
	int deleteSite(DbSiteinformation site);
	

}
