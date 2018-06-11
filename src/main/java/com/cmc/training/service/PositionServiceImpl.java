/**
 * training-system-backend - com.cmc.training.service
 */
package com.cmc.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.Department;
import com.cmc.training.entity.Position;
import com.cmc.training.repository.PositionRepository;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
@Service
public class PositionServiceImpl implements PositionService{
  @Autowired
  private PositionRepository positionRepository;
  /* (non-Javadoc)
   * @see com.cmc.training.service.PositionService#getAllPosition()
   */
  @Override
  public List<Position> getAllPosition() {
    return positionRepository.findAll();
  }
 
}
