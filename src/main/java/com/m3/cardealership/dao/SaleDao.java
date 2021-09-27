/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;


import com.m3.cardealership.entities.Sale;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface SaleDao {
    Sale getSaleById(int id);
    Sale getSaleByVehicleiId(int id);
    List<Sale> getAllSales();
    List<Sale> getUsedSales();
    List<Sale> getNewSales();
    Sale addSale(Sale contact);
    void updateSale(Sale teacher);
    void deleteSaleById(int id);
    
    
}
