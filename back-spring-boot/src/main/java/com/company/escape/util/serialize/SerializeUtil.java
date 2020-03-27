package com.company.escape.util.serialize;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

/**
 * Data 직렬화 , 역직렬화 유틸클래스
 *
 * @author kuh
 * @since 2020.03.27
 */

@Component
public class SerializeUtil {

    public String XmlToJson(String xmlStr){
        JSONObject jObject = XML.toJSONObject(xmlStr);
        return jObject.toString();
    }


    public void XmlToObjectMapping(String xmlStr){
        JSONObject jObject = new JSONObject(xmlStr);
        jObject.toString();
    }
}
