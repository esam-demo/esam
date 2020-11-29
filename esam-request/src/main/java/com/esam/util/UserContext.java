package com.esam.util;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author trutao
 * @create 2020-11-29 15:53
 */
@Component
@Data
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String RES_ID = "tmx-res-id";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String resId = new String();

}
