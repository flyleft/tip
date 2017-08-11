package spring_boot.ExceptionHandler;

/**
 * Created by zhipeng.zuo on 2017/8/10.
 */
public class MyException extends RuntimeException {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
