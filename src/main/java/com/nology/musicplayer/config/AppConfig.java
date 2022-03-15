package com.nology.musicplayer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.nology")
@PropertySource("classpath:app.properties")
public class AppConfig {

}
