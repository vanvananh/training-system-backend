/**
 * 
 */
package com.cmc.training.dto;

import com.cmc.training.entity.Group;

/**
 * 
 * @author: NTGiang1
 * @Date: Mar 1, 2018
 */
public class GroupDTO {
  private Integer groupId;
  private String groupName;
  private Integer creatorId;

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Integer getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  /**
   * 
   * function convert GroupDTO to Group entity
   * 
   * @return Group
   * @author: NTGiang1
   */
  public Group toEntity() {
    // create dto
    if (groupId == null) {
      return new Group(groupName, creatorId);
    }
    // update dto
    return new Group(groupId, groupName);
  }
}
