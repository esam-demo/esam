package com.esam.controller;

import com.esam.services.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author trutao
 * @create 2020-11-28 22:00
 */
@RestController
@RequestMapping(value = "v1/tools")
public class ToolsController {
    
    @Autowired
    private DiscoveryService discoverSerivce;

    @GetMapping("/eureka/services")
    public List<String> getEurekaServices() {
        return discoverSerivce.getEurekaServices();
    }
}
