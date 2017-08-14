package spring_boot.http_message_converters;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhipeng.zuo on 2017/8/11.
 */
@Data
@NoArgsConstructor
public class Cat {
    private long id;
    private String name;
    private String type;


}
