/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

/**
 *
 * @author ohanna
 */
public class Villas extends DbConnection {

    

    //<editor-fold defaultstate="collapsed" desc="Data Member">
    private int idvilla;
    private String name;
    private String address;
    private int total_bedroom;
    private int total_bathroom;
    private String unit_size;
    private String fasilities;
    private String photo;
    private String description;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Villas() {
        getConnection();
    }

    public Villas(int idvilla, String name, String address, int total_bedroom, int total_bathroom, String unit_size, String fasilities, String photo, String description) {
        getConnection();
        this.idvilla = idvilla;
        this.name = name;
        this.address = address;
        this.total_bedroom = total_bedroom;
        this.total_bathroom = total_bathroom;
        this.unit_size = unit_size;
        this.fasilities = fasilities;
        this.photo = photo;
        this.description = description;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getIdvilla() {
        return idvilla;
    }

    public void setIdvilla(int idvilla) {
        this.idvilla = idvilla;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal_bedroom() {
        return total_bedroom;
    }

    public void setTotal_bedroom(int total_bedroom) {
        this.total_bedroom = total_bedroom;
    }

    public int getTotal_bathroom() {
        return total_bathroom;
    }

    public void setTotal_bathroom(int total_bathroom) {
        this.total_bathroom = total_bathroom;
    }

    public String getUnit_size() {
        return unit_size;
    }

    public void setUnit_size(String unit_size) {
        this.unit_size = unit_size;
    }

    public String getFasilities() {
        return fasilities;
    }

    public void setFasilities(String fasilities) {
        this.fasilities = fasilities;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    //</editor-fold>
}
