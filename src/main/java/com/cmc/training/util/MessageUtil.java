package com.cmc.training.util;

public class MessageUtil {
  public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
  public static final String LOGIN_ERROR = "LOGIN_ERROR";
  public static final String UNSIGNED_YET = "UNSIGNED_YET";
  public static final String USER_NONEXIST = "USER_NONEXIST";
  public static final String USERNAME_OR_PASSWORD_INVALID = "USERNAME_OR_PASSWORD_INVALID";
  public static final String NO_CONTENT = "NO_CONTENT";

  public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
  public static final String UPDATE_ERROR = "UPDATE_ERROR";
  public static final String DATA_INVALID = "DATA_INVALID";
  public static final String DATA_VALID = "DATA_VALID";
  public static final String DATA_EMPTY = "DATA_EMPTY";
  public static final String DATA_NOT_EXIST = "DATA_NOT_EXIST";
  public static final String DATA_UNFORMAT = "DATA_UNFORMAT";
  public static final String DATABASE_ERROR = "DATABASE_ERROR";

  //
  public static final String CREATE_FILE_SUCCESS = "CREATE_FILE_SUCCESS";
  public static final String CREATE_FILE_FAIL = "CREATE_FILE_FAIL";

  public static final String GET_SUCCESS = "GET_SUCCESS";
  public static final String GET_ERROR = "GET_ERROR";

  // Permission Manage
  public static final String SAVE_SUCCESS = "SAVE_SUCCESS";
  public static final String SAVE_ERROR = "SAVE_ERROR";

  public static final String CREATED_PLAN_SUCCESSFULLY = "CREATED_PLAN_SUCCESSFULLY";
  public static final String INTERNAL_ERROR_HAPPENED = "INTERNAL_ERROR_HAPPENED";
  public static final String PARAMETERS_WERE_INVALID = "PARAMETERS_WERE_INVALID";

  public static final String PARAMETER_SORTTYPE_NOT_VALID = "Parameter sortType not valid";
  public static final String PARAMETER_SORTFIELD_NOT_VALID = "Parameter sortField not valid";
  public static final String PARAMETER_PAGENUMBER_NOT_VALID = "Parameter pageNumber not valid";

  public static class Group {
    public static final String PARAMETER_GROUPNAME_NOT_VALID = "Parameter groupName not valid";
    public static final String GROUP_NAME_EXISTS = "Group name exists";
    public static final String GROUP_NAME_NOT_VALID = "Group name is not valid";
    public static final String GROUP_ID_NOT_VALID = "Group id is not valid";
    public static final String GROUP_ID_NOT_EXISTS = "Group id is not exists";
  }

  public static class Account {
    public static final String ACCOUNT_NOTEXISTS = "Account not exists";
    public static final String PARAMETER_KEYWORD_SEARCH_NOT_VALID = "Parameter KeywordSearch not valid";
    public static final String CREATOR_ID_NOT_EXISTS = "creatorId not exists";
  }

  public static class CategoryQuestion {
    public static final String PARAMETER_CATEGORYNAME_NOT_VALID = "Parameter CategoryName not valid";
    public static final String PARAMETER_CATEGORY_CODE_NOT_VALID = "Parameter CategoryCode not valid";
    public static final String PARAMETER_CATEGORY_ID_NOT_VALID = "Parameter categoryId not valid";
    public static final String PARAMETER_CREATORID_NOT_VALID = "Parameter CreatorId not valid";
    public static final String CREATOR_ID_NOT_EXISTS = "creatorId not exists";
    public static final String CATEGORY_NAME_EXISTS = "Name Category exists";
    public static final String CATEGORY_QUESTION_NAME_NOT_VALID = "Category Question name is not valid";
    public static final String CATEGORY_QUESTION_ID_NOT_EXISTS = "Category Question id is not exists";
  }

  public static class Question {
    public static final String LEVEL_QUESTION_ID_NOT_EXISTS = "levelId not exists";
    public static final String TYPE_QUESTION_ID_NOT_EXISTS = "typeId not exists";
    public static final String PARAMETER_CONTENT_NOT_VALID = "Parameter content not valid";
    public static final String PARAMETER_IS_CORRECT_NOT_VALID = "Parameter isCorrect not valid";
    public static final String PARAMETER_IS_CORRECT_HAVE_LEAST_ONE = "Question is must be at least one answer is correct";
    public static final String QUESTION_HAVE_LEAST_TWO_ANSWER_AND_MAX_FIVE_ANSWER = "Question is must be at least 2 answer and max 5 answers";
    public static final String NO_SUGGESSION = "No suggession";
    public static final String UPLOAD_IMAGE_NOT_SUCCESS = "Upload image not success";
    public static final String LINK_IMAGE_NOT_EXIST = "link image not exist";
  }

  public static class Exam {
    public static final String STATUS_EXAM_IS_PUBLIC = "Status Exam is public. Cannot delete";
    public static final String EXAM_TITLE_NOT_VALID = "Parameter title is not valid";
    public static final String EXAM_NOTE_NOT_VALID = "Parameter note is not valid";
  }

  public static class GroupAccount {
    public static final String GROUP_ACCOUNT_NOT_VALID = "Group Account parameter not valid";
    public static final String UPDATE_ACCOUNT_GROUP_SUCCESS = "Update group successful";
  }

  public static final String PARAMETER_ID_NOT_VALID = "Parameter id not exists";
  public static final String PARAMETER_LIST_ID_NOT_VALID = "Parameter List id not valid";
  public static final String PARAMETER_LIST_ID_NOT_EXIST = "Parameter List id not exist";
  public static final String PARAMETER_NOT_VALID = "Parameter not valid";
  public static final String DELETE_SUCCESSFULLY = "Delete successfully";

  public static class File {
    public static final String IMAGE_NOT_VALID = "Parameter image not valid";
    public static final String FILE_NOT_VALID = "Parameter file not valid";
    public static final String FILE_NOT_IMAGE = "File not image";
    public static final String FILE_NOT_EXIST = "File not exist";
    public static final String IMAGE_LARGE_THREE_MB = "Image is must less 3MB";
    public static final String UPLOAD_IMAGE_SUCCESSFULLY = "Upload Image successfully";
    public static final String FILE_OR_FOLDER_NOT_EXIST = "Error! File not Exist.";
    public static final String FILE_OR_FOLDER_EXIST = "Error! File or Folder Exist.";
    public static final String CREATE_FILE_SUCCESS = "Create file success!";
    public static final String CREATE_FOLDER_SUCCESS = "Create folder success!";
    public static final String RENAME_FILE_SUCCESS = "Rename file success!";
    public static final String NOT_FOLDER = "Error! Not folder.";
    public static final String DELETE_FILE_SUCCESS = "Delete file success!";
    public static final String FILE_SOURCE_NOT_EXIST = "Error! File source not Exist.";
    public static final String FILE_DESTINATION_EXIST = "Error! File destination Exist.";
    public static final String PATH_NOT_DECODE = "Path not decode UTF-8";

  }

}
