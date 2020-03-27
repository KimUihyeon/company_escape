package com.company.escape.schedulors;

import com.company.escape.services.HelloService;
import com.company.escape.util.rest.RestRequester;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author kuh
 * @since 2020.03.27
 */

@Component
public class HelloSchedulor {

    @Autowired
    private HelloService helloService;

    @Autowired
    private RestRequester restRequester;

    public HelloSchedulor(){
        System.out.println("컴포넌트 생성");
    }

    @Scheduled(initialDelay = 5000 , fixedDelay = 5000)
    public void schedulingTest(){
    }
}
