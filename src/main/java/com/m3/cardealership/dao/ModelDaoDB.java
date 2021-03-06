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
public class ModelDaoDB implements ModelDao {

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
    public List<Model> getModelFromMakeName(String makeName) {
        final String SELECT_MODEL_BY_MAKENAME = "SELECT * FROM model mo LEFT JOIN make ma ON mo.MakeId = ma.MakeId WHERE MakeName = \"" + makeName + "\"";
        return jdbc.query(SELECT_MODEL_BY_MAKENAME, new ModelMapper());
    }

    @Override
    public Model getModelFromModelName(String modelName) {
        try {
            final String SELECT_MODEL_BY_MODELNAME = "SELECT * FROM model WHERE modelName = ?";
            return jdbc.queryForObject(SELECT_MODEL_BY_MODELNAME, new ModelMapper(), modelName);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Model> getAllModels() {
        final String SELECT_ALL_MODELS = "SELECT * FROM model m "
                + "LEFT JOIN user u ON m.userId = u.userId "
                + "LEFT JOIN make ma ON m.makeId = ma.makeID";
        return jdbc.query(SELECT_ALL_MODELS, new ModelMapper());
    }

    @Override
    public Model addModel(Model model) {
        final String INSERT_STUDENT = "INSERT INTO model(makeId, userId, modelName, dateAdded) "
                + "VALUES(?,?,?,?)";
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
            if(isThere(rs, "userEmail")){
                model.setUserEmail(rs.getString("userEmail"));
            }
            if(isThere(rs, "MakeName")){
                model.setMakeName(rs.getString("MakeName"));
            }

            return model;
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
