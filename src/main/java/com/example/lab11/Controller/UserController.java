package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

        @GetMapping("/get")
        public ResponseEntity getUser() {
        return ResponseEntity.status(200).body(userService.getAllUsers());

    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("is added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id ,@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String Message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(Message);
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body("is updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("is deleted");

    }
    @GetMapping("/get/{username}{password}")
    public ResponseEntity searchUserAndPassword(@RequestBody String username, @PathVariable int password){
        List<User>  users = userService.findUserByUsernameAndPassword(username,password);
        return ResponseEntity.status(200).body("found user");
    }


}
