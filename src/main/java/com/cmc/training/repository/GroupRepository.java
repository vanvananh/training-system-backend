package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.Group;

/**
 * This is group repository
 * 
 * @author: VDHao
 * @Date: Mar 1, 2018
 */
public interface GroupRepository
    extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {

}