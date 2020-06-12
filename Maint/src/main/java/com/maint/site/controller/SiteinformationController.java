package com.maint.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.along.entity.DbSiteinformation;
import com.common.UUIDUtil;
import com.maint.site.service.SiteinformationService;

@Controller
@RequestMapping(value="site")
public class SiteinformationController {
	@Autowired
	SiteinformationService siteinformationService;
	
	/**
	 * 地图显示
	 */
	@ResponseBody
	@RequestMapping(value = "listMap",method = RequestMethod.POST)
	public List<DbSiteinformation> listUser(HttpServletRequest request, HttpServletResponse response,String siteTypeName) {
		List<DbSiteinformation> list = this.siteinformationService.GDAll(siteTypeName);
		return list;
	}
	
	/**
	 * 长沙(山洪)站点页面
	 */
	@RequiresPermissions("site:CsPage")
	@RequestMapping(value = "CsPage")
	   public String CsPage() throws Exception {
	       return "/site/csPage";
	   }
	/**
	 * 长沙(中小河流)站点页面
	 */
	@RequiresPermissions("site:CsRiversPage")
	@RequestMapping(value = "CsRiversPage")
	   public String CsRiversPage() throws Exception {
	       return "/site/csRiversPage";
	   }
	
	/**
	 * 浏阳市(山洪)站点页面
	 */
	@RequiresPermissions("site:LyPage")
	@RequestMapping(value = "LyPage")
	   public String LyPage() throws Exception {
	       return "/site/lyPage";
	   }
	/**
	 * 浏阳市(中小河流)站点页面
	 */
	@RequiresPermissions("site:LyRiversPage")
	@RequestMapping(value = "LyRiversPage")
	   public String LyRiversPage() throws Exception {
	       return "/site/lyRiversPage";
	   }
	/**
	 * 宁乡(山洪)站点页面
	 */
	@RequiresPermissions("site:NxPage")
	@RequestMapping(value = "NxPage")
	   public String NxPage() throws Exception {
	       return "/site/nxPage";
	   }
	
	/**
	 * 宁乡(中小河流)站点页面
	 */
	@RequiresPermissions("site:NxRiversPage")
	@RequestMapping(value = "NxRiversPage")
	   public String NxRiversPage() throws Exception {
	       return "/site/nxRiversPage";
	   }

	/**
	 * 城区(山洪)站点页面
	 */
	@RequiresPermissions("site:CqPage")
	@RequestMapping(value = "CqPage")
	   public String CqPage() throws Exception {
	       return "/site/cqPage";
	   }
	/**
	 * 城区(中小河流)站点页面
	 */
	@RequiresPermissions("site:CqRiversPage")
	@RequestMapping(value = "CqRiversPage")
	   public String CqRiversPage() throws Exception {
	       return "/site/cqRiversPage";
	   }
	
	
	/**
	 * 长沙(山洪)站点页面
	 */
	@RequiresPermissions("site:Search")
	@RequestMapping(value = "Search")
	   public String Search() throws Exception {
	       return "/site/search";
	   }
	/**
	 * 所有站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSearch", method = RequestMethod.GET)
	public String listSearch(HttpServletRequest request, HttpServletResponse response) {
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll();
		return JSON.toJSONString(list);
	}
	
	
	
	/**
	 *长沙县 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteCs", method = RequestMethod.GET)
	public String listSiteCs(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "长沙县(山洪)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	
	/**
	 * 浏阳市 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteLy", method = RequestMethod.GET)
	public String listSiteLy(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "浏阳市(山洪)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	/**
	 *宁乡 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteNx", method = RequestMethod.GET)
	public String listSiteNx(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "宁乡(山洪)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	/**
	 * 城区 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteCq", method = RequestMethod.GET)
	public String listSiteCq(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "城区(山洪)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	
	
	/**
	 *长沙县 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteCsRivers", method = RequestMethod.GET)
	public String listSiteCsRivers(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "长沙县(中小河流)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	
	/**
	 * 浏阳市 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteLyRivers", method = RequestMethod.GET)
	public String listSiteLyRivers(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "浏阳市(中小河流)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	/**
	 *宁乡 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteNxRivers", method = RequestMethod.GET)
	public String listSiteNxRivers(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "宁乡(中小河流)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	/**
	 * 城区 站点列表
	 */
	@ResponseBody
	@RequestMapping(value = "listSiteCqRivers", method = RequestMethod.GET)
	public String listSiteCqRivers(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteTypeName", "城区(中小河流)");
		List<DbSiteinformation> list = this.siteinformationService.findsiteAll(map);
		return JSON.toJSONString(list);
	}
	
	/**
	 * 添加站点
	 */
	@ResponseBody
	@RequestMapping(value = "addSite", method = RequestMethod.POST)
	public String addSite(@RequestBody DbSiteinformation site,HttpServletRequest request, HttpServletResponse response){
		String reslut;
		site.setSiteId(UUIDUtil.createUUID().substring(0, 8).toUpperCase());
		int count = this.siteinformationService.addSite(site);
		if(count>0)
		{
			reslut="添加成功";
		}else {
			reslut="添加失败";
		}
		
		return reslut;
	}
	
	/**
	 * 编辑站点
	 */
	@ResponseBody
	@RequestMapping(value = "updSite", method = RequestMethod.POST)
	public String updSite(@RequestBody DbSiteinformation site,HttpServletRequest request, HttpServletResponse response){
		String reslut;
		boolean b = this.siteinformationService.updateSiteMain(site);
		if(b)
		{
			reslut="修改成功";
		}else {
			reslut="修改失败";
		}
		return reslut;
	}
	
	
	/**
	 * 删除站点
	 */
	@ResponseBody
	@RequestMapping(value = "deleteSite", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String deleteSite(@RequestBody DbSiteinformation site,HttpServletRequest request, HttpServletResponse response) {
		int count = this.siteinformationService.deleteSite(site);
		Map<String ,String> reslut = new HashMap<>();
		if(count>0)
		{
			reslut.put("msg", "删除成功");
		}else {
			reslut.put("msg", "删除失败");
		}
		return JSON.toJSONString(reslut);
	}
	
	 
}
