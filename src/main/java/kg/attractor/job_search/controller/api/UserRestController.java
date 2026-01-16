package kg.attractor.job_search.controller.api;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/register")
    public HttpStatus createUser(@RequestBody @Valid UserDto userDto){
        userService.create(userDto);
        return HttpStatus.CREATED;
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
    @PutMapping("/{id}")
    public HttpStatus editUser(@PathVariable Integer id, @RequestBody @Valid UserEditDto userEditDto){
        userService.edit(id, userEditDto);
        return HttpStatus.ACCEPTED;
    }

}
