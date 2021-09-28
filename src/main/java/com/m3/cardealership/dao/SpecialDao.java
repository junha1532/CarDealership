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
    Special getSpecialByTitle(String title);
    List<Special> getAllSpecials();
    Special addSpecial(Special special);
    void deleteSpecialByTitle(String title);
}
