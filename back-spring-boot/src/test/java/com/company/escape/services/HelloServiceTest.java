package com.company.escape.services;

import com.company.escape.util.rest.BusRouteRequester;
import com.company.escape.util.rest.RestRequester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * @author kuh
 * @since 2020.03.27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    @Rollback(true)
    public void test() {
        Object obj = new Object();
        helloService.test();

    }

    @Autowired
    private BusRouteRequester busRouteRequester;

    @Test
    public void test2(){
        this.busRouteRequester.getBusRouteInfo(233000031);
    }
}
