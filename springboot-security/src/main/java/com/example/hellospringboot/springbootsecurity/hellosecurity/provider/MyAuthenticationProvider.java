package com.example.hellospringboot.springbootsecurity.hellosecurity.provider;

import com.example.hellospringboot.springbootsecurity.hellosecurity.userdetailservice.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 18:49
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String)authentication.getPrincipal();

        UserDetails userDetails = userDetailService.loadUserByUsername(principal);

        String password = (String)authentication.getCredentials();
        if(password != null && password.equals(userDetails.getPassword())){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            return token;
        }else{
            throw new BadCredentialsException("密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
