package project.demo.domain.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.demo.web.dto.PostsListResponseDto;
import project.demo.web.dto.PostsResponseDto;
import project.demo.web.dto.PostsSaveRequestDto;
import project.demo.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Long id = postsRepository.save(requestDto.toEntity()).getId();
        return id;
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle() , requestDto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id) {

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        //postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        List<PostsListResponseDto> collect = postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts)).collect(Collectors.toList());
        // (PostsListResponseDto::new) =>  (posts -> new PostsListResponseDto(posts)) 와 같다.
        return collect;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
        // deleteById(Long id) 가 존재하지만 해당 id가 있는지 확인한다음에 삭제하는것이 좋다.
    }
}
