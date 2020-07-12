package com.anibaba.user.config;

import com.anibaba.user.service.SpringDataUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
//声明认证服务
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SpringDataUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    //TODO 以数据库的形式存储客户端信息
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    // 客户端配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
        /*clients.inMemory()  //认证信息存储方式
                .withClient("client_1")   //那些客户端可以使用
                .secret(new BCryptPasswordEncoder().encode("secret"))   //密码加密方式
                .resourceIds("res_1")  //允许访问的资源
                //该客户端允许的授权类型
                .authorizedGrantTypes(
                        "authorization_code",   //授权码模式
                        "password", //密码模式
                        "client_credentials",
                        "implicit",
                        "refresh_token"
                )
                .scopes("all")   //允许的授权范围
                .autoApprove(false) //false: 认证时跳转到授权页面
                .redirectUris("http://www.baidu.com")
        ;*/
    }

    //令牌服务配置
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);  //客户端信息服务
        service.setSupportRefreshToken(true);    //是否刷新令牌
        service.setTokenStore(tokenStore);  //令牌存储策略
        service.setAccessTokenValiditySeconds(7200); //令牌默认有效期
        service.setRefreshTokenValiditySeconds(259200); //刷新令牌默认有效期

        //TODO 令牌增强, 配置JWT令牌转换器
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);


        return service;
    }

    //令牌访问端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)  //密码认证模式需要
                .authorizationCodeServices(authorizationCodeServices)   //授权码模式需要

//                .userDetailsService(userDetailsService)

                .tokenServices(tokenService())  //令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许POST提交
    }

    //令牌访问端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //意为开放那些端口不需要做认证
        security.tokenKeyAccess("permitAll()")  //oauth/token_key公开
                .checkTokenAccess("permitAll()")    //oauth/check_token公开
                .allowFormAuthenticationForClients();   //允许表单提交
    }
}
