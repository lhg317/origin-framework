package com.goldgov.origin.modules.user.web;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;

@Controller
@RequestMapping("/user")
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
	public String addUser(RpcUser user) throws TException{
		userService.addUser(user);
		return "forward:/user/findUserList";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("userID") Integer[] ids) throws TException{
		userService.deleteUser(Arrays.asList(ids));
		return "forward:/user/findUserList";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(RpcUser user) throws TException{
		userService.updateUser(user);
		return "forward:/user/findUserList";
	}
	
	@RequestMapping("/findUser")
	public String findUserByID(@RequestParam("userID") int userID,Model model) throws TException{
		RpcUser user = userService.findUserByID(userID);
		model.addAttribute("user", user);
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/findUserList")
	public String findUserList(RpcUserQuery userQuery,Model model) throws TException{
		userQuery = userService.findUserList(userQuery);
		model.addAttribute("query", userQuery);
		return PAGES_BASE_PATH + "list";
	}
}
