package com.service.posts;
import com.domain.posts.PostsRepository;
import com.web.dto.PostsResponseDto;
import com.web.dto.PostsSaveRequestDto;
import com.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.domain.posts.Posts;
import java.util.List;
import java.util.stream.Collectors;
import com.web.dto.PostsListsResponseDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public long update(Long id, PostsUpdateRequestDto requestDto) {
        System.out.println("id"+id);
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음 id:" + id));
        System.out.println("idid"+id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        System.out.println("ididid"+id);
        return id;
    }

    @Transactional
    public long delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음 id:" + id));
        System.out.println("idid"+id);
        postsRepository.delete(posts);
        System.out.println("ididid"+id);
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {

        System.out.println("1111111111111111111112");
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음 id:" + id));
System.out.println("1111111111111111111113"+entity);
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) /*리드온리는 조회기능만 있어 속도 개선*/
    public List<PostsListsResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListsResponseDto::new)/*.map(posts->new PostsListsResponseDto(posts)와 같다)*/
                .collect(Collectors.toList());

    }



}



