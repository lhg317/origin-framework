package com.goldgov.origin.modules.user.web;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;
import com.goldgov.origin.core.web.validator.Valid;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserExistException;
import com.goldgov.origin.modules.user.api.RpcUserNameCheckFailException;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;

@Controller
@RequestMapping("/user")
@ModuleResource(name="i18n:label.user+manage",code="user")
public class RpcUserController {
	
	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@RequestMapping("/preAdd")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAdd() throws TException{
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/addUser")
	@WebToken(handle=TokenHandleType.VERIFY,forward="/user/listUser")
	@ModuleOperating(name="i18n:label.user+add",type=OperateType.ADD)
	public String addUser(RpcUser user) throws TException{
		try {
			userService.addUser(user);
		} catch (RpcUserExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RpcUserNameCheckFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:/user/listUser";
	}
	
	@RequestMapping("/deleteUser")
	@ModuleOperating(name="i18n:label.user+delete",type=OperateType.DELETE)
	public String deleteUser(@RequestParam("userID") String[] ids) throws TException{
		userService.deleteUser(Arrays.asList(ids));
		return "forward:/user/listUser";
	}
	
	@RequestMapping("/updateUser")
	@ModuleOperating(name="i18n:label.user+update",type=OperateType.UPDATE)
	@WebToken(handle=TokenHandleType.VERIFY)
	public String updateUser(RpcUser user) throws TException{
		userService.updateUser(user);
		return "forward:/user/listUser";
	}
	
	@RequestMapping("/getUser")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String getUser(@RequestParam("userID") String userID,Model model) throws TException{
		RpcUser user = userService.getUser(userID);
		model.addAttribute("user", user);
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/listUser")
	public String listUser(RpcUserQuery userQuery,Model model) throws TException{
		userQuery = userService.listUser(userQuery);
		model.addAttribute("query", userQuery);
		return PAGES_BASE_PATH + "list";
	}
	
//	@RequestMapping("/findUserSelectList")
//	public String listUserSelectList(RpcUserQuery userQuery,Model model) throws TException{
//		listUser(userQuery,model);
//		return PAGES_BASE_PATH + "select";
//	}
	
	@RequestMapping("/existUser")
	public @ResponseBody String existUser(@RequestParam("loginName") String loginName,Model model) throws TException{
		boolean existUser = userService.existUser(loginName);
		return existUser?"exist":"not exist";
	}
	
	@RequestMapping("/checkUserName")
	public @ResponseBody String checkUserName(@RequestParam("userName") String userName,Model model) throws TException{
		boolean existUser = userService.checkUserName(userName);
		return existUser?"pass":"fail";
	}
}
