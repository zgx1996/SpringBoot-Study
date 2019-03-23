package com.example.hellospringboot.springbootoauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/16 11:25
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /*必须加上，覆盖掉默认的AuthenticationManager ，否则会出现 Unsupported grant type: password*/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client_2").resourceIds("order")
                .authorizedGrantTypes("password","refresh_token")
                .scopes("select").authorities("client").secret(finalSecret).and()
                .withClient("client_1").resourceIds("order")
                .authorizedGrantTypes("client_credentials")
                .scopes("select").authorities("client").secret(finalSecret);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
