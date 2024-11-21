/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.model.Spent;
import com.repository.SpentRegisterRepository;
import com.service.SpentRegisterService;
import com.view.SpentRegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author C2A601-08
 */
public class SpentRegisterController implements ActionListener{
    
    private SpentRegisterView view;
    private SpentRegisterService service;
    
    public SpentRegisterController(SpentRegisterView view) {
        this.view = view;
        SpentRegisterRepository repo = new SpentRegisterRepository();
        service = new SpentRegisterService(repo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnSaveBook()) {  
            try {
            // Usamos el getter para acceder al botón
            guardarGasto();
            } catch (SQLException ex) {
                Logger.getLogger(SpentRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void guardarGasto() throws SQLException {
        String description = view.getDescription();
        String category = view.getCategory();
        String amount = view.getAmount();
        String spendDate = view.getSpendDate();
        if (description.isEmpty() || category.isEmpty() || amount.isEmpty() || spendDate.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Porfavor completar los campos");
            return;
        }else{
        Spent s = new Spent.SpentBuilder()
                .setDescription(description)
                .setCategory(category)
                .setAmount(Double.valueOf(amount))
                .setSpentDate(spendDate)
                .build();
        service.addSpent(s);
        }
    }
    
}
