package com.anibaba.user.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_USER_ROLE")
public class UserRole {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String roleId;
}
