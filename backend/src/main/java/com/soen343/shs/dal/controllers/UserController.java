package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="api/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String username, String password) {

        User u = User.builder().username(username).password(password).build();
        userRepository.save(u);
        return "Saved";
    }
}
