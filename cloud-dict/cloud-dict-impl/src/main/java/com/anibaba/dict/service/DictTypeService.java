package com.anibaba.dict.service;

import com.anibaba.dict.mapper.DictTypeMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;


}
