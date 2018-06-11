package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.training.entity.Menu;

/**
 * This class is Repository of Menu.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
