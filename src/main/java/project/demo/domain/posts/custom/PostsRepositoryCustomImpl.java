package project.demo.domain.posts.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import project.demo.domain.posts.Posts;

import javax.persistence.EntityManager;
import java.util.List;

import static project.demo.domain.posts.QPosts.posts;


public class PostsRepositoryCustomImpl {

    private final JPAQueryFactory queryFactory;

    public PostsRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public List<Posts> findAllDesc() {
//        List<Posts> fetch = queryFactory.select(posts).from(posts).orderBy(posts.id.desc()).fetch();
//        return fetch;
//    }
}
