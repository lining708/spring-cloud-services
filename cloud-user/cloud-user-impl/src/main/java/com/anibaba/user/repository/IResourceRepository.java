package com.anibaba.user.repository;

import com.anibaba.user.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResourceRepository extends JpaRepository<Resource, String> {

    public List<Resource> findByIdIn(String[] ids);
}
