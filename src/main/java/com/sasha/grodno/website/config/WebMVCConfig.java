package com.sasha.grodno.website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin-panel").setViewName("admin");
        registry.addViewController("/about").setViewName("about");
       // registry.addViewController("/forgot_password").setViewName("forgot_password");


    }
}
