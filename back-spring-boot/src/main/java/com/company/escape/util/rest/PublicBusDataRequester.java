package com.company.escape.util.rest;

import com.company.escape.util.serialize.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    @Autowired
    private RestRequester restRequester;

    @Autowired
    private SerializeUtil serializeUtil;

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
     /**
     * 버스 해당 노선번호로 정보를 가지고옴
     *
     * @param routeId
     * @return
     */
    public String getBusRouteInfo(int routeId) {

        String result = null;

        try {

            String urlTemp = "http://openapi.gbis.go.kr/ws/rest/buslocationservice";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(urlTemp)
                    .queryParam("ServiceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY, "UTF-8"))
                    .queryParam("routeId", String.valueOf(routeId))
                    .build();

            String xmlResponse = this.restRequester.requestRestFul(uri);
            result = this.serializeUtil.xmlToJson((xmlResponse));

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
                    .queryParam("serviceKey", API_KEY)
                    .queryParam("serviceKey", URLEncoder.encode(API_KEY,"UTF-8"))
                    .queryParam("keyword", String.valueOf(keyword))
                    .build(false);

            String responseXML = this.restRequester.requestRestFul(uri);
            result = this.serializeUtil.xmlToJson(responseXML);

            System.out.println(result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return result;
    }


}
