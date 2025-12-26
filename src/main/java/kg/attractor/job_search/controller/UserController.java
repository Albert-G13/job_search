package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public HttpStatus createUser(@RequestBody @Valid UserDto userDto){
        return userService.create(userDto);
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String phoneNumber,
                                     @RequestParam(required = false) String email) {
        return userService.searchUsers(name, phoneNumber, email);
    }
    @GetMapping("/exists")
    public boolean existsByEmail(@RequestParam String email){
        return userService.existsByEmail(email);
    }

}
