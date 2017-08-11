package spring_boot;

import spring_boot.application_event.MyApplicationEnvironmentPreparedEvent;
import spring_boot.application_event.MyApplicationStartingEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
@SpringBootApplication
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的<tx:annotation-driven/>
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new MyApplicationStartingEvent());
        app.addListeners(new MyApplicationEnvironmentPreparedEvent());
        app.run(args);
    }
}