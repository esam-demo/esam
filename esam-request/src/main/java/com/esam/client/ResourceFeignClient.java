package com.esam.client;

import com.esam.model.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author trutao
 * @create 2020-11-28 22:27
 */

@FeignClient("resourceservice")
public interface ResourceFeignClient {

//    @RequestMapping(value = "/v1/resource/{resourceId}", method = RequestMethod.GET, consumes = "application/json")
    @RequestMapping(method = RequestMethod.GET, value = "/v1/resource/{resourceId}")
    Resource getResource(@PathVariable("resourceId") String resourceId);


}
