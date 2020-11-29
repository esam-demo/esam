package com.esam.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author trutao
 * @create 2020-11-29 20:15
 */

@Getter
@Configuration
public class ServiceConfig {
    @Value("${signing.key}")
    private String jwtSigningKey = "";
}
