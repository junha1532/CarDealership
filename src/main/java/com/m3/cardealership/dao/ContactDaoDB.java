/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
public class ContactDaoDB implements ContactDao{
        
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Contact getContactById(int id) { 
        try{
            final String SELECT_CONTACT_BY_ID = "SELECT * FROM contact where = ?";
            Contact contact = jdbc.queryForObject(SELECT_CONTACT_BY_ID,new ContactMapper(), id);
            return contact;
        }catch (DataAccessException ex){
            return null;
        }
    }

    

    @Override
    public List<Contact> getAllContacts() {
        final String GET_ALL_CONTACTS = "SELECT * FROM teacher";
        return jdbc.query(GET_ALL_CONTACTS, new ContactMapper());
    }

    //ADD VIN WHEN COME FROM CAR VIEW
    @Override
    public Contact addContact(Contact contact) {
        final String INSERT_CONTACT = "INSERT INTO contact(ContactName,ContactMessage,ContactEmail,ContactPhone) VALUES(?,?,?,?)";
        jdbc.update(INSERT_CONTACT,contact.getContactName(),contact.getMessage(),contact.getContactEmail(),contact.getContactPhone());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setContactId(newId);
        return contact;
    }

    @Override
    public void updateContact(Contact contact) {
        final String UPDATE_CONTACT = "UPDATE contact SET ContactName = ?, Message = ?, ContactEmail = ?, ContactPhone = ? WHERE id = ?";
        jdbc.update(UPDATE_CONTACT,
                contact.getContactName(),
                contact.getMessage(),
                contact.getContactEmail(),
                contact.getContactPhone(),
                contact.getContactId());
    }

    
    
    
    @Override
    @Transactional
    public void deleteContactById(int id) {
        final String DELETE_CONTACT = "DELETE FROM contact WHERE id = ?";
        jdbc.update(DELETE_CONTACT,id);
    }
    
    public static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int index) throws SQLException {
            Contact contact = new Contact();
            contact.setContactId(rs.getInt("ContactId"));
            contact.setContactName(rs.getString("ContactName"));
            contact.setMessage(rs.getString("Message"));
            contact.setContactEmail(rs.getString("ContactEmail"));
            contact.setContactPhone(rs.getString("rs.getString"));
            return contact;
        }
    }    

}
