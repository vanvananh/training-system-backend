package com.cmc.training.util;

public class ValidateChracterUtils {

  private ValidateChracterUtils() {
  }

  public static boolean isInteger(String str) {
    if (str == null || !str.matches("[0-9]+$")) {
      return false;
    }
    return true;
  }

  public static String validateKeySearch(String str) {
    return str.replaceAll("_", "&_").replaceAll("%", "&%");
  }
}
