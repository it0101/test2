package com.web;
import com.config.auth.LoginUser;
import com.config.auth.dto.SessionUser;
import com.service.posts.PostsService;
import com.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());

        /*@LoginUser로 아래내용 대체*/
        /*SessionUser user = (SessionUser) httpSession.getAttribute("user");*//*로그인성공시 세션에서 user*/
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable long id, Model model){
        System.out.println("111111111111111111111");
        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post",dto);
        System.out.println("1111111111111111111114"+dto);
        return "posts-update";

    }

}
