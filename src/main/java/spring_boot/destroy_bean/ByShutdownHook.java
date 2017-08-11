package spring_boot.destroy_bean;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
@SpringBootApplication
public class ByShutdownHook {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ByShutdownHook.class)
                .run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> applicationContext.getBeanFactory().destroySingletons()));
    }
}
