package com.maint.dict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.along.entity.DbStatistics;
import com.maint.dict.service.StatisticsService;
import com.maint.dict.service.SupplierService;

@Controller
@RequestMapping(value="dict")
public class StatisticsController {
	@Autowired
	StatisticsService statisticsService;
	
	@Autowired
	SupplierService suplierService;
	
	/**
	 * 配件列表
	 */
	@ResponseBody
	@RequestMapping(value = "listStatistics", method = RequestMethod.GET)
	public String listStatistics(HttpServletRequest request, HttpServletResponse response) {
		
		List<DbStatistics> list = this.statisticsService.listStatistics();
			
		return JSON.toJSONString(list);
	}
	/**
	 * 配件列表维护
	 */
	@ResponseBody
	@RequestMapping(value = "listStatisticsMain", method = RequestMethod.GET)
	public String listStatisticsMain(HttpServletRequest request, HttpServletResponse response) {
		
		List<DbStatistics> list = this.statisticsService.listStatisticsMain();
			
		return JSON.toJSONString(list);
	}
	
	/**
	 * 配件页面
	 */
	@RequestMapping(value = "StatisticsPage")
	   public String StatisticsPage(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
	       return "/dict/statistics";
	   }
	
	
	/**
	 * 配件页面
	 */
	@RequestMapping(value = "Home")
	   public String StatisticsHome(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
	       return "/dict/home";
	   }
	
	/**
	 * 添加配件
	 */
	@ResponseBody
	@RequestMapping(value = "addStatistics", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String addDepartment(@RequestBody DbStatistics statistics,HttpServletRequest request, HttpServletResponse response){
		
		if(statistics!=null)
		{
			Integer number = statistics.getStatisticsNumber();
			Integer emp = statistics.getStatisticsEmp();
			Integer sy=number-emp;
			statistics.setStatisticsSyu(sy);
		}
		
		int count = this.statisticsService.addStatistics(statistics);
		Map<String ,String> reslut = new HashMap<>();
		if(count>0) {
			reslut.put("msg", "配件添加成功");
		}else{
			reslut.put("msg", "配件添加失败");
		}
		return JSON.toJSONString(reslut);
	}
	
	/**
	 * 修改配件
	 */
	@ResponseBody
	@RequestMapping(value = "updateStatistics", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String updateStatistics(@RequestBody DbStatistics statistics,HttpServletRequest request, HttpServletResponse response) {
		
		if(statistics!=null)
		{
			Integer number = statistics.getStatisticsNumber();
			Integer emp = statistics.getStatisticsEmp();
			Integer sy=number-emp;
			statistics.setStatisticsSyu(sy);
		}
		
		boolean succeed = this.statisticsService.updateStatistics(statistics);
		Map<String ,String> reslut = new HashMap<>();
		if(succeed) {
			reslut.put("msg", "配件修改成功");
			
		}else{
			reslut.put("msg", "配件修改失败");
		}
		return JSON.toJSONString(reslut);
	}
	
	/**
	 * 删除配件
	 */
	@ResponseBody
	@RequestMapping(value = "deleteStatistics", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String deleteStatistics(@RequestBody DbStatistics statistics,HttpServletRequest request, HttpServletResponse response) {
		int count = this.statisticsService.deleteStatistics(statistics);
		Map<String ,String> reslut = new HashMap<>();
		if(count>0)
		{
			reslut.put("msg", "删除成功");
		}else {
			reslut.put("msg", "删除失败");
		}
		return JSON.toJSONString(reslut);
	}
	
	
	/**
	 * 配件供应商
	 */
	@ResponseBody
	@RequestMapping(value = "findAllByPid", method = RequestMethod.GET)
	public String findAllcombox(HttpServletRequest request, HttpServletResponse response,int pid) {
		
		List<DbStatistics> list = this.statisticsService.findAllByPid(pid);
		
		return JSON.toJSONString(list);
	}
	
	
	
}
