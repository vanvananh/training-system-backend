/**
 * 
 */
package com.cmc.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmc.training.entity.GroupAccount;
import com.cmc.training.util.Constants;

/**
 * @author AnhNgoc
 *
 */

public interface GroupAccountRepository extends JpaRepository<GroupAccount, Integer> {
  /**
   * 
   * TODO description this function return list account of group
   * 
   * @return List<GroupAccount>
   * @author: nvangoc
   */
  @Query(value = "from GroupAccount ga where ga.groupId.groupId = :id")
  public List<GroupAccount> getAccountsOfGroup(@Param(Constants.Param.ID) int groupId);

  /**
   * 
   * TODO description this function remove account from group
   * 
   * @param accountIds
   *          void
   * @author: nvangoc
   */
  @Modifying
  @Query(value = "delete from GroupAccount ga where ga.accountId.accountId in (:accountIds) and ga.groupId.groupId = :groupId")
  public void removeAccountFromGroup(@Param("accountIds") List<Integer> accountIds,
      @Param("groupId") int groupId);
}
