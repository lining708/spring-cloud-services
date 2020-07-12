package com.anibaba.user.service;


import com.anibaba.core.exception.CustomErrorCode;
import com.anibaba.user.model.Resource;
import com.anibaba.user.model.Role;
import com.anibaba.user.repository.IRoleRepository;
import com.anibaba.user.repository.IUserRoleRepository;
import com.anibaba.user.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private ResourceService resourceService;

    public Page<Role> search (Role.Criteria criteria) {
        Specification<Role> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!BaseUtils.isEmpty(criteria.getId()))
                predicates.add(cb.equal(root.get("id"),criteria.getId()));
            if (!BaseUtils.isEmpty(criteria.getName()))
                predicates.add(cb.equal(root.get("name"),criteria.getName()));
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
        Pageable pageable = PageRequest.of(criteria.getPage(),criteria.getSize());
        return roleRepository.findAll(spec,pageable);
    }


    public Role create(Role role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        return roleRepository.save(role);
    }

    public Role update(String id, Role role) {
        Role preUpdate = roleRepository.findById(id).orElseThrow(CustomErrorCode.NOT_FOUND_ENTITY_EXCEPTION::runtimeException);
        preUpdate.setName(role.getName());
        preUpdate.setCode(role.getCode());
        preUpdate.setUpdateTime(new Date());
        return roleRepository.save(preUpdate);
    }

    public void delete (String id) {
        roleRepository.delete(roleRepository.findById(id).orElseThrow(CustomErrorCode.NOT_FOUND_ENTITY_EXCEPTION::runtimeException));
    }

    public List<Role> getRolesByUserId(String userId) {
        List<String> roleIds = userRoleRepository.findRoleIdsByUserId(userId);
        List<Role> roles = roleRepository.findByIdIn(roleIds.toArray(new String[0]));
        return roles;

    }

    public List<Resource> getResourcesByRole(Role role) {
        return resourceService.getResourcesByRoleId(role.getId());
    }

    ;


}
