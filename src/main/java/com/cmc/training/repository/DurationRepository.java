package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.Duration;

/**
 * 
 * @author: NTVAnh1
 * @Date: Mar 14, 2018
 */
public interface DurationRepository
    extends JpaRepository<Duration, Integer>, JpaSpecificationExecutor<Duration> {
}
