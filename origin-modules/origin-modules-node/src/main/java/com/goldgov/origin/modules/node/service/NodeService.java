package com.goldgov.origin.modules.node.service;

import java.util.List;

import com.goldgov.origin.core.service.Query;

public interface NodeService {

	void addNode(Node node);
	void updateNode(Node node);
	Node getNode(String nodeID);
	List<Node> listNode(Query<Node> query);
}
