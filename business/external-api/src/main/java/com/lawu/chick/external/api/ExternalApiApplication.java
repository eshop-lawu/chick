package com.lawu.chick.external.api;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 回调api启动类
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@MapperScan({"com.lawu.chick.repository.mapper"})
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.lawu.chick"})
public class ExternalApiApplication {

    private static Logger logger = LoggerFactory.getLogger(ExternalApiApplication.class);

    public static void main(String[] args) {
        logger.info("external-api is starting");
        SpringApplication.run(ExternalApiApplication.class, args);
        logger.info("external-api is started");
    }
}

