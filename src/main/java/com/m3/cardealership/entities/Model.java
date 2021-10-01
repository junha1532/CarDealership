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
 * @author junha
 */
public class Model {
    private int modelId;
    private int makeId;
    private String makeName;
    private int userId;
    private String userEmail;
    private String modelName;
    private LocalDate dateAdded;

    @Override
    public String toString() {
        return "Model{" + "modelId=" + modelId + ", makeId=" + makeId + ", makeName=" + makeName + ", userId=" + userId + ", userEmail=" + userEmail + ", modelName=" + modelName + ", dateAdded=" + dateAdded + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.modelId;
        hash = 97 * hash + this.makeId;
        hash = 97 * hash + Objects.hashCode(this.makeName);
        hash = 97 * hash + this.userId;
        hash = 97 * hash + Objects.hashCode(this.userEmail);
        hash = 97 * hash + Objects.hashCode(this.modelName);
        hash = 97 * hash + Objects.hashCode(this.dateAdded);
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
        final Model other = (Model) obj;
        if (this.modelId != other.modelId) {
            return false;
        }
        if (this.makeId != other.makeId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.makeName, other.makeName)) {
            return false;
        }
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        if (!Objects.equals(this.modelName, other.modelName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    
    
}
