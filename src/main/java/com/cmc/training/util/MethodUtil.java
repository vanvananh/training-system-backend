package com.cmc.training.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cmc.training.dto.LoginParameterObject;

public class MethodUtil {

  /**
   * 
   * TODO description this function replace % and _ character
   * 
   * @param str
   * @return String
   * @author: nvangoc
   */
  public static String formatKeySearch(String str) {
    return str.replaceAll("%", "&%").replaceAll("_", "&_");
  }

  /**
   * 
   * TODO description this function return string is integer or not
   * 
   * @param str
   * @return boolean
   * @author: nvangoc
   */
  public static boolean isInteger(String str) {
    if (str == null || !str.matches("[0-9]+$")) {
      return false;
    }
    return true;
  }

  /**
   * function to paging result list, use on Service
   * 
   * @param paging
   *          - object paging.
   * @param sortings
   *          - List object sorting
   * @return PageRequest
   * @author: NNDuy
   */
  public static PageRequest Pagination(Paging paging, Sorting sorting) {
    // return PageRequest
    return new PageRequest(paging.getPageNumber() - 1, paging.getNumberRecordPerPage(),
        convertSort(sorting));
  }

  /**
   * convert sorting model --> sort of spring
   * 
   * @param sorting
   *          - list input model
   * @return Sort - sort of spring
   * @author: NNDuy
   */
  public static Sort convertSort(Sorting sorting) {
    // init field sort index 0
    Sort sort = new Sort(sorting.getSortTypes().get(0), sorting.getSortFields().get(0));
    // init field sort index from 1 --> end
    for (int i = 1; i < sorting.getSortTypes().size() && i < sorting.getSortFields().size(); i++) {
      sort = sort.and(new Sort(sorting.getSortTypes().get(i), sorting.getSortFields().get(i)));
    }
    return sort;
  }

  /**
   * function to paging result list, use on Service
   * 
   * @param paging
   *          - object paging.
   * @return PageRequest
   * @author: NNDuy
   */
  public static PageRequest Pagination(Paging paging) {
    return new PageRequest(paging.getPageNumber() - 1, paging.getNumberRecordPerPage());
  }

  /**
   * Check object null.
   * 
   * @param object
   * @return boolean
   * @author: Hoai-Nam
   */
  public static boolean isNull(Object object) {
    return object == null || "".equals(object);
  }

  /**
   * check list null.
   * 
   * @param list
   * @return boolean
   * @author: Hoai-Nam
   */
  public static boolean checkListIsNull(List<?> list) {
    return list == null || list.size() == 0;
  }

  /**
   * method convert password get from client into sha1 code.
   * 
   * @param input
   * @return String
   * @author: Hoai-Nam
   */
  public static String sha1(String input) {
    if (input == null || input.equalsIgnoreCase("")) {
      return input;
    }
    try {
      MessageDigest mDigest;
      mDigest = MessageDigest.getInstance("SHA1");
      byte[] result = mDigest.digest(input.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < result.length; i++) {
        sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Method check input login;
   * 
   * @param data
   * @param regexter
   * @return boolean
   * @author: Hoai-Nam
   */
  public static boolean validateLoginParams(String data, String regexter) {
    return (data == null || regexter == null || "".equals(data)) ? false : data.matches(regexter);
  }

  /**
   * method get parameter request from client.
   * 
   * @param passedParams
   * @return LoginParameterObject
   * @author: Hoai-Nam
   */
  public static LoginParameterObject getLoginParamsFromString(String passedParams) {
    if (passedParams == null || "".equals(passedParams)) {
      return null;
    }
    try {
      JSONObject object = new JSONObject(passedParams);
      String username = object.getString("username");
      String password = object.getString("password");
      return new LoginParameterObject(username, password);

    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Compare between two parame.
   * 
   * @param paramter1
   * @param paramter2
   * @return boolean
   * @author: Hoai-Nam
   */
  public static boolean compare(String paramter1, String paramter2) {
    return (paramter1.equals(paramter2));
  }

  /**
   * Check user role has access api request
   * 
   * @param role
   * @return true if has role else false
   */
  public static boolean hasRole(Role role) {
    Collection<? extends GrantedAuthority> auths = SecurityContextHolder.getContext()
        .getAuthentication().getAuthorities();
    return auths == null ? false
        : auths.stream().anyMatch(ga -> ga.getAuthority().equals(role.getName())
            || ga.getAuthority().equals(Role.ADMIN.getName()));

  }

  /**
   * This method is check regular.
   * 
   * @author: NNDuy
   * @create_date: Feb 23, 2018
   * @version: 1.0
   * @modifer: NNDuy
   * @modifer_date: Feb 23, 2018
   * @param input
   *          - input string.
   * @param regular
   *          - Regular Expression.
   * @return boolean
   */
  public static boolean checkRegularExpression(String input, String regular) {
    // validate input
    if (isNull(input)) {
      return false;
    }
    // validate success
    return Pattern.compile(regular).matcher(input).matches();
  }

  /**
   * validate length of String
   * 
   * @param input
   *          - input String
   * @param maxlength
   *          - Max length of String
   * @return boolean
   * @author: NNDuy
   */
  public static boolean validateStringlength(String input, int maxlength) {
    if (input.length() <= maxlength) {
      return true;
    }
    return false;
  }

  /**
   * remove Accent from input String
   * 
   * @param input
   *          - input String
   * @return String - String has remove Accent
   * @author: NNDuy
   */
  public static String removeAccent(String input) {
    String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
  }

  public static String convertContentSearch(String input) {
    if (!isNull(input)) {
      return MethodUtil.removeAccent(input) + " " + input;
    }
    return null;
  }

  /**
   * get link project current in disk
   * 
   * @return String
   * @author: NNDuy
   */
  public static String getLinkProject() {
    return System.getProperty("user.dir");
  }

  public static String decodeUTF8(String input) throws UnsupportedEncodingException {
    return URLDecoder.decode(input, "UTF-8");
  }

}
