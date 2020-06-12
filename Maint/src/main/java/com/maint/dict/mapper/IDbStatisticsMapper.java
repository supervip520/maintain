package com.maint.dict.mapper;

import java.util.List;

import com.along.entity.DbStatistics;

public interface IDbStatisticsMapper {
	
	List<DbStatistics> listStatistics();
	List<DbStatistics> listStatisticsMain();

	int addStatistics(DbStatistics statistics);

	int updateStatistics(DbStatistics statistics);

	int deleteStatistics(DbStatistics statistics);
	
	DbStatistics findStatisticsById(int statisticsId);
	
	List<DbStatistics> findAllByPid(Integer pid);
}
