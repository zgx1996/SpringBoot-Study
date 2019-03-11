package com.example.hellospringboot.springbootsecurity.hellosecurity.config;

import com.example.hellospringboot.springbootsecurity.hellosecurity.provider.MyAuthenticationProvider;
import com.example.hellospringboot.springbootsecurity.hellosecurity.userdetailservice.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 16:25
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserDetailService userDetailService;

    @Bean
    public MyAuthenticationProvider authenticationProvider(){
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        return myAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                /*自定义需要登录的时候跳转的url*/
                .loginPage("/authentication/require")
                /*配置处理登录请求的url*/
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/mylogin.html").permitAll()
                .anyRequest().authenticated()
                .and()
                /*禁用csrf*/
                .csrf().disable()
                .authenticationProvider(authenticationProvider());
    }
}
