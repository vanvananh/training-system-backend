/**
 * training-system-backend - com.cmc.training.util.specification
 */
package com.cmc.training.util.specification;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author: NNDuy
 * @Date: Mar 4, 2018
 */
public class MySQLDialectCustom extends MySQL5Dialect {
  public MySQLDialectCustom() {
    super();
    registerFunction("match", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
        "match(?1) against  (?2 in NATURAL LANGUAGE mode)"));
  }
}
