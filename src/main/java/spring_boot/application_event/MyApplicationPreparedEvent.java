package spring_boot.application_event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
/*
        生效，在Application中配置
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new MyApplicationPreparedEvent());
        app.run(args);
 */
@Slf4j
public class MyApplicationPreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext cac = event.getApplicationContext();
        passContextInfo(cac);
    }
    private void passContextInfo(ApplicationContext cac) {
    }
}
