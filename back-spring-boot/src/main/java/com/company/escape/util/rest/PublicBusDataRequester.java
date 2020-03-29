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
    private String BUSSTOP_INFO_API_URL;

    @Value("${BUS_LOCATION_API_URL}")
    private String BUS_LOCATION_API_URL;

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

            UriComponents url = UriComponentsBuilder.fromHttpUrl(BUS_LOCATION_API_URL)
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

    // TODO : 이건 왜 안될까 .. Okky에 물어보자.
    private void test() {

//
//
//        try{
//            StringBuilder urlBuilder = new StringBuilder(urlTemp); /*URL*/
//            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+API_KEY); /*Service Key*/
//            urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode(API_KEY, "UTF-8")); /*인증키(공공데이터포털 발급)*/
//            urlBuilder.append("&" + URLEncoder.encode("routeId","UTF-8") + "=" + URLEncoder.encode("233000031", "UTF-8")); /*노선 ID*/
//
//
//            String data1 = urlBuilder.toString();
//
//
//            uri =UriComponentsBuilder.fromHttpUrl(urlTemp)
//                    .queryParam(URLEncoder.encode("ServiceKey","UTF-8"), API_KEY)
//                    .queryParam(URLEncoder.encode("serviceKey","UTF-8"), URLEncoder.encode(API_KEY, "UTF-8"))
//                    .queryParam(URLEncoder.encode("routeId", "UTF-8"), URLEncoder.encode(String.valueOf(routeId),"UTF-8"))
//                    .build();
//
//            String date2 =uri.toUriString();
//
//            URL url = new URL(date2);
//
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//
//            System.out.println("Response code: " + conn.getResponseCode());
//            BufferedReader rd;
//            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            } else {
//                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//            }
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                sb.append(line);
//            }
//            rd.close();
//            conn.disconnect();
//            System.out.println(sb.toString());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Map<String, String> paramMap = new HashMap<>();
//
//        try {
//            uri =UriComponentsBuilder.fromHttpUrl(urlTemp)
//                    .queryParam(URLEncoder.encode("serviceKey","UTF-8"), URLEncoder.encode(API_KEY, "UTF-8"))
//                    .queryParam(URLEncoder.encode("routeId", "UTF-8"), URLEncoder.encode(String.valueOf(routeId),"UTF-8"))
//                    .build();
//        }catch (Exception e){
//
//        }
//
//        StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/buslocationservice"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=서비스키"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("1234567890", "UTF-8")); /*인증키(공공데이터포털 발급)*/
//        urlBuilder.append("&" + URLEncoder.encode("routeId","UTF-8") + "=" + URLEncoder.encode("233000031", "UTF-8")); /*노선 ID*/
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

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(BUS_LOCATION_API_URL)
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
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(BUSSTOP_INFO_API_URL)
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
