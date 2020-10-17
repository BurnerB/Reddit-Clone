package spring.reddit.clone.exeption;

import org.springframework.mail.MailException;

public class SpringRedditException extends Throwable {
    public SpringRedditException(String exMessage) {
        super(exMessage);
    }
}
