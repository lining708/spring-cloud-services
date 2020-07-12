package com.anibaba.dict.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //资源ID
    public static final String RESOURCE_ID = "res_1";

    @Autowired
    private TokenStore tokenStore;

    //配置令牌服务
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)   //验证令牌的服务
//                .tokenServices(tokenService())   //验证令牌的服务
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .access("#oauth2.hasScope('SCOPE_ORDER')")
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

/*
    //TODO 此方法为远程Token校验
    //令牌解析服务
    @Bean
    public ResourceServerTokenServices tokenService() {
        // 请求远程服务器进行Token校验, 必须指定校验Token的url, client_id, client_secret
        RemoteTokenServices service = new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://127.0.0.1:19090/uaa/oauth/check_token");
        service.setClientId("client_1");
        service.setClientSecret("secret");
        return service;
    }
*/

}
