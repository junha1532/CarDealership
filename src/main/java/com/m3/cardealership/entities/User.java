/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.entities;

import java.util.Objects;

/**
 *
 * @author junha
 */
public class User {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userType;
    private String userEmail;
    private String password;

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userType=" + userType + ", userEmail=" + userEmail + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.userId;
        hash = 59 * hash + Objects.hashCode(this.userFirstName);
        hash = 59 * hash + Objects.hashCode(this.userLastName);
        hash = 59 * hash + Objects.hashCode(this.userType);
        hash = 59 * hash + Objects.hashCode(this.userEmail);
        hash = 59 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userFirstName, other.userFirstName)) {
            return false;
        }
        if (!Objects.equals(this.userLastName, other.userLastName)) {
            return false;
        }
        if (!Objects.equals(this.userType, other.userType)) {
            return false;
        }
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


  
    
}
