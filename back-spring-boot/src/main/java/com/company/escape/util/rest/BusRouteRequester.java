package com.company.escape.util.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 버스 노선정보를 가져오기 위한 객체.
 *
 * @author kuh
 * @since 2020.03.27
 */
@Component
@PropertySource("classpath:application-api.properties")
public class BusRouteRequester {

    @Value("${API_KEY}")
    private String apiKey;

    @Autowired
    private RestRequester restRequester;

    //    serviceKey=${process.env.REACT_APP_API_KEY}&routeId=233000031

    /**
     * 버스 해당 노선번호로 정보를 가지고옴
     */
    public void getBusRouteInfo(int routeId) {

        String url = "http://openapi.gbis.go.kr/ws/rest/buslocationservice";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("serviceKey", apiKey);
        paramMap.put("routeId", String.valueOf(routeId));

        Object obj = restRequester.requestRestFul(url,paramMap);
        String data = "breakPoint";
    }


}
