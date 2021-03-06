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
public class Sale {
    private int saleId;
    private String specialTitle;
    private int salespersonId;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerAddress2;
    private String customerCity;
    private String customerZipCode;
    private int purchasePrice;
    private String purchaseType;
    private LocalDate purchaseDate;

    @Override
    public String toString() {
        return "Sale{" + "saleId=" + saleId + ", specialTitle=" + specialTitle + ", salespersonId=" + salespersonId + ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress + ", customerAddress2=" + customerAddress2 + ", customerCity=" + customerCity + ", customerZipCode=" + customerZipCode + ", purchasePrice=" + purchasePrice + ", purchaseType=" + purchaseType + ", purchaseDate=" + purchaseDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.saleId;
        hash = 43 * hash + Objects.hashCode(this.specialTitle);
        hash = 43 * hash + this.salespersonId;
        hash = 43 * hash + Objects.hashCode(this.customerName);
        hash = 43 * hash + Objects.hashCode(this.customerEmail);
        hash = 43 * hash + Objects.hashCode(this.customerAddress);
        hash = 43 * hash + Objects.hashCode(this.customerAddress2);
        hash = 43 * hash + Objects.hashCode(this.customerCity);
        hash = 43 * hash + Objects.hashCode(this.customerZipCode);
        hash = 43 * hash + this.purchasePrice;
        hash = 43 * hash + Objects.hashCode(this.purchaseType);
        hash = 43 * hash + Objects.hashCode(this.purchaseDate);
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
        final Sale other = (Sale) obj;
        if (this.saleId != other.saleId) {
            return false;
        }
        if (this.salespersonId != other.salespersonId) {
            return false;
        }
        if (this.purchasePrice != other.purchasePrice) {
            return false;
        }
        if (!Objects.equals(this.specialTitle, other.specialTitle)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.customerEmail, other.customerEmail)) {
            return false;
        }
        if (!Objects.equals(this.customerAddress, other.customerAddress)) {
            return false;
        }
        if (!Objects.equals(this.customerAddress2, other.customerAddress2)) {
            return false;
        }
        if (!Objects.equals(this.customerCity, other.customerCity)) {
            return false;
        }
        if (!Objects.equals(this.customerZipCode, other.customerZipCode)) {
            return false;
        }
        if (!Objects.equals(this.purchaseType, other.purchaseType)) {
            return false;
        }
        if (!Objects.equals(this.purchaseDate, other.purchaseDate)) {
            return false;
        }
        return true;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSpecialTitle() {
        return specialTitle;
    }

    public void setSpecialTitle(String specialTitle) {
        this.specialTitle = specialTitle;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress2() {
        return customerAddress2;
    }

    public void setCustomerAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerZipCode() {
        return customerZipCode;
    }

    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    
    
}
