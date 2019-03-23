package com.example.springboot.authorizationcodeserver.qqserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/18 20:50
 */
@Configuration
public class ClientRegister {

    @Configuration
    @EnableAuthorizationServer
    protected class authorizationServerRegister extends AuthorizationServerConfigurerAdapter{

        @Autowired
        @Qualifier(value = "authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private PasswordEncoder bCryptPasswordEncoder;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer){
            endpointsConfigurer.authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory().withClient("mongotv").secret(bCryptPasswordEncoder.encode("mongotv"))
                    .scopes("fans_list","base_info").authorizedGrantTypes("authorization_code","password","refresh_token")
                    .authorities("client").resourceIds("qq")
                    .autoApprove("fans_list")
                    .redirectUris("http://localhost:8081/mongotv/qq/redirect");
        }
    }


    @Configuration
    @EnableResourceServer()
    protected class ResourceServerRegister extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId("qq").stateless(true);
            // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
//            resources.resourceId(QQ_RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers()
                    // 保险起见，防止被主过滤器链路拦截
                    .antMatchers("/qq/**").and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .authorizeRequests().antMatchers("/qq/base_info").access("#oauth2.hasScope('base_info')")
                    .antMatchers("/qq/fans_list").access("#oauth2.hasScope('fans_list')");
        }
    }



}
