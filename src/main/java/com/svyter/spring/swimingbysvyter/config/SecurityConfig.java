package com.svyter.spring.swimingbysvyter.config;

import com.svyter.spring.swimingbysvyter.serviceImpl.MyCustomersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final MyCustomersDetailServiceImpl detailService;
    @Autowired
    public SecurityConfig(MyCustomersDetailServiceImpl detailService) {
        this.detailService = detailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Настройка пользователей для аутентификации
        auth.userDetailsService(detailService).passwordEncoder(NoOpPasswordEncoder.getInstance());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Конфигурация доступа
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
            /*    .formLogin()
                .defaultSuccessUrl("/swagger-ui.html", true)
                .permitAll()
                .and()*/
                .logout()
                .permitAll()
                .and()
                .httpBasic();
    }
}
