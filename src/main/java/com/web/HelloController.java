package com.web;
import com.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //01 - json 반화 컨트롤러 @ResponseBody와 같은
public class HelloController {
    @GetMapping("/hello") //01 - http get 요청 받는 api @RequestMapping (method = RequestMethod.GET) 같은
    public String hello(){
        return "hello111111111";
    }


    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto (@RequestParam("name") String name
                                    , @RequestParam("amount") int amount){

       // System.out.println("name"+name);
        //System.out.println("amount"+amount);

        return new HelloResponseDto(name,amount);
    }

}
