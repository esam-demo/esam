package com.esam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
@RefreshScope
// used for refresh configuration info from github, but for multi-instances, need refresh for everyone. so it will be replaced by spring cloud bus
//@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class EsamRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsamRequestApplication.class, args);
    }

    @Bean
    @LoadBalanced // support Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
