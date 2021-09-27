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
public class Special {
    private String specialTitle;
    private String specialDescription;
    private int promotionAmount;

    @Override
    public String toString() {
        return "Special{" + "specialTitle=" + specialTitle + ", specialDescription=" + specialDescription + ", promotionAmount=" + promotionAmount + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.specialTitle);
        hash = 47 * hash + Objects.hashCode(this.specialDescription);
        hash = 47 * hash + this.promotionAmount;
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
        final Special other = (Special) obj;
        if (this.promotionAmount != other.promotionAmount) {
            return false;
        }
        if (!Objects.equals(this.specialTitle, other.specialTitle)) {
            return false;
        }
        if (!Objects.equals(this.specialDescription, other.specialDescription)) {
            return false;
        }
        return true;
    }

    public String getSpecialTitle() {
        return specialTitle;
    }

    public void setSpecialTitle(String specialTitle) {
        this.specialTitle = specialTitle;
    }

    public String getSpecialDescription() {
        return specialDescription;
    }

    public void setSpecialDescription(String specialDescription) {
        this.specialDescription = specialDescription;
    }

    public int getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(int promotionAmount) {
        this.promotionAmount = promotionAmount;
    }
    
    
}
