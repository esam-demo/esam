package com.esam.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author trutao
 * @create 2020-11-27 22:38
 */

@Slf4j
@Configuration
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {

        //String user = System.getProperty("user.name");
        String user = "test-01";

        log.info("user: {}", user);
        return Optional.of(user);
    }
}
