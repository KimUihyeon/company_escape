package com.company.escape.util.rest;

import com.company.escape.util.serialize.JsonUtil;
import com.company.escape.util.serialize.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 특정 REST api에서 데이터를 받아오는 역활을 함.
 *
 * @author kuh
 * @since 2020.03.27
 */

@Component
public class RestRequester {

    @Autowired
    private SerializeUtil serializeUtil;

    @Autowired
    private JsonUtil jsonUtil;

    /**
     * 실제 REST API를 요청함..
     *
     * @param uri
     * @return
     */
    public String responseRestFul(UriComponents uri) {
        String responceData = null;
        try{

            URL url = new URL(uri.toUriString());
            System.out.println("REST api request -> "+uri.toUriString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");


            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + System.lineSeparator());
                }

                responceData = sb.toString();

                System.out.println("responce XML -> " + responceData);

                responceData = this.serializeUtil.xmlToJson(responceData);
                System.out.println("responce json -> " + responceData);
            }
            else {

            }
        }catch (Exception e){
            System.out.println(e);
        }

        return responceData;
    }


}
