package com.todolist.todolistproject.controller;
import com.todolist.todolistproject.model.User;
import com.todolist.todolistproject.security.UserDetail;
import com.todolist.todolistproject.service.UserDetailService;
import com.todolist.todolistproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;


    @RequestMapping("/")
    public String showMainPage() {
        return "start-page";
    }

    @GetMapping("/profile")
    public String showUserInfo(Model model, Principal principal) {
        UserDetail userDetail = (UserDetail) userDetailService.loadUserByUsername(principal.getName());
        User user = userService.getUser(userDetail.getId());
        model.addAttribute("user", user);
        return "user-pages/profile";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user);
        return "redirect:/logout";
    }
}
