/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Model;
import java.util.List;

/**
 *
 * @author junha
 */
public interface ModelDao {
    Model getModelById(int id);
    List<Model> getAllModels();
    Model addModel(Model model);
    List<Model> getModelFromMakeName(String makeName);
    Model getModelFromModelName(String modelName);
    
//    List<Model> getCoursesForTeacher(Teacher teacher);
//    List<Model> getCoursesForStudent(Student student);
}
