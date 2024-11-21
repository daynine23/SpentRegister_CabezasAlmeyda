/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author C2A601-08
 */
public class Spent {
    
    private int id;
    private String description;
    private String category;
    private double amount;
    private String spentDate;
    
    
    // Constructor privado para el patrón Builder
    private Spent(SpentBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.category = builder.category;
        this.amount = builder.amount;
        this.spentDate = builder.spentDate;
    }

    public Spent() {
    }

    public Spent(int id, String description, String category, double amount, String spentDate) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.spentDate = spentDate;
    }
    
    
    // Builder Class
    public static class SpentBuilder {
        private int id;
        private String description;
        private String category;
        private double amount;
        private String spentDate;

        public SpentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SpentBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public SpentBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public SpentBuilder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public SpentBuilder setSpentDate(String spentDate) {
            this.spentDate = spentDate;
            return this;
        }

        public Spent build() {
            if (description == null || category == null) {
                throw new IllegalArgumentException("Title and Author are required.");
            }
            return new Spent(this);
        }
    }
    

    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }


    public double getAmount() {
        return amount;
    }


    public String getSpentDate() {
        return spentDate;
    }

    
    
    
    
}
