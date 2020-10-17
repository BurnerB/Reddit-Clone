package spring.reddit.clone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.reddit.clone.Model.Post;
import spring.reddit.clone.Model.Subreddit;
import spring.reddit.clone.Model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllBySubreddit(Subreddit subreddit);

//    List<Post> findByUser(User user);

}
