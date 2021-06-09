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
    private String fullname;
    private String display_name;
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

    public Users(int iduser, String fullname, String display_name, String phoneNumber, String email, String password, String role, String ktp) {
        getConnection();
        this.iduser = iduser;
        this.fullname = fullname;
        this.display_name = display_name;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
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
    public String LoginClient(String email, String password) {
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT `iduser`,`fullname`,`display_name`,`email`"
                        + "FROM `users` WHERE `email`= ? AND `password`= ? AND `role`='CLIENT'";

                //set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setString(1, email);
                sql.setString(2, password);

                result = sql.executeQuery();

                //nanti mau diatur lagi return nya seperti apa
                if (result.next()) {
                    String ket = "[1]hasilLoginClient,[2]iduser,[3]fullname,[4]display_name,[5]email,[6]role;;";
                    String hasil = result.getInt("iduser") + ";;"
                            + result.getString("fullname") + ";;"
                            + result.getString("display_name") + ";;"
                            + result.getString("email") + ";;";

                    return ket + "true;;" + hasil;
                } else {
                    String ket = "[1]hasilLoginClient";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error Login" + ex);
        }
        return null;
    }

    public String LoginAdmin(String email, String password) {
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT * "
                        + "FROM `users` WHERE `email`= ? AND `password`= ? AND `role`='ADMIN'";

                //set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setString(1, email);
                sql.setString(2, password);

                result = sql.executeQuery();

                //nanti mau diatur lagi return nya seperti apa
                String ket = "[1]hasilLoginAdmin;;";
                if (result.next()) {
                    return ket + "true;;";
                } else {
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error Login" + ex);
        }
        return null;
    }

    public String Registration(String fullname, String display_name, String phoneNumber, String email, String password, String ktp) {
        try {
            // set query
            String query = "INSERT INTO users(`fullname`,`display_name`,`phoneNumber`,`email`,`password`,`ktp`) "
                    + "VALUES(?,?,?,?,?,?)";

            // read file
            File file = new File(ktp);
            FileInputStream inputFile = new FileInputStream(file);

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setString(1, fullname);
            sql.setString(2, display_name);
            sql.setString(3, phoneNumber);
            sql.setString(4, email);
            sql.setString(5, password);
            sql.setBinaryStream(6, inputFile);

            // store the resume file in database (to test if we can read the data)
            System.out.println("Reading file " + file.getAbsolutePath());

            result = sql.executeQuery();
            String ket = "[1]hasilRegis;;";
            if (result.next()) {
                return ket + "true";
            } else {
                return ket + "false";
            }

        } catch (SQLException | FileNotFoundException ex) {

            System.out.println("Error Registration: " + ex);
        }
        return null;
    }

    //</editor-fold>
}
