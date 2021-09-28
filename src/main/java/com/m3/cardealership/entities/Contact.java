/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.entities;

/**
 *
 * @author Daniel
 */

import java.util.Objects;

public class Contact{
    
    private int contactId;
    private String contactName;
    private String message;
    private String contactEmail;
    private String contactPhone;

    @Override
    public String toString() {
        return "Contact{" + "contactId=" + contactId + ", contactName=" + contactName + ", message=" + message + ", contactEmail=" + contactEmail + ", contactPhone=" + contactPhone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.contactId;
        hash = 89 * hash + Objects.hashCode(this.contactName);
        hash = 89 * hash + Objects.hashCode(this.message);
        hash = 89 * hash + Objects.hashCode(this.contactEmail);
        hash = 89 * hash + Objects.hashCode(this.contactPhone);
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
        final Contact other = (Contact) obj;
        if (this.contactId != other.contactId) {
            return false;
        }
        if (!Objects.equals(this.contactName, other.contactName)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.contactEmail, other.contactEmail)) {
            return false;
        }
        if (!Objects.equals(this.contactPhone, other.contactPhone)) {
            return false;
        }
        return true;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    


}