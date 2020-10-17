package spring.reddit.clone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.reddit.clone.Model.Comment;
import spring.reddit.clone.Model.Post;
import spring.reddit.clone.Model.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findByPost(Post post);
//
//    List<Comment> findAllByUser(User user);
}
