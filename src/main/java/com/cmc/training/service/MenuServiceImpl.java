package com.cmc.training.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmc.training.entity.Menu;
import com.cmc.training.repository.MenuRepository;

/**
 * This class is implement of Menu interface .
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
@Service
public class MenuServiceImpl implements MenuService {

  // declare menu service
  @Autowired
  private MenuRepository menuRepository;

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.MenuService#getAllMenu()
   */
  @Override
  public List<Menu> getAllMenu() {
    return menuRepository.findAll();
  }
}
