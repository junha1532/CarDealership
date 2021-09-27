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

    public Make(int makeId, int userId, LocalDateTime dateAdded) {
        this.makeId = makeId;
        this.userId = userId;
        this.dateAdded = dateAdded;
    }

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
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.makeId;
        hash = 53 * hash + this.userId;
        hash = 53 * hash + Objects.hashCode(this.dateAdded);
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
        return true;
    }
    
}
