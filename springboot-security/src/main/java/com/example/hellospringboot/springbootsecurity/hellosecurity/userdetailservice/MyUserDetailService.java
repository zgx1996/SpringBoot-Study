package com.example.hellospringboot.springbootsecurity.hellosecurity.userdetailservice;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 18:05
 */
@Component
public class MyUserDetailService implements UserDetailsService {


    @Override
    public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /*从数据库中根据 userName 查询用户名和密码以及一些其他信息*/
        MyUserDetails userDetails = new MyUserDetails(userName, "zgx");
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        userDetails.setGrantedAuthorityList(grantedAuthorities);
        return userDetails;
    }
}
