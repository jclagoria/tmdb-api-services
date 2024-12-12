package com.tmdb.api.util;

import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Interceptor
@Loggable
public class LoggingInterceptor {

    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class.getName());

    public Object logMethodCall(InvocationContext context) throws Exception {
        long statTime = System.currentTimeMillis();
        String methodName = context.getMethod().getName();

        LOGGER.info(() -> "Starting method: " + methodName );

        try {
            Object result = context.proceed();
            long duration = System.currentTimeMillis() - statTime;
            LOGGER.info(() -> "Completed method: " + methodName + " in " + duration);
            return result;
        } catch (Exception e) {
            LOGGER.severe(() -> "Exception in method: " + methodName + " - " + e.getMessage());
            throw e;
        }
    }

}
