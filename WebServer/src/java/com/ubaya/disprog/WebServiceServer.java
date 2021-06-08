/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubaya.disprog;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ohanna
 */
@WebService(serviceName = "WebServiceServer")
public class WebServiceServer extends DbConnection {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "login")
    public String Login(@WebParam(name = "email") String email,
            @WebParam(name = "password") String password) {
        getConnection();
        String message = "";
        try {
            //set query
            String query = "SELECT `email`,`password` FROM `users` WHERE `email`=? AND `password`=?";

            //set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setString(1, email);
            sql.setString(2, password);

            result = sql.executeQuery();
            if (result.next()) {
                message = "true";
                return message;
            } else {
                message = "false";
            }
            connect.close();
            return message;
        } catch (Exception ex) {
            System.out.println("Error LogIn" + ex);
        }
        return message;
    }

    @WebMethod(operationName = "registration")
    public String Registration(@WebParam(name = "name") String name,
            @WebParam(name = "phoneNumber") String phoneNumber,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "ktp") String ktp) {
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

    //METHOD FOR RESERVATION
    @WebMethod(operationName = "Insert Reservation")
    public String InsertReservation(@WebParam(name = "checkin_date") Date checkIn,
            @WebParam(name = "checkout_date") Date checkout,
            @WebParam(name = "total_guest") Integer total_guest,
            @WebParam(name = "notes") String notes,
            @WebParam(name = "iduser") Integer iduser,
            @WebParam(name = "idvilla") Integer idvilla) {

        getConnection();
        String message = "";
        try {
            // set query
            String query = "INSERT INTO reservations(`checkin_date`,`checkout_date`,`total_guest`,`notes`,`iduser`,`idvilla`) "
                    + "VALUES(?,?,?,?,?,?)";

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setDate(1, checkIn);
            sql.setDate(2, checkout);
            sql.setInt(3, total_guest);
            sql.setString(4, notes);
            sql.setInt(5, iduser);
            sql.setInt(6, idvilla);

            result = sql.executeQuery();
            if (result.next()) {
                message = "true";
                return message;
            } else {
                message = "false";
            }
            connect.close();
            return message;
        } catch (SQLException ex) {
            System.out.println("Error Input Reservation: " + ex);
        }
        return message;
    }

    //untuk upload bukti_pembayaran pada reservaasi
    @WebMethod(operationName = "Check Reservasi")
    public String UploadPayment(@WebParam(name = "bukti_pembayaran") String bukti_pembayaran,
            @WebParam(name = "idreservation") Integer idreservation) {

        getConnection();
        String message = "";
        try {
            // set query
            String query = "UPDATE reservations bukti_pembayaran SET =? WHERE idreservation=?";

            // read file
            File file = new File(bukti_pembayaran);
            FileInputStream inputFile = new FileInputStream(file);

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setBinaryStream(1, inputFile);
            sql.setInt(2, idreservation);

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
            System.out.println("Error Upload Payment: " + ex);
        }
        return message;
    }
    
    //chats 
    @WebMethod(operationName = "Insert Chat")
    public String InsertChat(@WebParam(name = "messages") String messages,
            @WebParam(name = "idsender") Integer idsender,
            @WebParam(name = "idreceiver") Integer idreceiver) {

        getConnection();
        String message = "";
        try {
            // set query
            String query = "INSERT INTO chats(`messages`,idsender`,`idreceiver`) "
                    + "VALUES(?,?,?)";

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setString(1, messages);
            sql.setInt(2, idsender);
            sql.setInt(3, idreceiver);

            result = sql.executeQuery();
            if (result.next()) {
                message = "true";
                return message;
            } else {
                message = "false";
            }
            connect.close();
            return message;
        } catch (SQLException ex) {
            System.out.println("Error Insert Chat: " + ex);
        }
        return message;
    }
    
}
