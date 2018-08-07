package com.tinhcao.spring.springinactionfifthedition.controller;

import com.tinhcao.spring.springinactionfifthedition.request.RegistrationForm;
import com.tinhcao.spring.springinactionfifthedition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@RequestBody RegistrationForm form) {
        userService.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
