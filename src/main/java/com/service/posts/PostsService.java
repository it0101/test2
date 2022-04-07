package com.service.posts;
import com.domain.posts.PostsRepository;
import com.web.dto.PostsResponseDto;
import com.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.domain.posts.Posts;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public long update(Long id, PostsSaveRequestDto requestDto) {
        System.out.println("idididid11111146"+id);
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음 id:" + id));
        System.out.println("idididid11111147"+id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        System.out.println("idididid11111148"+id);
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음 id:" + id));
System.out.println("entityentity"+entity);
        return new PostsResponseDto(entity);
    }
}



