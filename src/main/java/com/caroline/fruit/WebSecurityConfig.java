package com.caroline.fruit;

import com.caroline.fruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;

  @Autowired
  public WebSecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/").authenticated()
        .antMatchers("/register","/express","/express/detail","/express/list","/css/*","/js/*","favicon.ico").permitAll()
        //.antMatchers("/user").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .usernameParameter("username")
        .loginPage("/login")
        .permitAll()
        .and().csrf().disable()//spring boot 集成 spring securty登陆时报 403 https://blog.csdn.net/qq_35170213/article/details/86014394
        .rememberMe();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
  }
}