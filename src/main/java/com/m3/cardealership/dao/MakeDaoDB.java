/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Make;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
public class MakeDaoDB implements MakeDao {
        
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Make getMakeById(int id) {
        try{
            final String SELECT_MAKE_BY_ID = "SELECT * FROM make where makeId = ?";
            Make make = jdbc.queryForObject(SELECT_MAKE_BY_ID,new MakeMapper(), id);
            return make;
        }catch (DataAccessException ex){
            return null;
        }    }

    @Override
    public List<Make> getAllMakes() {
        final String GET_ALL_MAKES = "SELECT * FROM make";
        return jdbc.query(GET_ALL_MAKES, new MakeMapper());
    }
   
    @Override
    public Make addMake(Make make) {
        final String INSERT_MAKE = "INSERT INTO make(userId,MakeName,dateAdded) VALUES(?,?,?)";
        jdbc.update(INSERT_MAKE,make.getUserId(),make.getMakeName(),make.getDateAdded());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setMakeId(newId);
        return make;
    }

    
    //Probably unused
    @Override
    public void updateMake(Make make) {
        final String UPDATE_MAKE = "UPDATE make SET MakeName = ?, userId = ?, dateAdded = ? WHERE Makeid = ?";
        jdbc.update(UPDATE_MAKE,make.getMakeName(),make.getUserId(),make.getDateAdded(),make.getMakeId());
    }

    @Override
    @Transactional
    public void deleteMakeById(int id) {
        //Delete Sales associated with makeId
        final String DELETE_SALES_MAKE = "DELETE s.* FROM sale s " + "JOIN vehicle v on s.vehicleId = v.vehicleId "+ "JOIN make m ON m.MakeId = v.MakeId " + "WHERE m.makeId = ?";
        jdbc.update(DELETE_SALES_MAKE,id);
        
        //Delete Vehicle containing Make
        final String DELETE_VEHICLE_MAKE = "DELETE v.* FROM vehicle v JOIN make m ON v.MakeId = m. WHERE c.teacherId = ?";
        jdbc.update(DELETE_VEHICLE_MAKE, id);
        
        final String DELETE_MAKE = "DELETE FROM make WHERE Makeid = ?";
        jdbc.update(DELETE_MAKE, id);
       
    }

    
    
    public static final class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int index) throws SQLException {
            Make make = new Make();
            make.setMakeId(rs.getInt("MakeId"));
            make.setUserId(rs.getInt("userId"));
            make.setDateAdded(LocalDate.parse(rs.getString("dateAdded")));
//            make.setDateAdded(rs.getString("dateAdded"));
            return make;
        }
    }
    
    //    @Override
//    public List<Make> getMakeFromUserId(int userId) {
//        final String SELECT_MAKES_FROM_COURSE = "SELECT s.* FROM student s "
//            + "JOIN course_student cs ON cs.studentId = s.id WHERE cs.courseId = ?";
//        return jdbc.query(SELECT_STUDENTS_FOR_COURSE, new MakeMapper(), userId);
//    }
}
