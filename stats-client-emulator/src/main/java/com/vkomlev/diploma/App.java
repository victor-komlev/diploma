package com.vkomlev.diploma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vkomlev.diploma.services.TestService;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    public static final String USER_NAME_PROPERTY = "employee.user.name";

    public static void main(String[] args) {
        LOG.info("++++++++++++++APPLICATION STARTED!!!!++++++++++++++");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

        LOG.info("Context Loaded {}", context);

        TestService service = context.getBean(TestService.class);
        service.startTestService();

    }
}
