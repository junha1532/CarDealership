/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Special;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author junha
 */
@Repository
public class SpeicalDaoDB implements SpecialDao{

       @Autowired
   JdbcTemplate jdbc;
    
    @Override
    public Special getSpecialByTitle(String title) {
                try {
            final String SELECT_SPECIAL_BY_TITLE = "SELECT * FROM special WHERE specialTitle = ?";
            return jdbc.queryForObject(SELECT_SPECIAL_BY_TITLE, new SpecialMapper(), title);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Special> getAllSpecials() {
        final String SELECT_ALL_SPECIALS = "SELECT * FROM special";
        return jdbc.query(SELECT_ALL_SPECIALS, new SpecialMapper());
    }


    @Override
    public Special addSpecial(Special special) {
                final String INSERT_SPECIAL = "INSERT INTO special(SpecialTitle, SpecialDescription, promotionAmount) "
                + "VALUES(?,?, ?)";
        jdbc.update(INSERT_SPECIAL,
                special.getSpecialTitle(),
                special.getSpecialDescription(),
                special.getPromotionAmount()
        );
        
//        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
//        student.setId(newId);
        return special;
    }

    @Override
    public void deleteSpecialByTitle(String title) {
        final String DELETE_SPECIAL = "DELETE FROM special WHERE specialTitle = ?";
        jdbc.update(DELETE_SPECIAL, title);
    }
    
    public static final class SpecialMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet rs, int index) throws SQLException {
            Special special = new Special();
            special.setSpecialTitle(rs.getString("SpecialTitle"));
            special.setSpecialDescription(rs.getString("SpecialDescription"));
            special.setPromotionAmount(rs.getInt("promotionAmount"));

            return special;
        }
    }
    
}
