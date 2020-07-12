package com.anibaba.dict.mapper;

import com.anibaba.dict.model.DictType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictTypeMapper {

    public int create(DictType dictType);

    public List<DictType> search(DictType.Criteria criteria);
}
