package com.web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //01 - json 반화 컨트롤러 @ResponseBody와 같은
public class HelloController {
    @GetMapping("/hello") //01 - http get 요청 받는 api @RequestMapping (method = RequestMethod.GET) 같은
    public String hello(){
        return "hello";
    }
}
