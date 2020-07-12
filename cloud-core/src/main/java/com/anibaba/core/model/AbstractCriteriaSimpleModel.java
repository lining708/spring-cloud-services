package com.anibaba.core.model;

import com.anibaba.core.util.BaseUtils;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@MappedSuperclass
public abstract class AbstractCriteriaSimpleModel {

    private String id;

    private Integer page = 0;

    private Integer size = 10;

}
