package com.anibaba.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.anibaba.gateway.common.EncryptUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        //是否过滤
        return true;
    }

    @Override
    public String filterType() {
        //拦截机制 请求之前拦截
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级 最优先
        return 0;
    }

    @Override
    public Object run() throws ZuulException {
        //从上下文中 拿到用户对象
        RequestContext context = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication))
            return null;
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        //获取当前身份信息
        String principal = userAuthentication.getName();
        //获取当前用户权限信息
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(c -> authorities.add(((GrantedAuthority) c).getAuthority()));

        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String, Object> jsonToken = new HashMap<>(requestParameters);
        if (userAuthentication != null) {
            jsonToken.put("principal", principal);
            jsonToken.put("authorities", authorities);
        }
        //把身份信息和权限信息放在json中, 加入http 的 header中, 斌妆发给微服务
//        DigestUtils.
        context.addZuulRequestHeader("json-token", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));


        return null;
    }
}
