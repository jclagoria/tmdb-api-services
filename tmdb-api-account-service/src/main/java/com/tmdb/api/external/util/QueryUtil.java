package com.tmdb.api.external.util;

import java.util.HashMap;
import java.util.Map;

public class QueryUtil {

    static final String LANGUAGE_DEFAULT_VALUE = "en-US";
    static final String SORT_BY_DEFAULT_VALUE = "created_at.asc";

    static final long PAGE_DEFAULT_VALUE = 1;

    public static Map<String, Object> buildQueryMap(String sessionId, String language,
                                                    long page, String sortBy) {
        Map<String, Object> queryParams = new HashMap<>();

        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("language", language != null  ? language : LANGUAGE_DEFAULT_VALUE);
        queryParams.put("page", page > 0 ? page : PAGE_DEFAULT_VALUE);
        queryParams.put("sort_by", sortBy != null ? sortBy : SORT_BY_DEFAULT_VALUE);

        return queryParams;
    }

    public static Map<String, Object> buildQueryMap(String sessionId) {
        Map<String, Object> queryParams = new HashMap<>();

        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }

        return queryParams;
    }

    public static Map<String, Object> buildQueryMap(String sessionId, long page) {
        Map<String, Object> queryParams = new HashMap<>();

        if(sessionId != null){
            queryParams.put("session_id", sessionId);
        }
        queryParams.put("page", page > 0 ? page : PAGE_DEFAULT_VALUE);

        return queryParams;
    }
}
