/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.UserDao;
import com.m3.cardealership.entities.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Daniel
 */
@Controller
public class AccountController {
    @Autowired
    UserDao userDao;
    //changePassword
    
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public AccountController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
    
    @PostMapping("/Account/updatePassword")
    public String updatePassword(HttpServletRequest request, @RequestParam("email") String email){
        User user = userDao.getUserByEmail(request.getRemoteUser());//issue getting logged in user
        user.setPassword(request.getParameter("password"));
        // 1. UserDao updates existing user in database
        userDao.updateUser(user);
        
        // 2. Reload the in memory user details
        inMemoryUserDetailsManager.deleteUser(user.getUserEmail());
        
        return "redirect:/";
    }
}
