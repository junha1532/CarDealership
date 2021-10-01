/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class Make {
    private int makeId;
    private int userId;
    private String userEmail;
    private LocalDate dateAdded;
    private String makeName;

    @Override
    public String toString() {
        return "Make{" + "makeId=" + makeId + ", userId=" + userId + ", userEmail=" + userEmail + ", dateAdded=" + dateAdded + ", makeName=" + makeName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.makeId;
        hash = 37 * hash + this.userId;
        hash = 37 * hash + Objects.hashCode(this.userEmail);
        hash = 37 * hash + Objects.hashCode(this.dateAdded);
        hash = 37 * hash + Objects.hashCode(this.makeName);
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
        final Make other = (Make) obj;
        if (this.makeId != other.makeId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        if (!Objects.equals(this.makeName, other.makeName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    

    
}