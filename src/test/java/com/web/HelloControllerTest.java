package com.web;
import com.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) //스프링 부트 테스트와 junit 사이 연결자
@WebMvcTest(controllers = HelloController.class,
            excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes= SecurityConfig.class)
            }
        )//mvc controller 사용, service 사용 x

public class HelloControllerTest {

    @Autowired // bean주입
    private MockMvc mvc; //api테스트 get,post

  @Test
  @WithMockUser(roles="USER")
    public void hello가_리턴된다() throws Exception{
        String hello ="hello";

        mvc.perform(get("/hello")) //hello get요청
                .andExpect(status().isOk()) //결과 검증, 200
                .andExpect(content().string(hello));//hello 리턴 검증
    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount=1000;
        //System.out.println("11111111111111111111111"+name);
        mvc.perform(
                get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name",is(name)))
                .andExpect(jsonPath("amount",is(amount)));
    }
}
