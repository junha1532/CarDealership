/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Report;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junha
 */
@Repository
public class ReportDaoDB implements ReportDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Report> getAllUserSales() {
       final String GET_ALL_USER_SALES = 
               "SELECT concat(u.userFirstName,\" \", u.userLastName) user, sum(s.purchasePrice) totalSales, count(*) totalVehicles "
               + "FROM sale s "
               + "JOIN user u ON s.salesPersonId = u.userId GROUP BY u.userId";
        return jdbc.query(GET_ALL_USER_SALES, new ReportMapper());
    }


    @Override
    public List<Report> querySalesReport(String dateFrom, String dateUntil, String userName) {
        String havingString = "";
        if(!userName.equals("all")){
            havingString = "HAVING user = \"" + userName + "\"";
        }
        final String GET_SALES_REPORT_QUERY = 
                String.format("SELECT concat(u.userFirstName,\" \", u.userLastName) user, sum(s.purchasePrice) totalSales, count(*) totalVehicles "
                    + "FROM  (SELECT * FROM sale  WHERE purchaseDate BETWEEN \"%s\" AND \"%s\") as s "
                    + "JOIN user u ON s.salesPersonId = u.userId "
                    + "GROUP BY u.userId %s", dateFrom, dateUntil, havingString);
        return jdbc.query(GET_SALES_REPORT_QUERY, new ReportMapper());
    }

    @Override
    public List<Report> queryInventory(Boolean isNew) {
        String compareSymbol = ">";
        if(isNew){
            compareSymbol = "=";
        }
        final String GET_INVENTORY_REPORT_QUERY = 
                String.format("SELECT v.year, ma.makeName, mo.modelName, COUNT(*) count, SUM(v.salePrice) stockValue "
                    + "FROM (SELECT * FROM vehicle WHERE mileage %s 0) as v " 
                    + "LEFT JOIN model mo ON mo.modelId = v.modelId "
                    + "LEFT JOIN make ma ON ma.makeId = v.makeId "
                    + "GROUP BY v.year, ma.makeName,  mo.modelName", compareSymbol);
    return jdbc.query(GET_INVENTORY_REPORT_QUERY, new ReportMapper());
    }
    
    public static final class ReportMapper implements RowMapper<Report> {

        @Override
        public Report mapRow(ResultSet rs, int index) throws SQLException {
            Report report = new Report();
            if(isThere(rs, "user")){
                report.setUser(rs.getString("user"));
            }
            if(isThere(rs, "totalSales")){
                report.setTotalSales(rs.getInt("totalSales"));
            }
            if(isThere(rs, "totalVehicles")){
                report.setTotalVehicles(rs.getInt("totalVehicles"));
            }
            if(isThere(rs, "year")){
                report.setYear(rs.getInt("year"));
            }
            if(isThere(rs, "makeName")){
                report.setMakeName(rs.getString("makeName"));
            }
            if(isThere(rs, "modelName")){
                report.setModelName(rs.getString("modelName"));
            }
            if(isThere(rs, "count")){
                report.setCount(rs.getInt("count"));
            }
            if(isThere(rs, "stockValue")){
                report.setStockValue(rs.getInt("stockValue"));
            }
            return report;
        }
    }
    
    private static boolean isThere(ResultSet rs, String column) {
        try {
            rs.findColumn(column);
            return true;
        } catch (SQLException sqlex) {
            //DO NOTHING
        }

        return false;
    }
    
}
