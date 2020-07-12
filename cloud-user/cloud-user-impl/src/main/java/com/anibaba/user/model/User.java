package com.anibaba.user.model;

import com.anibaba.core.model.AbstractCriteriaSimpleModel;
import com.anibaba.core.model.AbstractPersistentModel;
import com.anibaba.user.service.UserService;
import com.anibaba.user.utils.SpringUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TB_USER")
public class User extends AbstractPersistentModel {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String realName;

    private String phoneNumber;

    private String email;

    public List<Role> getRoles() {
        return getUserService().getRoles(this);
    }

    public List<Resource> getResources() {
        return getUserService().getResources(this);
    }

    private UserService getUserService() {
        return SpringUtil.getBean(UserService.class);
    }

    @Data
    public static class Criteria extends AbstractCriteriaSimpleModel {

        private String username;

        private String realName;

    }
}
