package com.company.escape.util.serialize;

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
public class JsonUtilTest {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private SerializeUtil serializeUtil;

    @Test
    public void jsonValueByKeyTest() {

        //given

        int returnCodeOrg = 20;

        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<response>\n" +
                "    <comMsgHeader>\n" +
                "        <errMsg>SERVICE ACCESS DENIED ERROR.</errMsg>\n" +
                "        <returnCode>" + returnCodeOrg + "</returnCode>\n" +
                "    </comMsgHeader>\n" +
                "    <msgHeader>\n" +
                "        <queryTime>2020-03-29 16:47:38.696</queryTime>\n" +
                "        <resultCode>50</resultCode>\n" +
                "        <resultMessage>comMsgHeader의 에러 메시지를 참조하세요.</resultMessage>\n" +
                "    </msgHeader>\n" +
                "</response>";

        String jsonStr = this.serializeUtil.xmlToJson(xmlStr);
        String[] jsonKeys = {"response", "comMsgHeader", "returnCode"};


        // when
        int returnCode = Integer.MIN_VALUE;


        try {
            String findValue = this.jsonUtil.getJsonValue(jsonStr, jsonKeys);
            returnCode = Integer.parseInt(findValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        String resultMsg = "원본값 -> " + returnCodeOrg + " || 결과값 -> " + returnCode;
        System.out.println(resultMsg);
        Assert.assertEquals(returnCodeOrg, returnCode);
        Assert.assertTrue(returnCode >= 0);
    }
}
