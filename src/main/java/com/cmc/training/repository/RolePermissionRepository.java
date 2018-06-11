package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.training.entity.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

}
