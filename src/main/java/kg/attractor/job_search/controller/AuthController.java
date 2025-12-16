package kg.attractor.job_search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    public HttpStatus register(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String type) {
        //TODO register
        return HttpStatus.CREATED;
    }
}
