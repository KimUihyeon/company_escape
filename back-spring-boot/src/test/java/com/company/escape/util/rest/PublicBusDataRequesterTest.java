package com.company.escape.util.rest;

import com.company.escape.util.serialize.JsonUtil;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


    /**
     * 공공데이터 포털에서 제공한 테스트 샘플
     */
    @Test
    public void restConnectionTestFunction() {

        // given
        boolean isConnection = this.publicBusDataRequester.apiConnectionTest();
        System.out.println(isConnection);
        Assert.assertTrue(isConnection);
        // then

    }


    @Test
    public void testBusInfoData() {
        // given

        String interesteBusStopName = "";
        String keyWord = "";

        // when
        String jsonData = this.publicBusDataRequester.getBusStopInfos(keyWord);

        JSONObject jobject = new JSONObject(jsonData);
        Object searchedBusStopName = jobject.get("");

        // then

        Assert.assertEquals(interesteBusStopName, searchedBusStopName.toString());

    }

}
