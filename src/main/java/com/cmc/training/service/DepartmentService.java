/**
 * training-system-backend - com.cmc.training.service
 */
package com.cmc.training.service;

import java.util.List;

import com.cmc.training.entity.Department;
import com.cmc.training.entity.TypeQuestion;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
public interface DepartmentService {
  public List<Department> getAllDepartment();
}
