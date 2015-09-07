package com.luke.cms.config.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.luke.cms.config.web")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(md5Encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // login configuration
        http.csrf().and().formLogin().loginPage("/login.jsp").and().formLogin().loginProcessingUrl("/login").and()
                .formLogin().defaultSuccessUrl("/home").and().formLogin().failureUrl("/?error=1");

        // logout configuration
        http.logout().logoutUrl("/logout").and().logout().logoutSuccessUrl("/").and().logout()
                .deleteCookies("JSESSIONID").and().logout().addLogoutHandler(new CmsLogoutHandler());
        
        // remember me
        http.rememberMe().tokenValiditySeconds(1209600).and().rememberMe().rememberMeParameter("remember-me");
    }

    class CmsLogoutHandler implements LogoutHandler {
        @Override
        public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
            System.out.println("my logout handler");
        }
    }

    class CmsLoginSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            System.out.println("login success");
        }
    }
}