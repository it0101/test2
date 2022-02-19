package com.web;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class) //스프링 부트 테스트와 junit 사이 연결자
@WebMvcTest(controllers = HelloController.class)//mvc controller 사용, service 사용 x
public class HelloControllerTest {

    @Autowired // bean주입
    private MockMvc mvc; //api테스트 get,post

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello ="hello";

        mvc.perform(get("/hello")) //hello get요청
                .andExpect(status().isOk()) //결과 검증, 200
                .andExpect(content().string(hello));//hello 리턴 검증
    }
}
