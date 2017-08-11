package spring_boot.ExceptionHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
@Slf4j
@Data
public class CustomErrorType {
    private int code;
    private String msg;

    public CustomErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
