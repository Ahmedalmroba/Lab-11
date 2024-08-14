package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void addUser(User user) {

        userRepository.save(user);
    }
    public void updateUser(Integer id,User user) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRegistrationdate(user.getRegistrationdate());
        userRepository.save(u);
    }
    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        userRepository.delete(u);
    }
    public User findUserByEmail(String email) {
        User u = userRepository.findUserByEmail(email);
        if (u == null) {
            throw new ApiException("user not found");
        }
        return u;
    }
    public List<User> findUserByUsernameAndPassword(String username, int password) {
        List<User> u = userRepository.findUserByUsernameAndPassword(username, password);
        if (u == null) {
            throw new ApiException("user not found");
        }
        return u;
    }

}
