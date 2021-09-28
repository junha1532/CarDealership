/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Sale;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Daniel
 */
public class SaleDaoDB implements SaleDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Sale getSaleById(int id) {
        try{
            final String SELECT_SALE_BY_ID = "SELECT * FROM sale WHERE saleId = ?";
            Sale sale = jdbc.queryForObject(SELECT_SALE_BY_ID,new SaleMapper(), id);
            return sale;
        }catch (DataAccessException ex){
            return null;
        }    
    }

    @Override
    public Sale getSaleByVehicleiId(int id) {
        try{
            final String SELECT_SALE_BY_ID = "SELECT * FROM sale WHERE vehicleId = ?";
            Sale sale = jdbc.queryForObject(SELECT_SALE_BY_ID,new SaleMapper(), id);
            return sale;
        }catch (DataAccessException ex){
            return null;
        }    
    }    

    @Override
    public List<Sale> getAllSales() {
        final String GET_ALL_SALES = "SELECT * FROM sale";
        return jdbc.query(GET_ALL_SALES, new SaleMapper());
    }

    @Override
    public List<Sale> getUsedSales() {
        final String GET_USED_SALES = "SELECT s.* FROM sale s JOIN vehicle v on s.vehicleId = v.vehicleId "
                + " WHERE v.mileage > 0";
        return jdbc.query(GET_USED_SALES, new SaleMapper());
    }
    

    @Override
    public List<Sale> getNewSales() {
        final String GET_NEW_SALES = "SELECT s.* FROM sale s JOIN vehicle v on s.vehicleId = v.vehicleId "
                + " WHERE v.mileage = 0";
        return jdbc.query(GET_NEW_SALES, new SaleMapper());    
    }

    @Override
    public Sale addSale(Sale sale) {
        final String INSERT_SALE = "INSERT INTO sale(SpecialTitle,vehicleId,salesPersonId,customerName,customerEmail,"
                + "customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_SALE, 
                sale.getSpecialTitle(),
//                sale.getVehicleId(),
                sale.getSalespersonId(),
                sale.getCustomerName(),
                sale.getCustomerEmail(),
                sale.getCustomerAddress(),
                sale.getCustomerAddress2(),
                sale.getCustomerCity(),
                sale.getCustomerZipCode(),
                sale.getPurchasePrice(),
                sale.getPurchaseType()
                );
       int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
       sale.setSaleId(newId);
       return sale;
        
    }

    @Override
    public void updateSale(Sale sale) {
        final String UPDATE_TEACHER = "UPDATE teacher SET SpecialTitle = ?, vehicleId = ?, " +
                "salesPersonId = ?, customerName = ?, customerEmail = ?, customerAddress = ?, customerAddress2 = ?, "
                + "customerCity = ?, customerZipCode = ?, purchasePrice = ?, purchaseType = ? WHERE id = ?";
        jdbc.update(UPDATE_TEACHER,                
                sale.getSpecialTitle(),
//                sale.getVehicleId(),
                sale.getSalespersonId(),
                sale.getCustomerName(),
                sale.getCustomerEmail(),
                sale.getCustomerAddress(),
                sale.getCustomerAddress2(),
                sale.getCustomerCity(),
                sale.getCustomerZipCode(),
                sale.getPurchasePrice(),
                sale.getPurchaseType(),
                sale.getSaleId()
                );
    }

    @Override
    public void deleteSaleById(int id) {
       final String DELETE_VEHICLE_SALE = "DELETE v.* FROM vehicle v JOIN sale s on s.vehicleId = v.vehicleId WHERE s.SaleID = ?";
       jdbc.update(DELETE_VEHICLE_SALE,id);
       
      final String DELETE_SALE = "DELETE * FROM sale where saleId = ?";
      jdbc.update(DELETE_SALE,id);
       
    }
    
    
    
    public static final class SaleMapper implements RowMapper<Sale> {
        @Override
        public Sale mapRow(ResultSet rs, int index) throws SQLException {
            Sale sale = new Sale();
            sale.setSaleId(rs.getInt("SaleId"));
            sale.setSpecialTitle(rs.getString("SpecialTitle"));
//            sale.setVehicleId(rs.getInt("vehicleId"));
            sale.setSalespersonId(rs.getInt("salespersonId"));
            sale.setCustomerName(rs.getString("customerName"));
            sale.setCustomerEmail(rs.getString("customerEmail"));
            sale.setCustomerAddress(rs.getString("customerAddress"));
            sale.setCustomerAddress2(rs.getString("customerAddress2"));
            sale.setCustomerCity(rs.getString("customerCity"));
            sale.setPurchasePrice(rs.getInt("purchasePrice"));
            sale.setPurchaseType(rs.getString("purchaseType"));
            return sale;
        }
    }
    
}
