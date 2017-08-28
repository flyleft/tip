package spring_boot.http_message_converters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhipeng.zuo on 2017/8/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private long id;
    private String name;
    private int age;
}
