/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class Make {
    private int makeId;
    private int userId;
    private LocalDateTime dateAdded;
    private String makeName;

//    public Make(int makeId, int userId, LocalDateTime dateAdded) {
//        this.makeId = makeId;
//        this.userId = userId;
//        this.dateAdded = dateAdded;
//    }

    public int getMakeId() {
        return makeId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public void setDateAdded(String dateAdded) {
        this.dateAdded = LocalDateTime.parse(dateAdded);
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.makeId;
        hash = 89 * hash + this.userId;
        hash = 89 * hash + Objects.hashCode(this.dateAdded);
        hash = 89 * hash + Objects.hashCode(this.makeName);
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
        if (!Objects.equals(this.makeName, other.makeName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }
    
    
}