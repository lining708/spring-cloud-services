package com.anibaba.user.service;

import com.anibaba.core.exception.CustomErrorCode;
import com.anibaba.user.model.Resource;
import com.anibaba.user.model.Role;
import com.anibaba.user.model.User;
import com.anibaba.user.repository.IUserRepository;
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
public class UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private IUserRepository userRepository;

    public Page<User> search(User.Criteria criteria) {
        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!BaseUtils.isEmpty(criteria.getId()))
                predicates.add(cb.equal(root.get("id"),criteria.getId()));
            if (!BaseUtils.isEmpty(criteria.getUsername()))
                predicates.add(cb.equal(root.get("username"),criteria.getUsername()));
            if (!BaseUtils.isEmpty(criteria.getRealName()))
                predicates.add(cb.like(root.get("realName"),"%" + criteria.getRealName() + "%"));
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
        Pageable pageable = PageRequest.of(criteria.getPage(),criteria.getSize());
        return userRepository.findAll(spec, pageable);
    }

    public User create(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userRepository.save(user);

    }

    public User update(String id, User user) {
        User preUpdate = userRepository.findById(id).orElseThrow(CustomErrorCode.NOT_FOUND_ENTITY_EXCEPTION::runtimeException);
        preUpdate.setEmail(user.getEmail());
        preUpdate.setPhoneNumber(user.getPhoneNumber());
        preUpdate.setRealName(user.getRealName());
        preUpdate.setUpdateTime(new Date());
        return userRepository.save(preUpdate);
    }

    public void delete(String id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(CustomErrorCode.NOT_FOUND_ENTITY_EXCEPTION::runtimeException));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Role> getRoles(User user) {
        return roleService.getRolesByUserId(user.getId());
    }

    public List<Resource> getResources(User user) {
        List<Role> roles = user.getRoles();

        if (!BaseUtils.isEmpty(roles)) {
            List<Resource> resultList = new ArrayList<>();
            for (Role role : roles) {
                List<Resource> resources = role.getResources();
                if (!BaseUtils.isEmpty(resources)) {
                    resultList.addAll(resources);
                }
            }
            return resultList;
        }
        return null;
    }
}
