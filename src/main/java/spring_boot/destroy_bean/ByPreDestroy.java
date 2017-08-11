package spring_boot.destroy_bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
@Component
@Slf4j
public class ByPreDestroy {
    @PreDestroy
    public void destroy(){
        log.info("destroy by PreDestroy");
    }
}
