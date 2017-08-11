package spring_boot.application_event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
//spring boot启动开始时执行的事件

/*
        生效，在Application中配置
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new MyApplicationStartingEvent());
        app.run(args);
 */
@Slf4j
public class MyApplicationStartingEvent implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        SpringApplication app = event.getSpringApplication();
        app.setBannerMode(Banner.Mode.OFF);
        System.out.println("==MyApplicationStartingEvent==");
    }
}
