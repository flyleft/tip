package spring_boot.start_up;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
@Component
@Slf4j
public class PostConstructInit {

    @PostConstruct
    public void init(){
        log.info("===PostConstructInit===");
    }
}
