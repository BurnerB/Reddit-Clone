package redditclone.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redditclone.Model.Post;
import redditclone.Model.Subreddit;
import redditclone.Model.User;
import redditclone.Repository.PostRepository;
import redditclone.Repository.SubredditRepository;
import redditclone.Repository.UserRepository;
import redditclone.dto.PostRequest;
import redditclone.dto.PostResponse;
import redditclone.exeption.PostNotFoundException;
import redditclone.exeption.SubredditNotFoundException;
import redditclone.mapper.PostMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));

    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
