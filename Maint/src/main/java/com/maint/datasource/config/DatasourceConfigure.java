package com.maint.datasource.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置数据源
 * @Primary 标识为主数据源；主数据源只能有一个
 * @author dzhw
 *
 */
@Configuration  
@MapperScan(basePackages = "com.maint.*.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DatasourceConfigure implements WebMvcConfigurer{
	@Primary
	@Bean
	public DataSource datasource(DataBaseConfigure config) throws SQLException {
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setDriverClassName(config.getDriverClassName());
		dataSource.setUrl(config.getUrl());
		dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        try {
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
	}
	
	

	@Primary
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//对应我   们的实体类所在的包,多个package之间可以用逗号或者分号等来进行分隔。包全名
		bean.setTypeAliasesPackage("com.along.entity");
		//Mapper.xml 配置文件路径
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*Mapper.xml"));
		return bean.getObject();
	}

	@Primary
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
