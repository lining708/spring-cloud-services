package com.anibaba.user.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TB_RESOURCE")
public class Resource {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private String id;

    private String name;

    private String code;

    public static String[] getResourceCodes(List<Resource> resources) {
        String[] codes = new String[resources.size()];
        for (int i = 0; i < resources.size(); i++) {
            codes[i] = resources.get(i).getCode();
        }
        return codes;
    }
}
