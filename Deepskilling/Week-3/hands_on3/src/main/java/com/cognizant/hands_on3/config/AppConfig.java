package com.example.hands_on3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:employee.xml")
public class AppConfig {
}
