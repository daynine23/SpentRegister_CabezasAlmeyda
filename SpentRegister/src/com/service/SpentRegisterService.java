/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service;

import com.model.Spent;
import com.repository.SpentRegisterRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author C2A601-08
 */
public class SpentRegisterService {
    
    private final SpentRegisterRepository repository;

    public SpentRegisterService(SpentRegisterRepository repository){
        this.repository = repository;
    }
    
    

    // Registrar un nuevo libro
    public void addSpent(Spent spent) throws SQLException {
        try {
            repository.save(spent);
            System.out.println("Book saved successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating book: " + e.getMessage());
        }
    }

    public void updateSpent(Spent updatedSpent) {
        repository.update(updatedSpent);
    }

    public List<Spent> getAllSpents() {
        return repository.findAll();
    }

    public void deleteSpent(int id) {
        //repository.delete(id);
    }

    public Spent getSpentById(int id) {
        //return repository.getSpentById(id); // Consulta al repositorio
        return null;
    }
    
}
