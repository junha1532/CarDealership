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
public class Vehicle {
    private int userId;
    private int vehicleId;
    private String VIN;
    private int year;
    private Make make;
    private Model model;
    private String color;
    private String interior;
    private String bodyStyle;
    private String transmission;
    private int mileage;
    private int salePrice;
    private int MSRP;
    private boolean featured;
    private LocalDate dateAdded;
    private String DESCRIPTION;

    @Override
    public String toString() {
        return "Vehicle{" + "userId=" + userId + ", vehicleId=" + vehicleId + ", VIN=" + VIN + ", year=" + year + ", make=" + make + ", model=" + model + ", color=" + color + ", interior=" + interior + ", bodyStyle=" + bodyStyle + ", transmission=" + transmission + ", mileage=" + mileage + ", salePrice=" + salePrice + ", MSRP=" + MSRP + ", featured=" + featured + ", dateAdded=" + dateAdded + ", DESCRIPTION=" + DESCRIPTION + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.userId;
        hash = 47 * hash + this.vehicleId;
        hash = 47 * hash + Objects.hashCode(this.VIN);
        hash = 47 * hash + this.year;
        hash = 47 * hash + Objects.hashCode(this.make);
        hash = 47 * hash + Objects.hashCode(this.model);
        hash = 47 * hash + Objects.hashCode(this.color);
        hash = 47 * hash + Objects.hashCode(this.interior);
        hash = 47 * hash + Objects.hashCode(this.bodyStyle);
        hash = 47 * hash + Objects.hashCode(this.transmission);
        hash = 47 * hash + this.mileage;
        hash = 47 * hash + this.salePrice;
        hash = 47 * hash + this.MSRP;
        hash = 47 * hash + (this.featured ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.dateAdded);
        hash = 47 * hash + Objects.hashCode(this.DESCRIPTION);
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
        final Vehicle other = (Vehicle) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (this.salePrice != other.salePrice) {
            return false;
        }
        if (this.MSRP != other.MSRP) {
            return false;
        }
        if (this.featured != other.featured) {
            return false;
        }
        if (!Objects.equals(this.VIN, other.VIN)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.interior, other.interior)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.transmission, other.transmission)) {
            return false;
        }
        if (!Objects.equals(this.DESCRIPTION, other.DESCRIPTION)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
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

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getMSRP() {
        return MSRP;
    }

    public void setMSRP(int MSRP) {
        this.MSRP = MSRP;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    


}
