/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author junha
 */
@Repository
public class UserDaoDB implements UserDao{
    
    @Autowired
   JdbcTemplate jdbc;

    @Override
    public User getUserByEmailPW(String email, String pw) {
        try {
            final String SELECT_USER_BY_EMAIL = "SELECT * FROM student WHERE userEmail = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_EMAIL, new UserMapper(), email);
            if(user.getPassword().equals(pw)) {
                return user;
            }
            return null;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
               final String SELECT_ALL_USERS = "SELECT * FROM user";
        return jdbc.query(SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    @Transactional
    public User addUser(User user) {
                final String INSERT_USER = "INSERT INTO user(userFirstName, userLastName, userType, userEmail, password) "
                + "VALUES(?,?,?,?,?)";
        try {
            jdbc.update(INSERT_USER,
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getUserType(),
                user.getUserEmail(),
                user.getPassword()
        );
        } catch (DataAccessException   e) {
            return null;
        }
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        return user;
    }
    
    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE user SET userFirstName = ?, userLastName = ?, userType = ?, userEmail = ?,password = ? "
                + "WHERE userEmail = ?";
        jdbc.update(UPDATE_USER,
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getUserType(),
                user.getUserEmail(),
                user.getPassword(),
                
                user.getUserEmail()
        );
    }
    
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("UserId"));
            user.setUserFirstName(rs.getString("userFirstName"));
            user.setUserLastName(rs.getString("userLastName"));
            user.setUserType(rs.getString("userType"));
            user.setUserEmail(rs.getString("userEmail"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }
    
}
