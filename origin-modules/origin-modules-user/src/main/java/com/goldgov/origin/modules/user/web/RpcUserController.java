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
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserExistException;
import com.goldgov.origin.modules.user.api.RpcUserNameCheckFailException;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.modules.user.exception.UserExistException;

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
	@WebToken(handle=TokenHandleType.VERIFY)
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
		return "forward:/user/findUsers";
	}
	
	@RequestMapping("/deleteUser")
	@ModuleOperating(name="i18n:label.user+delete",type=OperateType.DELETE)
	public String deleteUsers(@RequestParam("userID") Integer[] ids) throws TException{
		userService.deleteUsers(Arrays.asList(ids));
		return "forward:/user/findUsers";
	}
	
	@RequestMapping("/updateUser")
	@ModuleOperating(name="i18n:label.user+update",type=OperateType.UPDATE)
	public String updateUser(RpcUser user) throws TException{
		userService.updateUser(user);
		return "forward:/user/findUsers";
	}
	
	@RequestMapping("/findUser")
	public String findUserByID(@RequestParam("userID") int userID,Model model) throws TException{
		RpcUser user = userService.findUserByID(userID);
		model.addAttribute("user", user);
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/findUsers")
	public String findUsers(RpcUserQuery userQuery,Model model) throws TException{
		userQuery = userService.findUsers(userQuery);
		model.addAttribute("query", userQuery);
		return PAGES_BASE_PATH + "list";
	}
	
	@RequestMapping("/findUserSelectList")
	public String findUserSelectList(RpcUserQuery userQuery,Model model) throws TException{
		findUsers(userQuery,model);
		return PAGES_BASE_PATH + "select";
	}
	
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
