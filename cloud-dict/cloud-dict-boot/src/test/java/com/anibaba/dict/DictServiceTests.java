package com.anibaba.dict;

import com.anibaba.dict.mapper.DictTypeMapper;
import com.anibaba.dict.model.DictType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DictServiceTests {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Test
    public void search(){
        DictType.Criteria criteria = new DictType.Criteria();
//        criteria.setId("1");
//        criteria.setCode();
        criteria.setName("字典类型3");
        List<DictType> search = dictTypeMapper.search(criteria);
        System.out.println(search);
    }

    @Test
    public void insert(){
        DictType dictType = new DictType("4","字典类型4","字典编码4",new Date(),new Date());
        int i = dictTypeMapper.create(dictType);
        System.out.println(i);
    }



}
