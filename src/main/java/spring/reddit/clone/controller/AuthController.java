package spring.reddit.clone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.reddit.clone.dto.AuthenticationResponse;
import spring.reddit.clone.dto.LoginRequest;
import spring.reddit.clone.dto.RegisterRequest;
import spring.reddit.clone.exeption.SpringRedditException;
import spring.reddit.clone.service.AuthService;

//Avoid business logic in controllers
//RESPONSIBILITY TO RECIEVE REQUESTS FROM CLIENT AND DIRECT TO SERVICE
//Functionality should be in the service
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody RegisterRequest registerRequest) throws SpringRedditException {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK );
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws SpringRedditException {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
