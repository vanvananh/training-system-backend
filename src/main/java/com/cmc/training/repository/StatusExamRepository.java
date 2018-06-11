package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.StatusExam;

/**
 * This is status exam repository
 * 
 * @author: NVDong
 * @Date: Mar 14, 2018
 */
public interface StatusExamRepository
    extends JpaRepository<StatusExam, Integer>, JpaSpecificationExecutor<StatusExam> {

}
