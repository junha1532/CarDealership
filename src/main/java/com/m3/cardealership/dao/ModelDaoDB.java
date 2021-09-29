/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author junha
 */
@Repository
public class ModelDaoDB implements ModelDao{
@Autowired
   JdbcTemplate jdbc;
    @Override
    public Model getModelById(int id) {
                try {
            final String SELECT_MODEL_BY_ID = "SELECT * FROM model WHERE modelId = ?";
            return jdbc.queryForObject(SELECT_MODEL_BY_ID, new ModelMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public Model getModelFromModelName(String modelName){
        try {
            final String SELECT_MODEL_BY_MODELNAME = "SELECT * FROM model WHERE modelName = ?";
            return jdbc.queryForObject(SELECT_MODEL_BY_MODELNAME, new ModelMapper(), modelName);
        } catch (DataAccessException ex) {
            return null;
        }
    }


    @Override
    public List<Model> getAllModels() {
        final String SELECT_ALL_MODELS = "SELECT * FROM model";
        return jdbc.query(SELECT_ALL_MODELS, new ModelMapper());
    }

    @Override
    public Model addModel(Model model) {
                final String INSERT_STUDENT = "INSERT INTO model(makeId, userId, modelName, dateAdded) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_STUDENT,
                model.getMakeId(),
                model.getUserId(),
                model.getModelName(),
                model.getDateAdded()
        );
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setModelId(newId);
        return model;
    }
    
           public static final class ModelMapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int index) throws SQLException {
            Model model = new Model();
            model.setModelId(rs.getInt("modelId"));
            model.setMakeId(rs.getInt("makeId"));
            model.setUserId(rs.getInt("userId"));
            model.setModelName(rs.getString("modelName"));
            model.setDateAdded(LocalDate.parse(rs.getString("dateAdded")));

            return model;
        }
    }
    
}
