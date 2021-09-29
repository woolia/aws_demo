package project.demo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.demo.config.auth.LoginUser;
import project.demo.config.auth.dto.SessionUser;
import project.demo.domain.posts.PostsService;
import project.demo.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){
        model.addAttribute("posts" , postsService.findAllDesc());

        if (user != null){
            log.info("{}",user.getName());
            model.addAttribute("loginuserName" , user.getName());
            // userName 이라고 하면 window 의 경우에는 window 사용자명이 나온다고 한다 그래서 아무리 userName에 user.getName() 을 넣어서 index로 이동하더라도
            // 계속 window 사용자명 zecra 가 호출되었던 것이다.
            // 따라서 window 사용할때 mustache는 userName 대신 다른 이름을 사용하도록 하자
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id , Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post" , dto);
        return "posts-update";
    }

}
