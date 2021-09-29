/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.User;
import java.util.List;

/**
 *
 * @author junha
 */
public interface UserDao {
    User getUserByEmailPW(String email, String pw);
    List<User> getAllUsers();
    User addUser(User user);
    void updateUser(User user);
}
