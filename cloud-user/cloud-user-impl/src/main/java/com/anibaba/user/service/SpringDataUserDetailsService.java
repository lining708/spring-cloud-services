package com.anibaba.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;

import com.anibaba.user.model.Resource;
import com.anibaba.user.model.Role;
import com.anibaba.user.model.User;
import com.anibaba.user.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null)
            return null;

        List<Role> roles = user.getRoles();
        //用户权限信息
        String[] resourceCodes;
        List<Resource> resources = user.getResources();
        if (!BaseUtils.isEmpty(resources)) {
            resourceCodes = Resource.getResourceCodes(user.getResources());
        } else {
            resourceCodes = new String[0];
        }
        // 将user对象转换成json, 并排除指定成员属性
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes("resources", "roles", "password");
        String userJson = JSON.toJSONString(user, excludeFilter, SerializerFeature.PrettyFormat)
//                .replaceAll("[\r\n\t]","")
                ;

        return org.springframework.security.core.userdetails.User
                .withUsername(userJson)
//                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(resourceCodes)
                .build();
    }
}
