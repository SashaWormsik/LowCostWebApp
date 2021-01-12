package com.sasha.grodno.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/main", "/register/**", "/main/result").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/main")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/main")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/assets/**");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder noop() {
        return NoOpPasswordEncoder.getInstance();
    }
}