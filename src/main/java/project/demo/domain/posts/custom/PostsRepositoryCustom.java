package project.demo.domain.posts.custom;

import project.demo.domain.posts.Posts;

import java.util.List;

public interface PostsRepositoryCustom {

    List<Posts> findAllDesc();

}
