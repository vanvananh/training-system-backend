package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.view.ViewListExam;

/**
 * This is view exam repository
 * 
 * @author: NVDong
 * @Date: Mar 14, 2018
 */
public interface ViewListExamRepository
    extends JpaRepository<ViewListExam, Integer>, JpaSpecificationExecutor<ViewListExam> {

}
