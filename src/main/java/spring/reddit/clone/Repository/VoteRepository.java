package spring.reddit.clone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.reddit.clone.Model.Post;
import spring.reddit.clone.Model.User;
import spring.reddit.clone.Model.VerificationToken;
import spring.reddit.clone.Model.Vote;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
//    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
