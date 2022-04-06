package com.web.posts;
import com.domain.posts.Posts;
import com.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After//junit 단위 테스트 끝 이후 실행-전체 테스트 막기
    public void clenup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("1234@gmail.com").build());
        //inert/update 실행

        List<Posts> postsList = postsRepository.findAll();//조회
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

}
