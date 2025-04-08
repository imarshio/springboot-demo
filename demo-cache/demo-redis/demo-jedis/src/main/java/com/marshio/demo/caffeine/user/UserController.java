package com.marshio.demo.caffeine.user;

import org.springframework.web.bind.annotation.*;

/**
 * @author marshio
 * @desc
 * @create 2023-03-31 17:52
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/id/{id}")
    public Boolean deleteUserById(@PathVariable(name = "id") long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/update")
    public User updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }
}
