package com.everseeker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by everseeker on 16/8/7.
 */
@Configuration
@ComponentScan(basePackages = {"com.everseeker"})
@ImportResource({"classpath:spring-mybatis.xml"})
public class RootConfig {

}
