package com.esam.services;

import com.esam.client.ResourceDiscoveryClient;
import com.esam.client.ResourceFeignClient;
import com.esam.client.ResourceRestTemplateClient;
import com.esam.config.PropertiesConfig;
import com.esam.constant.HttpConst;
import com.esam.dto.HttpResponse;
import com.esam.model.Request;
import com.esam.model.Resource;
import com.esam.repository.RequestRepository;
import com.esam.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author trutao
 * @create 2020-11-25 11:11
 */

@Slf4j
@Service
public class RequestService {

    @Autowired
    ResourceDiscoveryClient resourceDiscoveryClient;

    @Autowired
    ResourceRestTemplateClient resourceRestTemplateClient;

    @Autowired
    ResourceFeignClient resourceFeignClient;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    PropertiesConfig propertiesConfig;

    public Resource retrieveResourceInfo(String resourceId, String clientType) {
        Resource resource = null;
        switch (clientType) {
            case "feign":
                log.info("I am using the feign client");
                resource = resourceFeignClient.getResource(resourceId);
                break;
            case "rest":
                log.info("I am using the rest client");
                resource = resourceRestTemplateClient.getResource(resourceId);
                break;
            case "discovery":
                log.info("I am using the discovery client");
                resource = resourceDiscoveryClient.getResource(resourceId);
                break;
            default:
                log.info("I am using default client method");
        }

        return resource;
    }

    /**
     * The @HystrixCommand used for wrapping other micro services invocation
     *
     * @param resourceId
     * @return
     */
    @HystrixCommand
    public Resource getResource(String resourceId) {
        Resource resource = resourceRestTemplateClient.getResource(resourceId);
        return resource;
    }

    public HttpResponse<Request> getRequest(Long id, String resourceId, String clientType) {
        Resource resource = retrieveResourceInfo(resourceId, clientType);
        Request request = requestRepository.findByIdAndResourceId(id, resourceId);
        request.withResourceName("get resource from remote: " + resource.getName())
                .withResourceType("get resource from remote: " + resource.getType())
                .withComment(propertiesConfig.getExampleProperty());
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request retrieved", request);
    }

    public HttpResponse<Request> getRequest(Long id, String resourceId) {
        Request request = requestRepository.findByIdAndResourceId(id, resourceId);
        Resource resource = getResource(resourceId);

        request.withComment("Hystrix protection: " + propertiesConfig.getExampleProperty())
                .withResourceName("Hystrix protection: " + resource.getName())
                .withResourceType("Hystrix protection: " + resource.getType());

        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request retrieved", request);
    }

    /**
     * The @HystrixCommand used for wrapping DB invocation
     *
     * @param resourceId
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "buildFallbackRequestList", // fallback model
            threadPoolKey = "requestByResThreadPool", // build a new thread pool (Bulkhead model)
            threadPoolProperties = { // customizing the thread pool properties of the new built thread pool
                    @HystrixProperty(name = "coreSize", value = "30"), // size of thread pool
                    @HystrixProperty(name = "maxQueueSize", value = "10") // max size of queue for blocking requests
            },
            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"), // timeout of circuit breaker
//                    @HystrixProperty(name="execution.isolation.strategy",value = "SEMAPHORE"), //default THREAD.

                    // customizing the circuit Breaker properties
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //default 20, Minimum number of calls
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),// default 50, Maximum error threshold
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),// default 5, time window of retry via using a call after circuit break
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),// default 10, time window to monitor and check service call
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")// controls the times of statistics which are collected in the defined scroll window.
            }

    )
    public HttpResponse<List<Request>> getRequestsByResourceId(String resourceId) {
        // in Hystrix, the correlation id can't be gotten. how to get it? --> using HystrixConcurrencyStrategy.
        log.debug("RequestService.getRequestsByResourceId  Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        randomlyRunLong();
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request retrieved ok", requestRepository.findByResourceId(resourceId));
    }

    private HttpResponse<List<Request>> buildFallbackRequestList(String resourceId) {
        List<Request> fallbackList = new ArrayList<>();
        Request request = new Request().withId(0L)
                .withRequestedFor("none")
                .withResourceId(resourceId)
                .withResourceName("none")
                .withComment("Sorry no request information currently available!");
        fallbackList.add(request);
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request retrieved ok", fallbackList);
    }


    public HttpResponse saveRequest(Request request) {
        requestRepository.save(request);
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.CREATED, HttpConst.SUCCESS_MESSAGE, request.getId());
    }

    @Transactional
    public HttpResponse updateRequest(final Request request) {
        if (request.getId() == null || request.getId() < 0 || !requestRepository.existsById(request.getId())) {
            log.info("request id does not exist!");
            return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request id does not exist!");
        }
        requestRepository.save(request);
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request update ok");
    }

    @Transactional
    public HttpResponse updateRequest(Long id, String status) {
        requestRepository.updateStatusById(id, status);
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, "request stutas update ok");
    }

    public HttpResponse deleteRequest(Long id) {
        String message = null;
        if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
            message = HttpConst.Delete_OK;
        } else {
            log.info("request[{}] is not present.", id);
            message = "request object is not present.";
        }
        return new HttpResponse(HttpConst.SUCCESS, HttpStatus.OK, message);
    }

    private boolean existRequest(String requester, String resourceId) {
        return requestRepository.findByRequestedForAndResourceId(requester, resourceId).isPresent();
    }

    /**
     * use for hystrix testing
     */
    private void randomlyRunLong() {
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum == 3) {
            log.info("random to sleep ~ ");
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
