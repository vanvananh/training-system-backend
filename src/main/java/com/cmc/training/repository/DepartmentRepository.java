/**
 * training-system-backend - com.cmc.training.repository
 */
package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.training.entity.Department;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
