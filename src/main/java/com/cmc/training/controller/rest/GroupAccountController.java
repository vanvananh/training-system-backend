/**
 * training-system-backend - com.cmc.training.controller.rest
 */
package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.dto.GroupAccountDTO;
import com.cmc.training.service.GroupAccountService;
import com.cmc.training.util.ApiMessage;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;

/**
 * @author: nvangoc
 * @Date: 20 Mar 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/groupAccount")
public class GroupAccountController {
  @Autowired
  private GroupAccountService groupAccountService;

  /**
   * 
   * TODO description. this function insert account to group
   * 
   * @param groupAccountDTOs
   * @return ResponseEntity<?>
   * @author: nvangoc
   */
  @PostMapping(value = "/updateAccountToGroup")
  public ResponseEntity<?> updateAccountToGroup(
      @RequestBody List<GroupAccountDTO> groupAccountDTOs) {
    if (MethodUtil.checkListIsNull(groupAccountDTOs)) {
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.GroupAccount.GROUP_ACCOUNT_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    groupAccountService.updateAccountToGroup(groupAccountDTOs);
    return new ResponseEntity<ApiMessage>(
        new ApiMessage(HttpStatus.OK, MessageUtil.GroupAccount.UPDATE_ACCOUNT_GROUP_SUCCESS),
        HttpStatus.OK);
  }

}
