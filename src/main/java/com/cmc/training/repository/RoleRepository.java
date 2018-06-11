package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.training.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
