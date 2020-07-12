package com.anibaba.core.model;

import com.anibaba.core.util.BaseUtils;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Data
@MappedSuperclass
public abstract class AbstractCriteriaCodeModel extends AbstractCriteriaSimpleModel{

    private String name;

    private String code;

}
