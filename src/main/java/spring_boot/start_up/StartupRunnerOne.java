package spring_boot.start_up;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */

@Component
@Order(value=1)
@Slf4j
public class StartupRunnerOne implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        StringBuilder argData=new StringBuilder();
        for (String arg:args){
            argData.append(" ").append(arg);
        }
       log.info("===StartupRunnerOne==="+argData);
    }

}
