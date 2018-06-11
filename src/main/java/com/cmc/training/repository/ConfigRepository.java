package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.training.entity.Config;

/**
 * This class is Repository of Config.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface ConfigRepository extends JpaRepository<Config, Integer> {

}
