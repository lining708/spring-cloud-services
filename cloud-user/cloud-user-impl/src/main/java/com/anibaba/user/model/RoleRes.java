package com.anibaba.user.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_ROLE_RES")
public class RoleRes {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private String id;

    @Column(nullable = false)
    private String roleId;

    @Column(nullable = false)
    private String resId;
}
