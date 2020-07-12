package com.anibaba.user.repository;

import com.anibaba.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    public User findByUsername(String username);
}
