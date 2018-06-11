/**
 * 
 */
package com.cmc.training.util.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.cmc.training.util.Constants;
import com.cmc.training.util.Constants.Operation;

/**
 * @author: NNDuy
 * @Date: Mar 3, 2018
 */
public class CriteriaCustom {
  private List<String> key;
  private Operation operation;
  private Object value;

  /**
   * Constructure
   */
  public CriteriaCustom(String key, Operation operation, Object value) {
    // convert Category.Account to List key
    parseKey(key);
    this.operation = operation;
    this.value = value;
  }

  /**
   * parse key
   * 
   * @param key
   *          - key is attribute or sub attribute.
   * 
   * @author: NNDuy
   */
  private void parseKey(String key) {
    if (key.contains(Constants.Common.DOT)) {
      this.key = Arrays.stream(key.split(Constants.Common.DOT_ENCODE)).collect(Collectors.toList());
    } else {
      this.key = new ArrayList<>();
      this.key.add(key);
    }
  }

  /**
   * @return the key
   */
  public List<String> getKey() {
    return key;
  }

  /**
   * @return the operation
   */
  public Operation getOperation() {
    return operation;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return value;
  }

}
