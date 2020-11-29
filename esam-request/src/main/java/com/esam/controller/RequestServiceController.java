package com.esam.controller;

import com.esam.dto.HttpResponse;
import com.esam.model.Request;
import com.esam.services.RequestService;
import com.esam.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author trutao
 * @create 2020-11-21 22:49
 */

@Slf4j
@RestController
@RequestMapping(value = "/v1/request")
public class RequestServiceController {

    @Autowired
    RequestService requestService;


    @GetMapping(value = "/{resourceId}/{requestId}")
    public HttpResponse getRequest(@PathVariable("resourceId") String resourceId,
                                   @PathVariable("requestId") Long id) {
        return requestService.getRequest(id, resourceId);
    }

    /**
     * Use Spring Discovery to call
     * @param resourceId
     * @param id
     * @param clientType
     * @return
     */
    @GetMapping(value = "/{resourceId}/{requestId}/{clientType}")
    public HttpResponse getRequestWithClient(@PathVariable("resourceId") String resourceId,
                                             @PathVariable("requestId") Long id,
                                             @PathVariable("clientType") String clientType) {
        return requestService.getRequest(id, resourceId, clientType);
    }

    @GetMapping(value = "/{resourceId}")
    public HttpResponse<List<Request>> getRequests(@PathVariable("resourceId") String resourceId) {
        log.debug("RequestServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return requestService.getRequestsByResourceId(resourceId);
    }

    @PutMapping(value = "/{id}/{status}")
    public HttpResponse updateRequest(@PathVariable Long id, @PathVariable String status) {
        return requestService.updateRequest(id, status);
    }

    @PutMapping
    public HttpResponse updateRequest(@RequestBody Request request) {
        return requestService.updateRequest(request);
    }

    @PostMapping
    public HttpResponse saveRequest(@RequestBody Request request) {
        log.info(String.format("This is the post -- save a new request"));
        return requestService.saveRequest(request);
    }

    @DeleteMapping(value = "{requestId}")
    public HttpResponse deleteRequest(@PathVariable("requestId") Long requestId) {
        log.info("This is the Request Delete Method");
        return requestService.deleteRequest(requestId);
    }
}
