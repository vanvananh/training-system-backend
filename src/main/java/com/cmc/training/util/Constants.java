package com.cmc.training.util;

/**
 * This class is constant for application.
 * 
 * @author: NNDuy
 * @create_date: Dec 16, 2017
 * @version: 1.0
 * @modifer: NNDuy
 * @modifer_date: Dec 16, 2017
 *
 */
public class Constants {

  public static final String SESSION_USER = "user";
  public static final String PAGE_ERROR_500 = "500";
  public static final String PAGE_ERROR_403 = "403";

  public static class HTTP_STATUS {
    // SUCCESS
    public static final String OK = "200";
    public static final String CREATED = "201";
    // ERROR
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";
    public static final String NOT_FOUND = "200";
    public static final String INTERNAL_SERVER_ERROR = "500";
  }

  public static class HTTP_STATUS_MSG {
    public static final String ERROR_COMMON = "Có lỗi xẩy ra";
    public static final String ERROR_NOT_FOUND = "Tài nguyên không có sẵn";
  }

  /*
   * This class is constant url for application.
   * 
   * @author: NNDuy
   * 
   * @create_date: Dec 16, 2017
   * 
   * @version: 1.0
   * 
   * @modifer: NNDuy
   * 
   * @modifer_date: Dec 16, 2017
   **/
  public class Url {

    public class CRUD {
      public static final String CREATE = "/create";
      public static final String GET_ALL = "/getAll";
      public static final String GET_BY_ID = "/getById";
      public static final String UPDATE = "/update";
      public static final String DELETE = "/delete";
    }

    public class AccountUrl {
      public static final String GET_SEARCH = "/getBySearch";
      public static final String GET_LIST_ACC_OF_GROUP = "/getListAccByGroupID";
      public static final String GET_ALL_DEPARTMENT = "/getAllDepartment";
      public static final String GET_ALL_POSITION = "/getAllPosition";
    }

    public class GroupAccountUrl {
      public static final String GET_LIST_ACC_OF_GROUP_NO_PAGING = "/getListAccountOfGroupID";
    }

    public class QuestionUrl {
      public static final String GET_ALL_LEVEL_QUESTION = "/getAllLevelQuestion";
      public static final String GET_ALL_TYPE_QUESTION = "/getAllTypeQuestion";
      public static final String GET_ALL_CATEGORY_QUESTION = "/getAllCategoryQuestion";
    }

    public class FileUrl {
      public static final String UPLOAD_IMAGE = "/uploadImage";
    }

    public static final String API = "/api";
    public static final String MENU = "/menu";
    public static final String GROUP = "/group";
    public static final String ACCOUNT = "/account";
    public static final String TYPEQUESTION = "/typequestion";
    public static final String CATEGORY_QUESTION = "/categoryQuestion";
    public static final String QUESTION_ANSWER = "/questionAnswer";
    public static final String EXAM = "/exam";
    public static final String DEPARTMENT = "/department";
    public static final String POSITION = "/position";
    public static final String STATUS_EXAM = "/status";
    public static final String DURATION_EXAM = "/duration";
    public static final String FILE = "/file";
  }

  /**
   * This class is constant name parameter of ajax.
   * 
   * @author: NNDuy
   * @create_date: Dec 16, 2017
   * @version: 1.0
   * @modifer: NNDuy
   * @modifer_date: Dec 16, 2017
   **/
  public class Param {
    public static final String ID = "id";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_NUMBER_DEFAULT_STR = "1";
    public static final String SORT_TYPE = "sortType";
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";
    public static final String SORT_FIELD = "sortField";
    public static final String SORT_FIELD_GROUP_DEFAULT = "createDate";
    public static final String NAME_GROUP = "nameGroup";
    public static final String NAME_CATEGORY_QUESTION = "categoryName";
    public static final String SORT_FIELD_CATEGORY_QUESTION_DEFAULT = "createDate";
    public static final String SORT_FIELD_ACCOUNT_DEFAULT = "username";
    public static final String SORT_FIELD_ACCOUNT_OF_GROUP_DEFAULT = "joinDate";
    public static final String KEYWORD_SEARCH = "keywordSearch";
    public static final String SORT_FIELD_QUESTION_DEFAULT = "createDate";
    public static final String SORT_FIELD_EXAM_DEFAULT = "createDate";
    public static final String IMAGE = "image";
    public static final String PATH = "path";
  }

  /**
   * This class is constant common of application.
   * 
   * @author: NNDuy
   * @create_date: Dec 18, 2017
   * @version: 1.0
   * @modifer: NNDuy
   * @modifer_date: Dec 18, 2017
   **/
  public class Common {
    public static final String SPACE = " ";
    public static final int ZERO_INT = 0;
    public static final int ONE_INT = 1;
    public static final int FIFTY_INT = 50;
    public static final String EMPTY_STRING = "";
    public static final String DOT_ENCODE = "\\.";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String UPLOADED_FOLDER = "C://Users//Lenovo//Desktop//";
    public static final int MAX_LENGTH_STRING = 50;
    public static final long THREE_MB = 3145728;

    // question
    public static final int QUESTION_MAX_LENGTH_CONTENT = 1500;
    public static final int ANSWER_MAX_LENGTH_CONTENT = 500;
    // exam
    public static final int MAX_LENGTH_STRING_TITLE = 200;
    public static final int MAX_LENGTH_STRING_NOTE = 500;
    public static final String IMAGE = "image";
    public static final int DRAFT_STATUS_IN_EXAM = 2;
  }

  /*
   * This class is name colume filter and search.
   * 
   * @author: NNDuy
   * 
   * @create_date: Dec 24, 2017
   * 
   * @version: 1.0
   * 
   * @modifer: NNDuy
   * 
   * @modifer_date: Dec 24, 2017
   **/
  public class NameColume {
    // common
    public static final String IS_DELETED = "isDeleted";
    // category
    public static final String CATEGORY_NAME = "categoryName";
    public static final String CATEGORY_CODE = "categoryCode";
    public static final String CATEGORY_NAME_SEARCH = "categoryNameSearch";
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_FILTER_CREATE_DATE = "createDate";
    public static final String CATEGORY_FILTER_CREATOR_ID = "creatorId.accountId";

    // group
    public static final String GROUP_ID = "groupId";
    public static final String GROUP_NAME = "groupName";
    public static final String GROUP_NAME_SEARCH = "groupNameSearch";
    public static final String GROUP_MEMBERS_FILTER = "numberOfMembers";
    public static final String GROUP_CREATE_DATE_FILTER = "createDate";

    public static final String GROUP_ACCOUNT_DEPARTMENT = "departmentId";
    public static final String GROUP_ACCOUNT_POSITION = "positionId";
    public static final String GROUP_ACCOUNT_DATE_JOINED_FROM = "joinDate";
    public static final String GROUP_ACCOUNT_DATE_JOINED_TO = "joinDate";

    // position
    public static final String POSITION_NAME_SEARCH = "positionNameSearch";
    public static final String POSITION_NAME = "positionName";

    // department
    public static final String DEPARTMENT_NAME_SEARCH = "departmentNameSearch";
    public static final String DEPARTMENT_NAME = "departmentName";

    // account
    public static final String FULL_NAME_SEARCH = "fullnameSearch";
    public static final String FULL_NAME = "fullname";
    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCOUNT_FILTER_GROUP_ID = "groupAccountCollection.groupId.groupId";
    public static final String ACCOUNT_FILTER_DEPARTMENT_ID = "departmentId.departmentId";
    public static final String ACCOUNT_FILTER_POSITION_ID = "positionId.positionId";
    // Question
    public static final String QUESTION_SEARCH = "contentSearch";
    public static final String QUESTION_ID = "questionId";
    public static final String QUESTION_CONTENT = "questionId";
    public static final String QUESTION_CATEGORY_FILTER = "categoryId";
    public static final String QUESTION_TYPE_FILTER = "typeId";
    public static final String QUESTION_LEVEL_FILTER = "levelId";
    public static final String QUESTION_CREATE_USER_FILTER = "creatorId";
    public static final String QUESTION_CREATE_DATE_FILTER = "createDate";
    // exam
    public static final String EXAM_TITLE = "title";
    public static final String EXAM_TITLE_SEARCH = "titleSearch";
    public static final String EXAM_ID = "examId";
    public static final String EXAM_TYPE_FILTER = "categoryId";
    public static final String EXAM_TIMES_FILTER = "durationValue";
    public static final String EXAM_QUESTIONS_FILTER = "numberOfQuestion";
    public static final String EXAM_CREATE_DATE_FILTER = "createDate";
    public static final String EXAM_CREATE_USER_FILTER = "creatorId";
    public static final String EXAM_STATUS_FILTER = "statusId";
  }

  public class Config {
    public static final int NUMBER_RECORD_PER_PAGE_DEFAULT = 1;
    public static final int NUMBER_PAGE_DEFAULT = 2;
    public static final int FOLDER_SAVE_IMAGE = 3;
  }

  public enum Operation {
    EQUAL(0), LIKE(1), FULL_TEXT_SEARCH(3), IN(4), GREATER_THAN_OR_EQUAL_DATE(
        5), LESS_THAN_OR_EQUAL_DATE(6), GREATER_THAN_OR_EQUAL_INT(7), LESS_THAN_OR_EQUAL_INT(8);
    private final int value;

    private Operation(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

  }

  /**
   * This class is constant in Database.
   * 
   * @author: NNDuy
   * @Date: Mar 19, 2018
   */
  public static class Database {
    public static final int TYPE_MULTI_CHOISE = 2;
  }

}
