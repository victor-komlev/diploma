package com.vkomlev.diploma.stats;

import com.vkomlev.diploma.stats.types.BasicHelloType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import java.sql.Timestamp;

/**
 * Created by vkomlev on 4/10/2016.
 */
@WebService(endpointInterface = "com.vkomlev.diploma.stats.StatService")
public class StatsServiceImpl implements StatService {

    private static final Logger LOG = LoggerFactory.getLogger(StatsServiceImpl.class);

    public BasicHelloType sayHello(BasicHelloType text) {
        LOG.info("sayHello({})", text);
        BasicHelloType helloMessage = new BasicHelloType();
        helloMessage.setHelloTitle("Hello there!");
        helloMessage.setHelloMessage("Now ot is : " + new Timestamp(System.currentTimeMillis()));
        return helloMessage;
    }
}
