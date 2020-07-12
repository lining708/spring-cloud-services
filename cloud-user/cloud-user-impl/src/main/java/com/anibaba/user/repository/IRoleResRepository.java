package com.anibaba.user.repository;

import com.anibaba.user.model.RoleRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleResRepository extends JpaRepository<RoleRes, String> {

    @Query("select resId from RoleRes where roleId = :roleId")
    public List<String> findResIdsByRoleId(@Param("roleId") String roleId);
}
