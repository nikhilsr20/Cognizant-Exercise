package com.cognizant.employeemanagementsystem.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource primaryDataSource() {
        return new HikariDataSource();
    }


    @Bean
    @ConfigurationProperties("app.secondary.datasource")
    public DataSource secondaryDataSource() {
        return new HikariDataSource();
    }
}