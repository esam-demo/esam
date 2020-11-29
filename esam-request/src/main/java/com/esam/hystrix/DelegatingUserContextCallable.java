package com.esam.hystrix;

import com.esam.util.UserContext;
import com.esam.util.UserContextHolder;

import java.util.concurrent.Callable;

/**
 * @author trutao
 * @create 2020-11-29 17:58
 */
public class DelegatingUserContextCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private UserContext originalUserContext;


    public DelegatingUserContextCallable(Callable<V> delegate, UserContext userContext) {
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    /**
     * It will be called before calling the method which annotated `@HystrixCommand`
     * @return
     * @throws Exception
     */
    @Override
    public V call() throws Exception {
        // setup UserContext and store it into threadlocal so that the UserContext is correlated with the method that Hystrix protect.
        UserContextHolder.setContext(originalUserContext);
        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
