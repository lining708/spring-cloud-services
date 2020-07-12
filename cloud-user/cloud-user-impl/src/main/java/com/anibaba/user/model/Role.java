package com.anibaba.user.model;

import com.anibaba.core.model.AbstractCriteriaCodeModel;
import com.anibaba.core.model.AbstractPersistentModel;
import com.anibaba.user.service.RoleService;
import com.anibaba.user.utils.SpringUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TB_ROLE")
public class Role extends AbstractPersistentModel {

    private String name;

    private String code;

    public List<Resource> getResources() {
        return getRoleService().getResourcesByRole(this);
    }

    private RoleService getRoleService() {
        return SpringUtil.getBean(RoleService.class);
    }

    @Data
    public static class Criteria extends AbstractCriteriaCodeModel {

    }
}
