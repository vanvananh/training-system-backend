package com.cmc.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.training.repository.ConfigRepository;
import com.cmc.training.util.Constants;

/**
 * This class is implement of ConfigService interface .
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
@Service
public class ConfigServiceImpl implements ConfigService {

  // declare config Repository
  @Autowired
  private ConfigRepository configRepository;

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.ConfigService#getNumberPageDefault()
   */
  @Override
  public int getNumberPageDefault() {
    return Integer
        .parseInt(configRepository.findOne(Constants.Config.NUMBER_PAGE_DEFAULT).getConfigValue());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.ConfigService#getNumberRecordPerPageDefault()
   */
  @Override
  public int getNumberRecordPerPageDefault() {
    return Integer.parseInt(
        configRepository.findOne(Constants.Config.NUMBER_RECORD_PER_PAGE_DEFAULT).getConfigValue());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.ConfigService#getFolderSaveImage()
   */
  @Override
  public String getFolderSaveImage() {
    return configRepository.findOne(Constants.Config.FOLDER_SAVE_IMAGE).getConfigValue();
  }

}
