package com.maint.dict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbStatistics;
import com.maint.dict.mapper.IDbStatisticsMapper;

@Service
public class StatisticsService {
	@Autowired
	private IDbStatisticsMapper statisticsMapper;
	
	public List<DbStatistics> listStatistics()
	{
		return this.statisticsMapper.listStatistics();
	}
	
	public int addStatistics(DbStatistics statistics)
	{	
		return this.statisticsMapper.addStatistics(statistics);
	}
	
	public boolean  updateStatistics(DbStatistics statistics)
	{
		return this.statisticsMapper.updateStatistics(statistics)>0;
	}
	
	public int deleteStatistics(DbStatistics statistics)
	{
		return this.statisticsMapper.deleteStatistics(statistics);
	}
	
	public DbStatistics findStatisticsById(int statisticsId) {
		return this.statisticsMapper.findStatisticsById(statisticsId);
	}
	
	public 	List<DbStatistics> findAllByPid(Integer pid){
		return this.statisticsMapper.findAllByPid(pid);
	}
	public 	List<DbStatistics> listStatisticsMain(){
		
		return this.statisticsMapper.listStatisticsMain();
	}
}
