package com.lawu.chick.cache.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户服务启动类
 * @author Leach
 * @date 2017/3/10
 */
@ComponentScan(value = {"com.lawu.chick.cache.service"})
@SpringBootApplication
public class CacheServiceApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(CacheServiceApplicationTest.class);

    public static void main(String[] args) {
        logger.info("cache-srv is starting");
        SpringApplication.run(CacheServiceApplicationTest.class, args);
        logger.info("cache-srv is started");
    }
}
