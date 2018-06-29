package com.lawu.chick.operator.api;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * 小鸡api启动类
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan({"com.lawu.chick.repository.mapper", "com.lawu.chick.operator.repository.mapper"})
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.lawu.chick"})
@ImportResource(locations={"classpath:spring.xml"})
public class OperatorApiApplication {

    private static Logger logger = LoggerFactory.getLogger(OperatorApiApplication.class);

    public static void main(String[] args) {
        logger.info("operator-api is starting");
        SpringApplication.run(OperatorApiApplication.class, args);
        logger.info("operator-api is started");
    }
}

