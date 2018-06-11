package com.cmc.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmc.training.entity.Account;

/**
 * 
 * @author: NTGiang1
 * @Date: Mar 1, 2018
 */
public interface AccountRepository
    extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

  /**
   * 
   * @description: .
   * @author AnhNgoc.
   * @createDate 28-02-2018 14:36:33
   * @modifier: .
   * @modifyDate: .
   * @param keyword
   * @return List<Account>
   */
  @Query(value = "From Account a Where ((a.username like %:keyword% escape '&') or (a.fullnameSearch like %:keyword% escape '&')"
      + " or (a.departmentId.departmentNameSearch like %:keyword% escape '&') "
      + "or (a.positionId.positionNameSearch like %:keyword% escape '&')) order by username asc")
  public List<Account> getAccountBySearch(@Param("keyword") String keyword);

  /**
   * Check exists an account by using the accountId as a search criteria.
   * 
   * @param nameGroup
   * @return boolean
   */
  @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM Account WHERE account_id = :accountId")
  public boolean exists(@Param("accountId") int accountId);
}
