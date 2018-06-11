package com.cmc.training.service;

import java.sql.SQLException;
import java.util.List;

import com.cmc.training.entity.RolePermission;

public interface RolePermissionService {

	/**
	 * Get All RolePermission
	 * @return
	 * @throws SQLException List<RolePermission> 
	 * @author: ngocd
	 */
	public List<RolePermission> getAllRolePermission() throws SQLException;

	/**
	 * Save all RolePermission of List
	 * @param listRolePermission
	 * @return
	 * @throws SQLException String 
	 * @author: ngocd
	 */
	public String saveAllRolePermission(List<RolePermission> listRolePermission) throws SQLException;

}
