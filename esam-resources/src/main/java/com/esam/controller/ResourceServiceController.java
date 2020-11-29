package com.esam.controller;

import com.esam.model.Resource;
import com.esam.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author trutao
 * @create 2020-11-26 16:41
 */

@RestController
@RequestMapping(value = "/v1/resource")
public class ResourceServiceController {

    @Autowired
    ResourceService resourceService;


    @GetMapping(value = "/{resourceId}")
    public Resource getResource(@PathVariable("resourceId") Long resourceId) {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resourceService.getResource(resourceId);
    }

    @PutMapping
    public void updateResource(@RequestBody Resource resource) {
        resourceService.updateResource(resource);
    }

    @PostMapping
    public void saveResource(@RequestBody Resource resource) {
        resourceService.saveResource(resource);
    }

    @RequestMapping(value = "/{resourceId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable("resourceId") Long resourceId) {
        resourceService.deleteResource(resourceId);
    }
}
