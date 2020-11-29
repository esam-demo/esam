package com.esam.util;

import org.springframework.util.Assert;

/**
 * @author trutao
 * @create 2020-11-29 15:52
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();
        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
        }
        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);

    }

    private static UserContext createEmptyContext() {
        return new UserContext();
    }
}
