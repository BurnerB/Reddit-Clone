package spring.reddit.clone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.reddit.clone.Model.Subreddit;
import spring.reddit.clone.Repository.SubredditRepository;
import spring.reddit.clone.dto.SubredditDto;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save = subredditRepository.save(mapSubredditDto(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;

    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll(){
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().subredditName(subreddit.getName())
                .id(subreddit.getId())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getSubredditName())
                .description(subredditDto.getDescription())
                .build();
    }


}
