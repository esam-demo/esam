package com.esam.client;

import com.esam.model.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author trutao
 * @create 2020-11-28 22:27
 */

@Slf4j
@Component
public class ResourceRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    public Resource getResource(String resourecId) {
        log.info("call resource service by RestTemplate with Ribbon");
        String serviceUri = "http://resourceservice/v1/resource/{resourceId}";

        log.info("service path: {}", serviceUri);

        ResponseEntity<Resource> restExchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                Resource.class,
                resourecId);
        return restExchange.getBody();
    }
}
