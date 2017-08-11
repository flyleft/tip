package spring_boot.destroy_bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
@Component
@Slf4j
public class ByDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        log.info("destroy by DisposableBean");
    }
}
