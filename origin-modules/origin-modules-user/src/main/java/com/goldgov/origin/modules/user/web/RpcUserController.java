package com.goldgov.origin.modules.user.web;

import java.util.Arrays;

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
import com.goldgov.origin.modules.auth.api.RpcAuthAccount;
import com.goldgov.origin.modules.auth.api.RpcAuthAccountService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserExistException;
import com.goldgov.origin.modules.user.api.RpcUserNameCheckFailException;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;

@Controller
@RequestMapping("/user")
@ModuleResource(name="i18n:label.user+manage",code="user")
public class RpcUserController {
	
	private final String PAGE_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Autowired
	@Qualifier("rpcAuthAccountService.Client")
	private RpcAuthAccountService.Iface authAccountService;
	
	@RequestMapping("/preAdd")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAdd() throws Exception{
		return PAGE_BASE_PATH + "form";
	}
	
	@RequestMapping("/addUser")
	@WebToken(handle=TokenHandleType.VERIFY,forward="/user/listUser")
	@ModuleOperating(name="i18n:label.user+add",type=OperateType.ADD)
	public String addUser(@Valid RpcUser user,@RequestParam("password")String password) throws Exception{
		String userID;
		try {
			userID = userService.addUser(user);
		} catch (RpcUserExistException e) {
			throw new RuntimeException("登录名重复："+user.getLoginName(),e);
		} catch (RpcUserNameCheckFailException e) {
			throw new RuntimeException("用户名检查失败："+user.getUserName(),e);
		}
		
		RpcAuthAccount authAccount = new RpcAuthAccount();
		authAccount.setAccountID(userID);
		authAccount.setPrincipal(user.getLoginName());
		authAccount.setPassword(password);
		authAccountService.addAuthAccount(authAccount);
		return "forward:/user/listUser";
	}
	
	@RequestMapping("/deleteUser")
	@ModuleOperating(name="i18n:label.user+delete",type=OperateType.DELETE)
	public String deleteUser(@RequestParam("userID") String[] ids) throws Exception{
		authAccountService.deleteAuthAccount(Arrays.asList(ids));
		userService.deleteUser(Arrays.asList(ids));
		return "forward:/user/listUser";
	}
	
	@RequestMapping("/updateUser")
	@ModuleOperating(name="i18n:label.user+update",type=OperateType.UPDATE)
	@WebToken(handle=TokenHandleType.VERIFY)
	public String updateUser(RpcUser user) throws Exception{
		userService.updateUser(user);
		return "forward:/user/listUser";
	}
	
	@RequestMapping("/getUser")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String getUser(@RequestParam("userID") String userID,Model model) throws Exception{
		RpcUser user = userService.getUser(userID);
		model.addAttribute("user", user);
		return PAGE_BASE_PATH + "form";
	}
	
	@RequestMapping("/listUser")
	public String listUser(RpcUserQuery userQuery,Model model) throws Exception{
		userQuery = userService.listUser(userQuery);
		model.addAttribute("query", userQuery);
		return PAGE_BASE_PATH + "list";
	}
	
//	@RequestMapping("/findUserSelectList")
//	public String listUserSelectList(RpcUserQuery userQuery,Model model) throws TException{
//		listUser(userQuery,model);
//		return PAGES_BASE_PATH + "select";
//	}
	
	@RequestMapping("/existUser")
	public @ResponseBody String existUser(@RequestParam("loginName") String loginName,Model model) throws Exception{
		boolean existUser = userService.existUser(loginName);
		return existUser?"true":"false";
	}
	
	@RequestMapping("/checkUserName")
	public @ResponseBody String checkUserName(@RequestParam("userName") String userName,Model model) throws Exception{
		boolean checkResult = userService.checkUserName(userName);
		return checkResult?"success":"fail";
	}
}
