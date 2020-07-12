package com.anibaba.dict.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class DictType {

    private String id;

    private String name;

    private String code;

    private Date createTime;

    private Date updateTime;

    public DictType() {
    }

    public DictType(String id, String name, String code, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Data
    public static class Criteria {
        private String id;
        private String name;
        private String code;

        public Criteria() {
        }

        public Criteria(String id, String name, String code) {
            this.id = id;
            this.name = name;
            this.code = code;
        }
    }
}
