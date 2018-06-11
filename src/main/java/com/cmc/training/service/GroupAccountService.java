/**
 * training-system-backend - com.cmc.training.service
 */
package com.cmc.training.service;

import java.util.List;

import com.cmc.training.dto.GroupAccountDTO;
import com.cmc.training.entity.GroupAccount;

/**
 * @author: nvangoc
 * @Date: 5 Mar 2018
 */
public interface GroupAccountService {
  /**
   * 
   * TODO description description this function return list account of group
   * 
   * @param groupId
   * @return List<GroupAccount>
   * @author: nvangoc
   */
  public List<GroupAccount> getAccountsOfGroup(int groupId);

  /**
   * 
   * TODO description. this function insert account to group
   * 
   * @param groupAccounts
   * @return List<GroupAccount>
   * @author: nvangoc
   */
  public void updateAccountToGroup(List<GroupAccountDTO> groupAccountDTOs);

}
