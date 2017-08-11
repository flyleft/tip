package spring_boot.application_event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
/*
        生效，在Application中配置
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new MyApplicationEnvironmentPreparedEvent());
        app.run(args);
 */
@Slf4j
public class MyApplicationEnvironmentPreparedEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println("===MyApplicationEnvironmentPreparedEvent===");
        ConfigurableEnvironment environment = event.getEnvironment();
        MutablePropertySources mps = environment.getPropertySources();
            Iterator<PropertySource<?>> iter = mps.iterator();
            while (iter.hasNext()) {
                PropertySource<?> ps = iter.next();
                //System.out.println("===MyApplicationEnvironmentPreparedEvent===##"+ps.getName()+ps.getSource()+ps.getClass());
        }
    }
}
