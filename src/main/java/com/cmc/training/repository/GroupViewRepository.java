package com.cmc.training.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.cmc.training.entity.view.ViewGroup;

/**
 * This is group repository
 * 
 * @author: VDHao
 * @Date: Mar 1, 2018
 */
public interface GroupViewRepository
    extends JpaRepository<ViewGroup, Integer>, JpaSpecificationExecutor<ViewGroup> {

  @Query(value = "From ViewGroup")
  public Page<ViewGroup> getViewGroup(Specification<ViewGroup> spec, Pageable pageable);
}
