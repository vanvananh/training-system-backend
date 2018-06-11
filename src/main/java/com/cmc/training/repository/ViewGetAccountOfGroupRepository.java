package com.cmc.training.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cmc.training.entity.view.ViewGetAccountOfGroup;

/**
 * This is View Get Account Of Group repository
 * 
 * @author: NVDong
 * @Date: Mar 1, 2018
 */
public interface ViewGetAccountOfGroupRepository extends
    JpaRepository<ViewGetAccountOfGroup, Integer>, JpaSpecificationExecutor<ViewGetAccountOfGroup> {

  @Query(value = "From ViewGetAccountOfGroup")
  public Page<ViewGetAccountOfGroup> getViewGroup(Specification<ViewGetAccountOfGroup> spec,
      Pageable pageable);
}
