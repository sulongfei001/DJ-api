package com.sevenXnetworks.treasure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-29 上午11:26
 * @Version 1.0
 */
@Configuration
public class TimerConfig {
    @Bean
    public Timer timer() {
        return new Timer();
    }
}
