package com.web;

import com.service.posts.PostsService;
import com.web.dto.PostsResponseDto;
import com.web.dto.PostsSaveRequestDto;
import com.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//service와 bean 객체 연결
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){

        System.out.println("111111");
        return  postsService.save(requestDto);

    }


    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        System.out.println("idididid:"+id);
        return  postsService.findById(id);

    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        System.out.println("updateupdateupdate:"+id);
        return postsService.update(id, requestDto);

    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        System.out.println("updateupdateupdate:"+id);
         postsService.delete(id);
        return id;
    }

    

}
