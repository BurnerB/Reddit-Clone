package redditclone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redditclone.Model.Post;
import redditclone.Model.Subreddit;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllBySubreddit(Subreddit subreddit);

//    List<Post> findByUser(User user);

}
