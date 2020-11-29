package com.esam.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trutao
 * @create 2020-11-28 22:01
 */

@Slf4j
@Service
public class DiscoveryService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    public List<String> getEurekaServices() {
        List<String> services = new ArrayList<>();

        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(serviceInstance -> {
                services.add(String.format(("%s:%s"), serviceName, serviceInstance.getUri()));
            });
        });

        return services;
    }
}
