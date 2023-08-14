package com.todolist.todolistproject.controller;
import com.todolist.todolistproject.DTO.UserDto;
import com.todolist.todolistproject.model.User;
import com.todolist.todolistproject.service.RegistrationService;
import com.todolist.todolistproject.service.UserService;
import com.todolist.todolistproject.util.UserValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator, ModelMapper modelMapper, AuthenticationManager authenticationManager, UserService userService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.modelMapper = modelMapper;
//        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "auth/login";}

    @GetMapping ("/token")
    public String confirmLogin(Principal principal) {
        User user = userService.findByName(principal.getName());
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword());

//        String token = jwtUtil.generateToken(user.getUsername());
        return "redirect:/";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") UserDto userDto) {
        return "auth/registration";
    }

    @PostMapping ("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult bindingResult) {
        User user = convertToUser(userDto);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.register(userDto);
        return "redirect:/auth/login";
    }
    public User convertToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }
}