/**
 * training-system-backend - com.cmc.training.repository
 */
package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.training.entity.Config;
import com.cmc.training.entity.Position;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
public interface PositionRepository extends JpaRepository<Position, Integer>{

}
