package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.cmc.training.entity.Permission;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

  @Query(value = "select permissions.permission_name from permissions", nativeQuery = true)
  List<String> getAllPermission();
}
