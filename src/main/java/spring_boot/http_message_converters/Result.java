package spring_boot.http_message_converters;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by zhipeng.zuo on 2017/8/11.
 */
@Data
@NoArgsConstructor
public class Result {
    private int code;
    private String message;
    private List<Person> data;
}
