package com.company.escape.util.serialize;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author kuh
 * @since 2020.03.29
 */
@Component
public class JsonUtil {

    public String getJsonValue(String jsonStr, String[] jsonKeys) {

//        Class<?> resultObject = lastNodeTypes;
//        Object resultObject = null;
        String targetJsonString = jsonStr;

        for (int i = 0; i < jsonKeys.length; i++) {
            String jsonKey = jsonKeys[i];
            try {
                targetJsonString = jsonValueByKey(targetJsonString, jsonKey);

            } catch (IllegalArgumentException e) {
                try {
                    throw new Exception("키를 찾을수 없습니다");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return targetJsonString;
//        return resultObject.getClass().cast(targetJsonString);
    }

    private String jsonValueByKey(String jsonStr, String key) throws IllegalArgumentException {
        JSONObject jobject = new JSONObject(jsonStr);
        return String.valueOf(jobject.get(key));
    }
}
