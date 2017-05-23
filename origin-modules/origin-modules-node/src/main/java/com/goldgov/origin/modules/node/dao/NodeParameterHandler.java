package com.goldgov.origin.modules.node.dao;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.dao.mybatis.InterceptorHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;
import com.goldgov.origin.modules.node.service.Node;

//@Component
//@Order(Ordered.LOWEST_PRECEDENCE - 10)
public class NodeParameterHandler implements InterceptorHandler{

	@Override
	public void doHandle(BoundSql boundSql, MetaObject metaObject, Connection connection) throws Throwable{
		HttpServletRequest request = RequestHolder.getRequest();
		HttpSession session = request.getSession();
		Object nodeID = session.getAttribute(Node.NODE_SESSION_KEY);
		if(nodeID != null){
			boundSql.setAdditionalParameter("$nodeID$", nodeID);
		}
	}

	@Override
	public boolean supports(BoundSql boundSql) throws Throwable{
		return true;
	}

}
