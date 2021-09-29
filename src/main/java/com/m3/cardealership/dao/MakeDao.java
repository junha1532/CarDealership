/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Make;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface MakeDao {
    
    Make getMakeById(int id);
//  List<Make> getMakeFromUserId(int userId);
    List<Make> getAllMakes();
    Make addMake(Make make);
    void updateMake(Make make);
    void deleteMakeById(int id);  
    Make getMakeFromMakeName(String makeName);
    
}
