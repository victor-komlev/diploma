package com.vkomlev.diploma.stats;

import com.vkomlev.diploma.stats.types.BasicHelloType;

import javax.jws.WebService;

/**
 * Created by vkomlev on 4/10/2016.
 */
@WebService
public interface StatService {
    BasicHelloType sayHello(BasicHelloType text);
}
