/**
 * training-system-backend - com.cmc.training.service
 */
package com.cmc.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.Department;
import com.cmc.training.repository.DepartmentRepository;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
  @Autowired
  private DepartmentRepository departmentRepository;
  @Override
  public List<Department> getAllDepartment() {
    return departmentRepository.findAll();
  }

}
