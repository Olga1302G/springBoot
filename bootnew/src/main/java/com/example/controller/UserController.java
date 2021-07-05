package com.example.controller;


import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUser(Model model,
                          Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/user";
    }

    @GetMapping("{id}/edituser")
    public String editUser(Model model,
                           @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/edituser";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           @PathVariable("id") long id){

        Set<Role> roles  = new HashSet<Role>();
        roles.add(new Role(2L, "ROLE_USER"));
        user.setRoles(roles);

        userService.editUser(user);
        return "redirect:/user";
    }
}
