package com.sevenXnetworks.treasure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //todo
        http.requestMatchers()
                .antMatchers("/switch_mode")
                .antMatchers("/splash_screen")
                .and()
                .authorizeRequests()
                .antMatchers("/switch_mode").hasAnyRole("MANAGER")
                .antMatchers("/splash_screen").hasAnyRole("MANAGER")
        ;

    }

}
