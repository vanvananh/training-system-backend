package com.cmc.training.util;

public class RegularExpressions {
  public static final String USERNAME_PATTERN = "[A-z0-9]{1,}";
  public static final String PASSWORD_PATTERN = "[A-z0-9@#$!^&%* `\\\\/-_]{8,32}";
  public static final String STRING_PATTERN = "[A-z0-9]{1,}";
  public static final String FIELD_NAME_PATTERN = "^[^\\s@!`~#$%^&*|\\/<>,.-][a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\\\s() ]*$";
  public static final String FIELD_ID_PATTERN = "[\\d ^0]{5}";
  public static final String SORT_TYPE_PATTERN = "(^ASC$|DESC$|asc$|desc$)";
  public static final String CATEGORY_QUESTION_SORT_FIELD_PATTERN = "(^categoryId$|categoryName$|categoryCode$|createDate$|creatorId.fullname$)";
  public static final String GROUP_SORT_FIELD_PATTERN = "(^groupName$|numberOfMembers$|fullname$|createDate$)";
  public static final String ACCOUNT_SORT_FIELD_PATTERN = "(^email$|username$|fullname$|departmentId.departmentName$|positionId.positionName$)";
  public static final String KEYWORD_SEARCH_AND_FILTER_PATTERN = "(.+?)(:|<|>|=|-|!)(.+?),";
  public static final String ACCOUNT_OF_GROUP_SORT_FIELD_PATTERN = "(^email$|fullname$|username$|departmentName$|positionName$|joinDate)";
  public static final String EXAM_SORT_FIELD_PATTERN = "(^code$|title$|categoryName$|durationValue$|numberOfQuestion$|isAssign$|createDate$|fullname$|note&|statusName)";
  public static final String QUESTION_SORT_FIELD_PATTERN = "(^categoryId.categoryName$|typeId.typeName$|createDate$|creatorId.fullname$)";
}