package com.company.escape.util.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

/**
 * 특정 REST api에서 데이터를 받아오는 역활을 함.
 *
 * @author kuh
 * @since 2020.03.27
 */

@Component
public class RestRequester {

    /**
     * URL 뒤에 붙는 쿼리스트링을 만들어
     *
     * @param paramMap
     * @return
     */
    private String getQueryString(Map<String, String> paramMap) {
        List<String> paramList = new ArrayList<>();
        Iterator<String> iter = paramMap.keySet().iterator();

        while (iter.hasNext()) {
            String key = iter.next();
            String data = paramMap.get(key);

            paramList.add(key + "=" + data);
        }

        return "?" + String.join("&", paramList);
    }

    /**
     * Url 과 쿼리스트링을 붙여서 URI로 만들어줌
     *
     * @param url
     * @param paramMap
     * @return
     */
    private String getUrl(String url, Map<String, String> paramMap) {
        String listChar = String.valueOf(url.charAt(url.length() - 1));

        if (("/".equals(listChar))) {
            url = url.substring(0, url.length() - 1);
        }

        return url + getQueryString(paramMap);
    }


    /**
     * 실제 REST API를 요청함..
     *
     * @param url
     * @param paramMap
     * @return
     */
    public Object requestRestFul(String url, Map<String, String> paramMap) {

        String uri = getUrl(url, paramMap);

        RestTemplate rest = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "xml", Charset.forName("utf-8")));
        return rest.exchange(uri, HttpMethod.GET, new HttpEntity<>(header), String.class);
    }


}
