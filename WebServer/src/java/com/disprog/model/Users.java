/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ohanna
 */
public class Users extends DbConnection {

    //<editor-fold defaultstate="collapsed" desc="Data Member">
    private int iduser;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private String ktp;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Users() {
        getConnection();
    }

    public Users(int iduser, String name, String phoneNumber, String email, String password, String role, String ktp) {
        getConnection();
        this.iduser = iduser;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.ktp = ktp;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public String Login(String email, String password) {
        Users u = new Users();
        try {
            //set query
            String query = "SELECT `email`,`password` FROM `users` WHERE `email`=? AND `password`=?";

            //set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setString(1, email);
            sql.setString(2, password);

            result = sql.executeQuery();
            
            //nanti mau diatur lagi return nya seperti apa
            if (result.next()) {
                return "true";
            } else {
                return "false";
            }
        } catch (Exception ex) {
            System.out.println("Error Login" + ex);
        }
        return null;
    }
     
     public String Registration(String name, String phoneNumber, String email, String password, String ktp){
        getConnection();
        String message = "";
        try {
            // set query
            String query = "INSERT INTO users(`name`,`phoneNumber`,`email`,`password`,`ktp`) "
                    + "VALUES(?,?,?,?,?)";

            // read file
            File file = new File(ktp);
            FileInputStream inputFile = new FileInputStream(file);

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setString(1, name);
            sql.setString(2, phoneNumber);
            sql.setString(3, email);
            sql.setString(4, password);
            sql.setBinaryStream(5, inputFile);

            // store the resume file in database (to test if we can read the data)
            System.out.println("Reading file " + file.getAbsolutePath());

            result = sql.executeQuery();
            if (result.next()) {
                message = "true";
                return message;
            } else {
                message = "false";
            }
            connect.close();
            return message;
        } catch (SQLException | FileNotFoundException ex) {

            System.out.println("Error Registration" + ex);
        }
        return message;
     }
         
  
    //</editor-fold>
}
