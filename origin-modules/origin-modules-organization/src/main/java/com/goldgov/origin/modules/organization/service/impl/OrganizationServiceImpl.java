package com.goldgov.origin.modules.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.organization.dao.OrganizationDao;
import com.goldgov.origin.modules.organization.service.Organization;
import com.goldgov.origin.modules.organization.service.OrganizationQuery;
import com.goldgov.origin.modules.organization.service.OrganizationService;

/**
 * 组织机构管理
 * @author LiuHG
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public void addOrganization(Organization Organization) {
		organizationDao.addOrganization(Organization);
	}

	@Override
	public void deleteOrganization(String[] ids) {
		organizationDao.deleteOrganization(ids);
	}

	@Override
	public void updateOrganization(Organization organization) {
		organizationDao.updateOrganization(organization);
	}

	@Override
	public List<Organization> listOrganization(OrganizationQuery query) {
		return organizationDao.listOrganization(query);
	}

	@Override
	public Organization getOrganization(String id) {
		return organizationDao.getOrganization(id);
	}

}
