package spring_boot.application_event;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
public class MyApplicationFailedEvent implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Throwable throwable = event.getException();
        handleThrowable(throwable);
    }
    private void handleThrowable(Throwable throwable) {
        // 异常处理
    }

}
