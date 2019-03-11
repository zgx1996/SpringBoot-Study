package com.example.hellospringboot.springbootsecurity.hellosecurity.authentication;

import com.example.hellospringboot.springbootsecurity.hellosecurity.support.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 16:38
 */
@RestController
public class AuthenticationController {

    RequestCache requestCache = new HttpSessionRequestCache();

    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(value = "authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData authenticationDirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            /*判断是否以 html 或者 htm 结尾*/
            if(redirectUrl.endsWith("html") || redirectUrl.endsWith("htm")){
                redirectStrategy.sendRedirect(request,response,"/mylogin.html");
            }
        }
        return new ResponseData(false,"请求的url需要登录");
    }


    @RequestMapping(value = "/authentication/form")
    public ResponseData authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ResponseData(true,"haha");
    }

}
