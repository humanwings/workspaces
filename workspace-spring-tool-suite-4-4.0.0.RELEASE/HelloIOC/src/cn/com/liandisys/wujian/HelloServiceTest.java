package cn.com.liandisys.wujian;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServiceTest {
    @Test
    public void testHelloWorld() {
        ApplicationContext context = new ClassPathXmlApplicationContext("helloIOC.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        helloService.sayHello();
    }
}
