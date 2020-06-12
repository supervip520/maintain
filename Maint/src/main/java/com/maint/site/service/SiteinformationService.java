package com.maint.site.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbSiteinformation;
import com.maint.site.mapper.IDbSiteinformationMapper;

@Service
public class SiteinformationService {
	
	@Autowired
	public	IDbSiteinformationMapper SiteinformationMapper;
	
	
	//地图查询
	public	List<DbSiteinformation> GDAll(String siteTypeName)
		{
			return this.SiteinformationMapper.GDAll(siteTypeName);
		}
	
	
	public  List<DbSiteinformation> findsiteAll(Map<String, Object> map)
	{
		return this.SiteinformationMapper.findsiteAll(map);
	}
	
	public  List<DbSiteinformation> findsiteAll()
	{
		return this.SiteinformationMapper.findsitesearch();
	}
	
	
	/*站点录入*/
	public  int addSite(DbSiteinformation site)
	{
		return this.SiteinformationMapper.addSite(site);
	}
	
	public  DbSiteinformation getSiteByidSiteid(String siteId)
	{
		return this.SiteinformationMapper.getSiteByidSiteid(siteId);
	}
	
	
	
	//站点信息修改
	public   boolean updateSiteMain(DbSiteinformation site)
	{
		return this.SiteinformationMapper.updateSiteMain(site);
	}
	
	//删除
	public   int deleteSite(DbSiteinformation site) {
		
		return this.SiteinformationMapper.deleteSite(site);
	}
	

}
