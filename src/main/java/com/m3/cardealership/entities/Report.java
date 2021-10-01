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
public class Report {
    private String user;
    private int totalSales;
    private int totalVehicles;
    private int year;
    private String makeName;
    private String modelName;
    private int count;
    private int stockValue;

    @Override
    public String toString() {
        return "Report{" + "user=" + user + ", totalSales=" + totalSales + ", totalVehicles=" + totalVehicles + ", year=" + year + ", makeName=" + makeName + ", modelName=" + modelName + ", count=" + count + ", stockValue=" + stockValue + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.user);
        hash = 37 * hash + this.totalSales;
        hash = 37 * hash + this.totalVehicles;
        hash = 37 * hash + this.year;
        hash = 37 * hash + Objects.hashCode(this.makeName);
        hash = 37 * hash + Objects.hashCode(this.modelName);
        hash = 37 * hash + this.count;
        hash = 37 * hash + this.stockValue;
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
        final Report other = (Report) obj;
        if (this.totalSales != other.totalSales) {
            return false;
        }
        if (this.totalVehicles != other.totalVehicles) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (this.stockValue != other.stockValue) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.makeName, other.makeName)) {
            return false;
        }
        if (!Objects.equals(this.modelName, other.modelName)) {
            return false;
        }
        return true;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(int totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStockValue() {
        return stockValue;
    }

    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }

   
}
