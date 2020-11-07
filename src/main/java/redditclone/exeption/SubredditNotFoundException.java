package redditclone.exeption;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String message) {
        super(message);
    }
}
