package com.lawu.chick.jobs;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@MapperScan({"com.lawu.chick.repository.mapper"})
@SpringBootApplication
@Configuration
@ImportResource(locations={"classpath:spring.xml"})
@ComponentScan(basePackages={"com.lawu.chick"})
public class JobsApplication {

    private static Logger logger = LoggerFactory.getLogger(JobsApplication.class);

    public static void main(String[] args) {
        logger.info("jobs is starting");
        SpringApplication.run(JobsApplication.class, args);
        logger.info("jobs is started");
    }
}
