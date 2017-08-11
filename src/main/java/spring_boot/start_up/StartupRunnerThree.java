package spring_boot.start_up;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
/*
 1。

 2。ApplicationRunner中run方法的参数为ApplicationArguments，而CommandLineRunner接口中run方法的参数为String数组。

 3。
 */
@Component
@Slf4j
public class StartupRunnerThree implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        StringBuilder argData=new StringBuilder();
        for (String arg:args.getSourceArgs()){
            argData.append(arg);
        }
        log.info("===StartupRunnerThree==="+argData);
    }
}
