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
 * @create 2020-11-28 20:41
 */

@Slf4j
@Component
public class ResourceDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Resource getResource(String resourecId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("resourceservice");
        if (instances.size() == 0) {
            return null;
        }
        String serviceUri = String.format("%s/v1/resource/%s",
                instances.get(0).getUri().toString(),
                resourecId);
        log.info("service path: {}", serviceUri);

        ResponseEntity<Resource> restExchange = restTemplate.exchange(serviceUri,
                HttpMethod.GET,
                null,
                Resource.class,
                resourecId);
        return restExchange.getBody();
    }

}
