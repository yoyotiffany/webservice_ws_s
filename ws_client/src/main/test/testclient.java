
import com.yq.webservice.server.helloImpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author：yq
 * date: 2019/7/17
 */
public class testclient {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        helloImpService helloService = (helloImpService) context.getBean("helloService");
        String result = helloService.sayHello();
        System.out.print(result);
    }
}
