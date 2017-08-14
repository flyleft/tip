package spring_boot.http_message_converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by zhipeng.zuo on 2017/8/11.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ConverterController.class)
@AutoConfigureRestDocs
public class TestConverterController {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void testSingleRequestBody() throws Exception {
        String result=this.mvc.perform(
                post("/persons")
                        .header("access_token", "2312")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(
                        Person.builder().name("per").age(23).id(1).build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andDo(document("list-persons",
                        requestHeaders(
                                headerWithName("access_token").description(
                                "Basic auth credentials"))))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testMultipleRequestBody() throws Exception {
        String result=this.mvc.perform(
                post("/persons/ceshi")
                        .header("access_token", "2312")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(
                                Person.builder().name("per").age(23).id(1).build())
                        +objectMapper.writeValueAsString(
                                Cat.builder().id(1).name("sd").type("bosi").build())
                        )
                      )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andDo(document("list-persons",
                        requestHeaders(
                                headerWithName("access_token").description(
                                        "Basic auth credentials"))))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void  getJson() throws Exception{
        String data=objectMapper.writeValueAsString(Person.builder().name("per").age(23).id(1).build())
                +objectMapper.writeValueAsString(Cat.builder().id(1).name("sd").type("bosi").build());

        System.out.println(data);

    }


}
