package spring_boot.http_message_converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhipeng.zuo on 2017/8/11.
 */
@RestController
@Slf4j
public class ConverterController {

    private Map<Integer,Person> map=new HashMap<>();

    @PostMapping("/persons")
    public Result<?> SingleRequestBody(@RequestBody Person person){
        map.put(person.getAge(),person);
        return new ResultHelper<List<Person>>().setData(map.values().stream().collect(Collectors.toList()));
    }

    @PostMapping("/persons/ceshi")
    public Result<?> multipleRequestBody(@RequestBody Person person,@RequestBody Cat cat){
        map.put(person.getAge(),person);
        log.info("person "+person);
        log.info("cat "+cat);
        return new ResultHelper<List<Person>>().setData(map.values().stream().collect(Collectors.toList()));
    }

}

