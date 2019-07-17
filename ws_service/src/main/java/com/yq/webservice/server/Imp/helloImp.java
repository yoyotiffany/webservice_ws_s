package com.yq.webservice.server.Imp;

import com.yq.webservice.server.hello;

import javax.jws.WebService;

/**
 * author：yq
 * date: 2019/7/12
 */
@WebService(endpointInterface = "com.yq.webservice.server.hello", serviceName = "hello")
public class helloImp implements hello {
    @Override
    public String sayHello() {
        return "Hello World";
    }

}
