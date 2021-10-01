/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Report;
import java.util.List;

/**
 *
 * @author junha
 */
public interface ReportDao {
    List<Report> getAllUserSales();
    List<Report> querySalesReport(String dateFrom, String dateUntil, String userName);
    
    List<Report> queryInventory(Boolean isNew);

}
