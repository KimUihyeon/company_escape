package com.company.escape.services;

import com.company.escape.util.rest.PublicBusDataRequester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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
}
