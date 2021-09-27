/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Sale;
import com.m3.cardealership.entities.Special;
import java.util.List;

/**
 *
 * @author junha
 */
public interface SpecialDao {
    Special getSpecialById(int id);
    List<Special> getAllSpecials();
    Special addSpecial(Special special);
    void updateSpecial(Special special);
    void deleteSpecialById(int id);
    
    List<Special> getSalesForSale(Sale sale);
}
