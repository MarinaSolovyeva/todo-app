package com.todolist.todolistproject.service;

import com.todolist.todolistproject.model.User;
import com.todolist.todolistproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**Method to saveUser() delete all old information about User. So if we want to update some info, we should set
     * all old info, which User doesn't see in profile
     */
    @Transactional
    public void update(long id, User updatedUser) {
        Optional<User> oldUser = userRepository.findById(id);
        updatedUser.setId(id);
        updatedUser.setPassword(oldUser.get().getPassword());
        updatedUser.setRole(oldUser.get().getRole());
        updatedUser.setTasks(oldUser.get().getTasks());
        userRepository.save(updatedUser);
    }

    @Transactional
    public User getUser(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User findByName(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.orElse(null);
    }

}