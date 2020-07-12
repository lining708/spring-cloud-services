package com.anibaba.user.service;

import com.anibaba.user.model.Resource;
import com.anibaba.user.repository.IResourceRepository;
import com.anibaba.user.repository.IRoleResRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private IResourceRepository resourceRepository;

    @Autowired
    private IRoleResRepository roleResRepository;

    public List<Resource> getResourcesByRoleId(String roleId) {
        List<String> resIds = roleResRepository.findResIdsByRoleId(roleId);
        return resourceRepository.findByIdIn(resIds.toArray(new String[0]));
    }
}
