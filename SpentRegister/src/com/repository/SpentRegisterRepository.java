/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;
import java.sql.Connection;

import com.model.Spent;
import com.util.DataBaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author C2A601-08
 */
public class SpentRegisterRepository {

    public SpentRegisterRepository() {
    }
    
    
    
    
    public void save(Spent spent) throws SQLException {
        try (Connection conn = DataBaseConnectionManager.getConnection()){
            PreparedStatement pstmtid = conn.prepareStatement("SELECT MAX(id) AS ultimo_id FROM gastos");
            Integer ultimoID = null;
            ResultSet rs = pstmtid.executeQuery();
            
            if (rs.next()) {
                // Obtén el valor de la columna "ultimo_id"
                int id = rs.getInt("ultimo_id"); // Esto devolverá 0 si es NULL
                if (rs.wasNull()) {
                    ultimoID = null; // Establece null explícitamente si fue NULL en la BD
                } else {
                    ultimoID = id; // Si no fue NULL, usa el valor obtenido
                }
            }

            // Imprimir el resultado
            if (ultimoID == null) {
                ultimoID = 1;
            } else {
                ultimoID = rs.getInt("ultimo_id") + 1;;
            }
            
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO GASTOS (id,descripcion,categoria,monto,fecha_gasto) VALUES(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, ultimoID);
            pstmt.setString(2, spent.getDescription());
            pstmt.setString(3, spent.getCategory());
            pstmt.setDouble(4, spent.getAmount());
            pstmt.setString(5, spent.getSpentDate());
            //Executing the statement
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving SPENT", e);
        }
    }

    public List<Spent> findAll() {
        List<Spent> books = new ArrayList<>();
        try (Connection conn = DataBaseConnectionManager.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GASTOS");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                books.add(new Spent.SpentBuilder()
                        .setId(resultSet.getInt("id"))
                        .setCategory(resultSet.getString("descripcion"))
                        .setCategory(resultSet.getString("categoria"))
                        .setAmount(resultSet.getDouble("monto"))
                        .setSpentDate(resultSet.getString("fecha_gasto"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books", e);
        }
        return books;
    }

    public void update(Spent spent) {
        try (Connection conn = DataBaseConnectionManager.getConnection()
                
                ){
            String sql = "UPDATE GASTOS SET descripcion = ?, categoria = ?, monto = ?, fecha_gasto = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            PreparedStatement pstmt = conn.prepareStatement("");
            ResultSet resultSet = pstmt.executeQuery();


            statement.setString(1, spent.getDescription());
            statement.setString(2, spent.getCategory());
            statement.setDouble(3, spent.getAmount());
            statement.setString(4, spent.getSpentDate());
            statement.setInt(5, spent.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book", e);
        }
    }

    /*public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
    }*/
    
    /*public Spent getSpentById(int id){
        String sql = "SELECT * FROM gastos WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
}*/
    
}
