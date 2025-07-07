package com.cg.UserManagementSpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cg.UserManagementSpringSecurity.entity.User;
import com.cg.UserManagementSpringSecurity.service.UserService;

@Controller
public class userController {
@Autowired
private UserService userService;
@GetMapping("/users")
public String listUsers(Model model)
{
model.addAttribute("users",userService.getAllUsers());
return "users";
}
@PostMapping("/users")
public String saveUser(@ModelAttribute User user)
{
userService.saveUser(user);
return "redirect:/users";
}
@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable Long id)
{
userService.deleteUser(id);
return "redirect:/users";
}

public String loginPage()
{
return "login";
}

}