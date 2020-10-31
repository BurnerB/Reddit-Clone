package redditclone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redditclone.Model.Subreddit;

@Repository
//Our generic arguments
//subreddit is our entity
// Long is the type of our primary key
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
//    Optional<Subreddit> findByName(String subredditName);
}
