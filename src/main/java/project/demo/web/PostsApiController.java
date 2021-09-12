package project.demo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.demo.domain.posts.PostsService;
import project.demo.web.dto.PostsResponseDto;
import project.demo.web.dto.PostsSaveRequestDto;
import project.demo.web.dto.PostsUpdateRequestDto;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }


    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id , @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id , requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }


    @DeleteMapping("/api/v1/posts/{id}")
    public Long postsDelete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
