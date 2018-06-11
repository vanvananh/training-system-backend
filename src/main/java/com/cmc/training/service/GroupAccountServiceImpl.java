/**
 * training-system-backend - com.cmc.training.service
 */
package com.cmc.training.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.training.dto.GroupAccountDTO;
import com.cmc.training.entity.GroupAccount;
import com.cmc.training.repository.GroupAccountRepository;
import com.cmc.training.util.Constants;
import com.cmc.training.util.MethodUtil;

/**
 * @author: nvangoc
 * @Date: 5 Mar 2018
 */
@Service
@Transactional
public class GroupAccountServiceImpl implements GroupAccountService {

  @Autowired
  private GroupAccountRepository groupAccountRepository;

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.GroupAccountService#getAccountOfGroup(int)
   */
  @Override
  public List<GroupAccount> getAccountsOfGroup(int groupId) {
    return groupAccountRepository.getAccountsOfGroup(groupId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.GroupAccountService#insertAccountToGroup(java.util.
   * List)
   */
  @Override
  public void updateAccountToGroup(List<GroupAccountDTO> groupAccountDTOs) {
    List<GroupAccount> groupAccounts = new ArrayList<>();
    List<Integer> accountIds = new ArrayList<>();
    for (GroupAccountDTO groupAccountDTO : groupAccountDTOs) {
      if (groupAccountDTO.isExistInGroup()) {
        groupAccountDTO.setJoinDate(new Date());
        groupAccounts.add(groupAccountDTO.toEntity());
      } else {
        accountIds.add(groupAccountDTO.getAccountId());
      }
    }
    if (!MethodUtil.checkListIsNull(groupAccounts)) {
      groupAccountRepository.save(groupAccounts);
    }
    if (!MethodUtil.checkListIsNull(accountIds)) {
      groupAccountRepository.removeAccountFromGroup(accountIds,
          groupAccountDTOs.get(Constants.Common.ZERO_INT).getGroupId());
    }
  }
}
