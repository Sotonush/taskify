package com.example.taskify.controlllers;

import com.example.taskify.entity.User;
import com.example.taskify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Получение всех пользователей
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Регистрация нового пользователя
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.registerUser(username, password);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.updeteUser(user, id);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.allUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
