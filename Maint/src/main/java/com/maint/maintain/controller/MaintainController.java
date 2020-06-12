package com.maint.maintain.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.along.entity.DbMainLog;
import com.along.entity.DbMaintain;
import com.along.entity.DbSiteinformation;
import com.along.entity.DbStatistics;
import com.along.entity.DbUser;
import com.maint.dict.service.StatisticsService;
import com.maint.maintain.serivce.MaintainService;
import com.maint.site.service.SiteinformationService;

@Controller
@RequestMapping(value="maintain")
public class MaintainController {
	@Autowired
	MaintainService maintainService;
	
	@Autowired
	StatisticsService statisticsService;
	
	@Autowired
	SiteinformationService siteinformationService;
	
	/**
	 * 跳转维护情况页面
	 */
	@RequestMapping(value = "listMaintainPage")
	   public String loginPage(String siteid,ModelMap model) throws Exception {
		model.put("siteid", siteid);
	       return "/site/maintainList";
	   }
	
	
	
	
	/**
	 *  根据站点编号查询该站点的维护情况
	 *  维护情况列表
	 */
	
	@ResponseBody
	@RequestMapping(value = "listMaintain", method = RequestMethod.GET)
	public String listMaintain(String siteid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteid",siteid);
		List<DbMaintain> list = this.maintainService.findMaintainBySiteId(map);
		return JSON.toJSONString(list);
	}
	
	/**
	 *  新增维护情况
	 */
	@ResponseBody
	@RequestMapping(value = "addMaintain", method = RequestMethod.POST)
	public String addMaintain(DbMaintain maintain,MultipartFile file1,MultipartFile file2) {
		
		String msg;
		String faultTypeName="";
		//获取故障配件ID
		String faultTypeId = maintain.getFaultTypeId();
		//截取最后一个字符串
		if(null!=faultTypeId)
		{
			String faultTypeName1 = maintain.getFaultTypeName();
			String maintainMan2 = maintain.getMaintainMan();
			 faultTypeName = faultTypeName1.substring(0,faultTypeName1.length()-1);
			String maintainMan = maintainMan2.substring(0,maintainMan2.length()-1);
			maintain.setFaultTypeName(faultTypeName);
			maintain.setMaintainMan(maintainMan);
			String[] split = faultTypeId.split(",");
			
			for (int i = 0; i < split.length; i++) {
				DbStatistics sta = statisticsService.findStatisticsById(Integer.parseInt(split[i]));
				int statisticsSyu = sta.getStatisticsSyu();
				
				
				//判断配件是否充足
				if(statisticsSyu<=0)
				{
					msg="配件不足，添加失败";
					return msg;
				}else {
					if(Integer.parseInt(split[i])==1 && "".equals(maintain.getRtusupplierId()))
					{
						
					}else if(Integer.parseInt(split[i])==2 && "".equals(maintain.getDtusupplierId())) {
						
					} else {
						Integer number = sta.getStatisticsNumber();
						Integer emp = sta.getStatisticsEmp();
						Integer sy=emp+1;//配件使用
						Integer pjsy=number-sy;//配件剩余
						sta.setStatisticsNumber(number);
						sta.setStatisticsEmp(sy);
						sta.setStatisticsSyu(pjsy);
						 statisticsService.updateStatistics(sta);
					}
					
				}
			}
			//DTU供应商
			if(null!=maintain.getDtusupplierId())
			{
			DbStatistics dtusup = statisticsService.findStatisticsById(Integer.parseInt(maintain.getDtusupplierId()));
			int dtusy = dtusup.getStatisticsSyu();
			if(dtusy<=0)
			{
				msg="DTU配件不足，添加失败";
				return msg;
			}else {
				Integer dtunumber = dtusup.getStatisticsNumber();
				Integer dtuemp = dtusup.getStatisticsEmp();
				Integer sy=dtuemp+1;//配件使用
				Integer dtupjsy=dtunumber-sy;//配件剩余
				dtusup.setStatisticsNumber(dtunumber);
				dtusup.setStatisticsEmp(sy);
				dtusup.setStatisticsSyu(dtupjsy);
				 statisticsService.updateStatistics(dtusup);
			}
			}else if(null!=maintain.getRtusupplierId())
			{
				//RDTU供应商
				DbStatistics rtusup = statisticsService.findStatisticsById(Integer.parseInt(maintain.getRtusupplierId()));
				int rtusy = rtusup.getStatisticsSyu();
				if(rtusy<=0)
				{
					msg="DTU配件不足，添加失败";
					return msg;
				}else {
					Integer rtunumber = rtusup.getStatisticsNumber();
					Integer rtuemp = rtusup.getStatisticsEmp();
					Integer sy=rtuemp+1;//配件使用
					Integer rtupjsy=rtunumber-sy;//配件剩余
					rtusup.setStatisticsNumber(rtunumber);
					rtusup.setStatisticsEmp(sy);
					rtusup.setStatisticsSyu(rtupjsy);
					 statisticsService.updateStatistics(rtusup);
				}
			}
		}
		
		String siteName = siteinformationService.getSiteByidSiteid(maintain.getSitemainId()).getSiteName();
		
		// 文件名, 生成一串随机的东西, 作为文件名, 获取图片的扩展名,UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，
		String fileName1 =siteName+ UUID.randomUUID().toString().substring(0, 8);
		String fileName2 =siteName+ UUID.randomUUID().toString().substring(0, 8);
		//得到上传时的文件名,当然不包括路径
		String subfix = file1.getOriginalFilename();
		String subfix2 = file2.getOriginalFilename();
		//截取文件后缀类型.jpg/.png
		int i = subfix.lastIndexOf(".");
		int j = subfix2.lastIndexOf(".");
		if(i==-1)
		{
			subfix=subfix+".jpg"; 
		}
		if(j==-1)
		{
			subfix2=subfix2+".jpg"; 
		}
		subfix = subfix.substring(subfix.lastIndexOf("."));
		subfix2 = subfix2.substring(subfix2.lastIndexOf("."));
        //组合后的新文件全名
		fileName1 = fileName1 + subfix;
		fileName2 = fileName2 + subfix2;
		// 获取项目的实际运行路径
		String path = "D:/upload";
		try {
            //保存图片到相应路径
			InputStream inputStream = file1.getInputStream();
			InputStream inputStream2 = file2.getInputStream();
			OutputStream outputStream = new FileOutputStream(path + "/" + fileName1);
			OutputStream outputStream2 = new FileOutputStream(path + "/" + fileName2);
			IOUtils.copy(inputStream, outputStream);
			IOUtils.copy(inputStream2, outputStream2);
			// 如果文件保存成功, 将图片路径 写入Goods对象中
			maintain.setMaintainImg(fileName1);
			maintain.setMaintainImgs(fileName2);
		} catch (IOException e) {
			msg="添加失败";
		}
		
		//根据站点编号查询站点
		DbSiteinformation site = siteinformationService.getSiteByidSiteid(maintain.getSitemainId());
		
	 boolean b = this.maintainService.insertMaintain(maintain);
	 	if(b) {
	 		String whid = maintain.getMaintainId();
			//如果维护情况 未解决 
			if(!"".equals(whid))
			{
				site.setMaintainsId(whid);
				site.setSiteId(maintain.getSitemainId());
				site.setMaintainDate(new Date());
			}
			siteinformationService.updateSiteMain(site);
			msg="添加成功";
			
		}else{
			msg="添加失败";
		}
		return msg;
	}
	

	/**
	 *  修改维护情况
	 */
	
	@ResponseBody
	@RequestMapping(value = "updMaintain", method = RequestMethod.POST)
	public String updMaintain(DbMaintain maintain,MultipartFile file1,MultipartFile file2) {
		
		String msg;
		String faultTypeName="";
		//获取故障配件ID
		String faultTypeId = maintain.getFaultTypeId();
		//截取最后一个字符串
		if(null!=faultTypeId)
		{
			String faultTypeName1 = maintain.getFaultTypeName();
			String maintainMan2 = maintain.getMaintainMan();
			 faultTypeName = faultTypeName1.substring(0,faultTypeName1.length()-1);
			String maintainMan = maintainMan2.substring(0,maintainMan2.length()-1);
			maintain.setFaultTypeName(faultTypeName);
			maintain.setMaintainMan(maintainMan);
			String[] split = faultTypeId.split(",");
			for (int i = 0; i < split.length; i++) {
				DbStatistics sta = statisticsService.findStatisticsById(Integer.parseInt(split[i]));
				int statisticsSyu = sta.getStatisticsSyu();
				//判断配件是否充足
				if(statisticsSyu<=0)
				{
					msg="配件不足，添加失败";
					return msg;
				}
			}
			//配件充足
			for (int i = 0; i < split.length; i++) {
				DbStatistics stat = statisticsService.findStatisticsById(Integer.parseInt(split[i]));
				Integer number = stat.getStatisticsNumber();
				Integer emp = stat.getStatisticsEmp();
				Integer sy=emp+1;//配件使用
				Integer pjsy=number-sy;//配件剩余
				stat.setStatisticsNumber(number);
				stat.setStatisticsEmp(sy);
				stat.setStatisticsSyu(pjsy);
				statisticsService.updateStatistics(stat);
			}
		}
	
		// 文件名, 生成一串随机的东西, 作为文件名, 获取图片的扩展名,UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，
		String fileName1 = UUID.randomUUID().toString();
		String fileName2 = UUID.randomUUID().toString();
		//得到上传时的文件名,当然不包括路径
		String subfix = file1.getOriginalFilename();
		String subfix2 = file2.getOriginalFilename();
		//截取文件后缀类型.jpg/.png
		subfix = subfix.substring(subfix.lastIndexOf("."));
		subfix2 = subfix2.substring(subfix2.lastIndexOf("."));
        //组合后的新文件全名
		fileName1 = fileName1 + subfix;
		fileName2 = fileName2 + subfix2;
		// 获取项目的实际运行路径
		String path = "D:/upload";
		try {
            //保存图片到相应路径
			InputStream inputStream = file1.getInputStream();
			InputStream inputStream2 = file2.getInputStream();
			OutputStream outputStream = new FileOutputStream(path + "/" + fileName1);
			OutputStream outputStream2 = new FileOutputStream(path + "/" + fileName2);
			IOUtils.copy(inputStream, outputStream);
			IOUtils.copy(inputStream2, outputStream2);
			// 如果文件保存成功, 将图片路径 写入Goods对象中
			maintain.setMaintainImg(fileName1);
			maintain.setMaintainImgs(fileName2);
		} catch (IOException e) {
			msg="添加失败";
		}
		
		//根据站点编号查询站点
		DbSiteinformation site = siteinformationService.getSiteByidSiteid(maintain.getSitemainId());
		
	 boolean b = this.maintainService.insertMaintain(maintain);
	 	if(b) {
	 		String whid = maintain.getMaintainId();
			//如果维护情况 未解决 
			if(!"".equals(whid))
			{
				site.setMaintainsId(whid);
				site.setSiteId(maintain.getSitemainId());
				site.setMaintainDate(new Date());
			}
			siteinformationService.updateSiteMain(site);
			msg="修改成功";
			
		}else{
			msg="修改失败";
		}
		return msg;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 故障提交
	 */
	@ResponseBody
	@RequestMapping(value = "addFault", method = RequestMethod.POST)
	public String addFault(String siteId,HttpServletRequest request, HttpServletResponse response){
		Map<String ,String> reslut = new HashMap<>();
		if(null!=siteId)
		{
			HttpSession session = request.getSession();
			DbUser user = (DbUser)session.getAttribute("user");
			DbMaintain fault=new DbMaintain();
			fault.setFaultUserId(user.getId().toString());
			fault.setFaultUserName(user.getFullName());
			fault.setSitemainId(siteId);
			DbSiteinformation site = siteinformationService.getSiteByidSiteid(siteId);
			 boolean b  = this.maintainService.addFault(fault);
			 if(b) {
					site.setSiteId(siteId);
					site.setMaintainsId("0");
					siteinformationService.updateSiteMain(site);
					reslut.put("msg", "提交成功");
				}
		}else
		{
			reslut.put("msg", "提交失败");
		}
		return JSON.toJSONString(reslut);
	}
	
	
	/**
	 * 删除维护信息
	 */
	@ResponseBody
	@RequestMapping(value = "delMaintain", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String delMaintain(@RequestBody Integer id,HttpServletRequest request, HttpServletResponse response) {
		boolean b = this.maintainService.delMaintain(id);
		Map<String ,String> reslut = new HashMap<>();
		if(b)
		{
			reslut.put("msg", "删除成功");
		}else {
			reslut.put("msg", "删除失败");
		}
		return JSON.toJSONString(reslut);
	}
	
	
	/**
	 * 维护统计信息页面
	 */
	@RequestMapping(value = "listMaintainLogPage")
	   public String listMaintainLogPage(ModelMap model) throws Exception {
	       return "/dict/mainLog";
	   }
	
	@ResponseBody
	@RequestMapping(value = "listMaintainLog", method = RequestMethod.GET)
	public String listMaintainLog() {
		List<DbMainLog> list = this.maintainService.findDayLog();
		return JSON.toJSONString(list);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "findDayByDate", method = RequestMethod.GET)
	public String findDayByDate(String startDate,String endDate) {
		List<DbMainLog> list = this.maintainService.findDayByDate(startDate, endDate);
		return JSON.toJSONString(list);
	}
	
	
	 
}
