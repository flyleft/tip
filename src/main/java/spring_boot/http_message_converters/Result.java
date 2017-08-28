package spring_boot.http_message_converters;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhipeng.zuo on 2017/8/11.
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;
}
