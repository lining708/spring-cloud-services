package com.anibaba.user.repository;

import com.anibaba.user.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, String> {

    @Query("select roleId from UserRole where userId = :userId")
    public List<String> findRoleIdsByUserId(@Param("userId") String userId);
}
