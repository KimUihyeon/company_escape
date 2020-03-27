package com.company.escape.controllers;

import com.company.escape.util.rest.BusRouteRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kuh
 * @since 2020.03.27
 */

@RestController
@RequestMapping(value = "/api/v1/bus/", produces = "application/json; charset=UTF-8")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BusStopApiController {

    @Autowired
    private BusRouteRequester busRouteRequester;

    @GetMapping("busTest")
    public String busTest(){
        return this.busRouteRequester.getBusRouteInfo(233000031);
    }


}
