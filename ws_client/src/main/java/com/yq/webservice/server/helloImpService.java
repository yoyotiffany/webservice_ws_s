package com.yq.webservice.server;

import javax.jws.WebService;

/**
 * author：yq
 * date: 2019/7/12
 */
@WebService(targetNamespace = "http://server.webservice.yq.com/", name = "helloImp")
public interface helloImpService {
    public String sayHello();
}
