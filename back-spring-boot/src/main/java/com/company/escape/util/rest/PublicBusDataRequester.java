package com.company.escape.util.rest;

import com.company.escape.util.serialize.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
public class PublicBusDataRequester {

    @Value("${API_KEY}")
    private String API_KEY;

    @Value("${BUS_STOP_INFO_API_URL}")
    private String BUS_STOP_INFO_API_URL;

    @Value("${UP_STATION_JUNGJA_ID")
    private String UP_STATION_JUNGJA_ID;

    @Value("${UP_STATION_SIHUNG_ID}")
    private String UP_STATION_SIHUNG_ID;

    @Value("${IN_COMMING_BUS_ALL_ROUTE_API_URL}")
    private String IN_COMMING_BUS_ALL_ROUTE_API_URL;

    @Value("${ROUTE_LIST_API_URL}")
    private String ROUTE_LIST_API_URL;


    @Autowired
    private RestRequester restRequester;

    @Autowired
    private JsonUtil jsonUtil;

    private MultiValueMap<String, String> defaultApiParams() {
        Map<String, String> map = new HashMap<>();
        map.put("ServiceKey", API_KEY);
        try {
            map.put("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new LinkedMultiValueMap(map);
    }


    public String getRouteListByRouteName(String routeName){
        try{

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(this.ROUTE_LIST_API_URL)
                    .queryParam("ServiceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"))
                    .queryParam("keyword", routeName)
                    .build(false);

            String responceJson = this.restRequester.requestRestFul(uri);
            return responceJson ;
        }catch (UnsupportedEncodingException e ){
            e.printStackTrace();
            return null;
        }
    }


    public String getBusStationInRouts(String stationId){
        try{

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(this.IN_COMMING_BUS_ALL_ROUTE_API_URL)
                    .queryParam("ServiceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"))
                    .queryParam("stationId", stationId)
                    .build(false);

            String responceJson = this.restRequester.requestRestFul(uri);
//            String[] keys = {}

        }catch (UnsupportedEncodingException e ){
            e.printStackTrace();
        }

        return null;
    }

    public boolean apiConnectionSuccess(UriComponents url){
        String result = this.restRequester.requestRestFul(url);
        return apiConnectionSuccess(result);
    }

    public boolean apiConnectionSuccess(String jsonStr){
        try {

            String[] jsonMsgCodeKeys = {"response", "comMsgHeader", "returnCode"};
            int code = Integer.MIN_VALUE;

            code = Integer.parseInt(this.jsonUtil.getJsonValue(jsonStr, jsonMsgCodeKeys));

            String errorStr = null;
            switch (code) {
                case 0: {
                    return true;
                }
                case Integer.MIN_VALUE: {
                    errorStr = "api 호출에서 다음 코드가 발생하였습니다. returnCode ->" + code;
                    break;
                }
                default: {
                    String[] jsonMsgKey = {"response", "comMsgHeader", "errMsg"};
                    String errorMsg = this.jsonUtil.getJsonValue(jsonStr, jsonMsgKey);
                    errorStr = "api 호출에서 다음 코드가 발생하였습니다. returnCode ->" + code + "("+errorMsg+ ")";
                    break;
                }
            }

            throw new IllegalAccessException(errorStr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

    public boolean apiConnectionTest() {
        boolean isSuccess = false;

        try {
            //api 샘플데이터

            UriComponents url = UriComponentsBuilder.fromHttpUrl(BUS_STOP_INFO_API_URL)
                    .queryParam("ServiceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"))
                    .queryParam("routeId", String.valueOf(233000031))
                    .build(false);

            isSuccess = apiConnectionSuccess(url);
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return isSuccess;
    }




    /**
     * /**
     * 버스 해당 노선번호로 정보를 가지고옴
     *
     * @param routeId
     * @return
     */
    public String getBusRouteInfo(int routeId) {

        String result = null;

        try {

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(BUS_STOP_INFO_API_URL)
                    .queryParam("ServiceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"))
                    .queryParam("routeId", String.valueOf(routeId))
                    .build();

            result = this.restRequester.requestRestFul(uri);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println(result);
        return result;
    }

    /**
     * key word를 기반으로 정류장정보를 뽑아옴 ..!
     * 관심 정류장을 찾기 위해 사용..!
     *
     * @param keyword 2글자 이상
     * @return
     */
    public String getBusStopInfos(String keyword) {

        String result = null;


        try {
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(BUS_STOP_INFO_API_URL)
                    .queryParam("ServiceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"))
                    .queryParam("keyword", String.valueOf(keyword))
                    .build(false);

            result = this.restRequester.requestRestFul(uri);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


}
