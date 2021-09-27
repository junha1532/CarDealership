/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Contact;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ContactDao {
    Contact getContactById(int id);
    List<Contact> getAllContacts();
    Contact addContact(Contact contact);
    void updateContact(Contact teacher);
    void deleteContactById(int id);  
}
