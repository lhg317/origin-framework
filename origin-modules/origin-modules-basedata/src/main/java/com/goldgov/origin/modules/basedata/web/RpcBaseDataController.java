package com.goldgov.origin.modules.basedata.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;
import com.goldgov.origin.modules.basedata.api.RpcBaseData;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataCategory;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataLocale;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataService;

@Controller
@RequestMapping("/basedata")
public class RpcBaseDataController {
	
	private final String PAGE_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcBaseDataService.Client")
	private RpcBaseDataService.Iface rpcBaseDataService;
	
	@RequestMapping("/preAddLocale")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAddLocale() throws Exception{
		return PAGE_BASE_PATH + "locale/form";
	}
	
	@RequestMapping("/addLocale")
	@WebToken(handle=TokenHandleType.VERIFY)
	public String addLocale(RpcBaseDataLocale locale) throws Exception{
		rpcBaseDataService.addLocale(locale);
		return "forward:/basedata/listLocale";
	}
	
	@RequestMapping("/deleteLocale")
	public String deleteLocale(@RequestParam("id") String id) throws Exception{
		rpcBaseDataService.deleteLocale(id);
		return "forward:/basedata/listLocale";
	}
	
	@RequestMapping("/getLocale")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String getLocale(@RequestParam("localeID") String localeID) throws Exception{
		rpcBaseDataService.getLocale(localeID);
		return PAGE_BASE_PATH + "locale/form";
	}
	
	@RequestMapping("/updateLocale")
	public String updateLocale(RpcBaseDataLocale locale) throws Exception{
		rpcBaseDataService.updateLocale(locale);
		return "forward:/basedata/listLocale";
	}
	
	@RequestMapping("/listLocale")
	public String listLocale(Model model) throws Exception{
		List<RpcBaseDataLocale> listLocale = rpcBaseDataService.listLocale();
		model.addAttribute("listLocale",listLocale);
		return PAGE_BASE_PATH + "locale/list";
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(RpcBaseDataCategory category) throws Exception{
		rpcBaseDataService.addCategory(category);
		return PAGE_BASE_PATH + "data/form";
	}
	
	@RequestMapping("/preAddData")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAddData() throws Exception{
		return PAGE_BASE_PATH + "data/form";
	}
	
	@RequestMapping("/addData")
	@WebToken(handle=TokenHandleType.VERIFY)
	public String addData(RpcBaseData data) throws Exception{
		rpcBaseDataService.addData(data);
		return "forward:/basedata/listData";
	}
	
	@RequestMapping("/listData")
	public String listData(Model model,@RequestParam(name="categoryCode") String categoryCode,@RequestParam(name="dataID",required=false) String dataID) throws Exception{
		List<RpcBaseData> listData = rpcBaseDataService.listDataByParent("zh_cn",categoryCode,dataID);
		model.addAttribute("listData",listData);
		return PAGE_BASE_PATH + "data/list";
	}
	
}
