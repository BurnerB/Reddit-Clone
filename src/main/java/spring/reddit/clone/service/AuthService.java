package spring.reddit.clone.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.reddit.clone.Model.NotificationEmail;
import spring.reddit.clone.Model.User;
import spring.reddit.clone.Model.VerificationToken;
import spring.reddit.clone.Repository.UserRepository;
import spring.reddit.clone.Repository.VerificationTokenRepository;
import spring.reddit.clone.controller.dto.LoginRequest;
import spring.reddit.clone.controller.dto.RegisterRequest;
import spring.reddit.clone.exeption.SpringRedditException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

//field vs constructor injection??
@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) throws SpringRedditException {
        User user= new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token=generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken= new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) throws SpringRedditException {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) throws SpringRedditException {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void login(LoginRequest loginRequest) {
    }
}