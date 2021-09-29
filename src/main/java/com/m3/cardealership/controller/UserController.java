/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controller;

import com.m3.cardealership.dao.UserDao;
import com.m3.cardealership.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author junha
 */

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserDao userDao;
    
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public UserController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        
        // 1. UserDao adds new user to database
        userDao.addUser(user);
        
        // 2. Reload the in memory user details
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getUserType()));
        inMemoryUserDetailsManager.createUser(new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),  grantedAuthorityList));

        return "redirect:/admin";
    }
    
    @PostMapping("/editUser")
    public String editUser(User user){
        // 1. UserDao updates existing user in database
        userDao.updateUser(user);
        
        // 2. Reload the in memory user details
        inMemoryUserDetailsManager.deleteUser(user.getUserEmail());
        
        return "redirect:/admin";
    }
    
}
