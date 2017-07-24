package com.goldgov.origin.modules.auth.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.auth.service.AuthAccount;

@Mapper
public interface AuthenticationDao {

	public void addAuthAccount(AuthAccount authAccount);
	
	public AuthAccount getAuthAccount(@Param("principal")String principal, @Param("password")String password);
	
	public void updatePassword(@Param("principal")String principal,@Param("oldPass")String oldPass,@Param("newPass")String newPass);
	
	public void updateExpiredDate(@Param("principal")String principal,@Param("expiredDate") Date expiredDate);
	
	public void setEnabled(@Param("principal")String principal,@Param("enabled")boolean enabled);
	
	public void setLocked(@Param("principal")String principal,@Param("locked") boolean locked);
	
	public void deleteAuthAccount(@Param("ids") String[] ids);
	
	public void deleteAuthAccountByPrincipal(@Param("principal") String[] ids);
	
	public String getPassword(String principal);
}
