package com.maint.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件属性; 从配置文件中取值注入/src/main/resources/application.properties
 * 
 * @ConfigurationProperties不会创建bean，所以要加上其他注解交给spring容器管理；如@Component
 * @author dzhw
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataBaseConfigure {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
