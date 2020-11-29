package com.esam.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author trutao
 * @create 2020-11-23 15:55
 */
@Getter
@Component
public class PropertiesConfig {

    @Value("${example.property}")
    private String exampleProperty;
}
