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
public class SerializeUtilTest {

    @Autowired
    private SerializeUtil serializeUtil;

    /**
     * XML -> Json 변환 테스트
     */
    @Test
    public void xmlToJsonTest(){

        //given

        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<response>\n" +
                "    <comMsgHeader>\n" +
                "        <errMsg>SERVICE ACCESS DENIED ERROR.</errMsg>\n" +
                "        <returnCode>20</returnCode>\n" +
                "    </comMsgHeader>\n" +
                "    <msgHeader>\n" +
                "        <queryTime>2020-03-29 16:47:38.696</queryTime>\n" +
                "        <resultCode>50</resultCode>\n" +
                "        <resultMessage>comMsgHeader의 에러 메시지를 참조하세요.</resultMessage>\n" +
                "    </msgHeader>\n" +
                "</response>";


        // when

        String jsonStr = this.serializeUtil.xmlToJson(xmlStr);



        // then

        Assert.assertNotNull(jsonStr);

    }
}
