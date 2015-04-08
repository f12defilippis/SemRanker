package com.flol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@Configuration
@EnableJpaRepositories(basePackages = {"com.flol.semrankercommon.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class SemRankerWeb {
    public static void main(String[] args) {
        SpringApplication.run(SemRankerWeb.class, args);
    }	
}
