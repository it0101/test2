package com.web;
import com.domain.posts.Posts;
import com.domain.posts.PostsRepository;
import com.web.dto.PostsSaveRequestDto;
import com.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록() throws Exception{
        String title="title";
        String content="content";
        PostsSaveRequestDto requestDto=PostsSaveRequestDto.builder().title(title).content(content)
                .author("author").build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        System.out.println("urlurlurl"+url);
        ResponseEntity<Long> responseEntity=restTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void Posts_수정() throws Exception{
        Posts savePosts = postsRepository.save(Posts.builder()
                                        .title("title")
                                        .content("content")
                                       .author("author").build());

        Long updateId=savePosts.getId();
        String expectedTitle="title2";
        String expectedContent="content2";
        System.out.println("idididid");
        PostsUpdateRequestDto requestDto= PostsUpdateRequestDto.builder().title(expectedTitle)
                .content(expectedContent).build();

        System.out.println("idididid");
        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        System.out.println("urlurlurl"+url);
        HttpEntity<PostsUpdateRequestDto> requestsEntity =new HttpEntity<>(requestDto);

        System.out.println("idididid"+updateId);
        ResponseEntity<Long> responseEntity= restTemplate.exchange(url, HttpMethod.PUT, requestsEntity, Long.class);

        System.out.println("idididid");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}
