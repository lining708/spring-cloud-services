package com.anibaba.user.repository;


import com.anibaba.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    public List<Role> findByIdIn(String[] ids);
}
