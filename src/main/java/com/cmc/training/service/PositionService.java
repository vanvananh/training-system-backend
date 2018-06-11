/**
 * training-system-backend - com.cmc.training.service
 */
package com.cmc.training.service;

import java.util.List;

import com.cmc.training.entity.Position;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
public interface PositionService {
  /**
   * 
   * TODO get all position
   * @return List<Position> 
   * @author: nhanh3
   */
  public List<Position> getAllPosition();
}
