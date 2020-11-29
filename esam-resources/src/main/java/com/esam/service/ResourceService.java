package com.esam.service;

import com.esam.model.Resource;
import com.esam.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author trutao
 * @create 2020-11-26 16:53
 */

@Slf4j
@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public Resource getResource(Long ResourceId) {
        return resourceRepository.findById(ResourceId).get();
    }

    public void saveResource(Resource resource) {
        log.info(resource.toString());
        resourceRepository.save(resource);
    }

    @Transactional
    public void updateResource(Resource resource) {
        if (resource.getId() == null || resource.getId() < 0 || !resourceRepository.existsById(resource.getId())) {
            log.info("resoruce id does not exist!");
            return;
        }
        resourceRepository.save(resource);
    }

    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }
}
