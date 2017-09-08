package com.cachexic.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tangmin
 * @version V1.0
 * @Title: OrderProviderApplication.java
 * @Package com.cachexic.cloud
 * @Description: rocketmq消息队列服务提供方，只对外提供服务，所以，不需要@EnableFeignClients和@EnableCircuitBreaker
 * @date 2017-09-06 22:20:18
 */
@SpringBootApplication
@EnableEurekaClient
public class RockemqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RockemqApplication.class, args);
    }
}
