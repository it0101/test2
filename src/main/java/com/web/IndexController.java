package com.web;
import com.service.posts.PostsService;
import com.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
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
