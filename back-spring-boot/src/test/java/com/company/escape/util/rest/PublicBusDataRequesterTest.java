package com.company.escape.util.rest;

import com.company.escape.util.serialize.JsonUtil;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kuh
 * @since 2020.03.29
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicBusDataRequesterTest {

    @Autowired
    private PublicBusDataRequester publicBusDataRequester;

    @Autowired
    private JsonUtil jsonUtil;


    @Test
    public void getRouteListByRouteNameTest() {

        //given
        String busName = "3200"; //92-1번 버스;
        String[] keys = {"response", "msgBody", "busRouteList"};


        //when
        String responseJson = this.publicBusDataRequester.getRouteListByRouteName(busName);
        String resultStr = this.jsonUtil.getJsonValue(responseJson, keys);


        //then
        System.out.println(resultStr);
    }


    /**
     * 공공데이터 포털에서 제공한 테스트 샘플
     */
    @Test
    public void restConnectionTestFunction() {

        // given
        // when
        boolean isConnection = this.publicBusDataRequester.apiConnectionTest();
        System.out.println(isConnection);
        Assert.assertTrue(isConnection);
        // then

    }


    @Test
    public void testBusInfoData() {
        // given
        Map<String, String> keywords = new HashMap<>();
        keywords.put("정자역", "07624");
        keywords.put("시흥영업소", "25847");

        String findBusStopName = "시흥영업소";

        // when
        String jsonData = this.publicBusDataRequester.getBusStopInfos(keywords.get(findBusStopName));

        String[] keys = {"response", "msgBody", "busStationList", "mobileNo"};
        String[] stationIdRoot = {"response", "msgBody", "busStationList", "stationId"};
        String mobileNo = this.jsonUtil.getJsonValue(jsonData, keys);
        String stationId = this.jsonUtil.getJsonValue(jsonData, stationIdRoot);

        System.out.println("stationIdRoot ->" + stationId);


        // then

        Assert.assertEquals(keywords.get(findBusStopName), mobileNo);
    }


    @Test
    public void testRouteInfo() {

        //given

        String busStopSerialNumber = "07624";
        String jsonData = this.publicBusDataRequester.getBusStopInfos(busStopSerialNumber);

        String[] stationIdKeys = {"response", "msgBody", "busStationList", "stationId"};
        String busStopInfoJson = this.jsonUtil.getJsonValue(jsonData, stationIdKeys);


//        String[]

        System.out.println(busStopInfoJson);
    }

}
