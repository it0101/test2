package com.web;

import com.service.posts.PostsService;
import com.web.dto.PostsResponseDto;
import com.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//service와 bean 객체 연결
@RestController
public class PostApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return  postsService.save(requestDto);

    }

    @PutMapping("/api/v1/posts/{id}")
        public long update(@PathVariable Long id,  @RequestBody PostsSaveRequestDto requestDto){
        System.out.println("idididid11111145"+id);
        return  postsService.update(id, requestDto);

    }


    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        System.out.println("idididid11111145"+id);
        return  postsService.findById(id);

    }

}
