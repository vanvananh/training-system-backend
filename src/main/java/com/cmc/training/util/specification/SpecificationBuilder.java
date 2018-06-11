/**
 * training-system-backend - com.cmc.training.util.specification
 */
package com.cmc.training.util.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 * @author: NNDuy
 * @Date: Mar 3, 2018
 */
public class SpecificationBuilder<T> {

  private final List<CriteriaCustom> criterias;

  public SpecificationBuilder() {
    criterias = new ArrayList<CriteriaCustom>();
  }

  /**
   * Constructure
   */
  public SpecificationBuilder(List<CriteriaCustom> criterias) {
    this.criterias = criterias;
  }

  /**
   * Constructure for delete list id
   */
  public SpecificationBuilder(CriteriaCustom criteria) {
    this.criterias = new ArrayList<>();
    this.criterias.add(criteria);
  }

  public SpecificationBuilder<T> addCriteriaCustom(CriteriaCustom criteria) {
    this.criterias.add(criteria);
    return this;
  }

  public SpecificationBuilder<T> addCriteriaCustoms(List<CriteriaCustom> criterias) {
    this.criterias.addAll(criterias);
    return this;
  }

  /**
   * compile list CriteriaCustom to Specification<T>
   * 
   * @return Specification<T>
   * @author: NNDuy
   */
  public Specification<T> build() {
    // check list null
    if (criterias.size() == 0) {
      return null;
    }

    // convert List CriteriaCustom to List Specification
    List<Specification<T>> specs = new ArrayList<Specification<T>>();
    for (CriteriaCustom param : criterias) {
      specs.add(new SpecificationCustom<T>(param));
    }

    // combined List Specification to filter
    Specification<T> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }
}
