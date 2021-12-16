package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class Login implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_NURSE")) {
            httpServletResponse.sendRedirect("/nurse");
        }
        if (roles.contains("ROLE_DOCTOR")) {
            httpServletResponse.sendRedirect("/doctor");
        }
        if (roles.contains("ROLE_PATIENT")) {
            httpServletResponse.sendRedirect("/patient");
        }

    }
}