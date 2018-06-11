package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.Exam;

/**
 * 
 * @author: NVDong
 * @Date: Mar 14, 2018
 */
public interface ExamRepository
    extends JpaRepository<Exam, Integer>, JpaSpecificationExecutor<Exam> {
}
