/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubaya.disprog;

import com.disprog.model.DbConnection;
import com.disprog.model.*;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ohanna
 */
@WebService(serviceName = "WebServiceServer")
public class WebServiceServer extends DbConnection {

    Users model_User;
    Villas model_Villa;
    Reservations model_Reservation;
    Chats model_Chat;
    ArrayList<Users> listOfUser = new ArrayList<Users>();

    public WebServiceServer(){
        listOfUser = model_User.displayAllClient();
    }
    
    @WebMethod(operationName = "login")
    public String Login(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password) {
        try {
           String result = model_User.Login(email,password);
           return result;
        } catch (Exception ex) {
            System.out.println("Error LogIn" + ex);
        }
        return null;
    }
    
     public String displayAllClient(
            ) {
        try {
           
        } catch (Exception ex) {
            System.out.println("Error LogIn" + ex);
        }
        return null;
    }

    @WebMethod(operationName = "registration")
    public String Registration(@WebParam(name = "name") String name,
            @WebParam(name = "phoneNumber") String phoneNumber,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "ktp") String ktp) {

        String result = model_User.Registration(name, phoneNumber, email, password, ktp);
        return result;
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
        String result = model_Reservation.InsertReservation(checkIn, checkout, total_guest, notes, iduser, idvilla);
        return result;
    }

    //untuk upload bukti_pembayaran pada reservaasi
    @WebMethod(operationName = "Upload Bukti Pembayaran")
    public String UploadPayment(@WebParam(name = "bukti_pembayaran") String bukti_pembayaran,
            @WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.uploadPayment(bukti_pembayaran,idreservation);
        return result;
    }
    
    //chats 
    @WebMethod(operationName = "Insert Chat")
    public String InsertChat(
            @WebParam(name = "messages") String messages,
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
    
    //display chat
    
    @WebMethod(operationName = "Display Chat")
    public String InsertChat(
            @WebParam(name = "idsender") Integer idsender,
            @WebParam(name = "idreceiver") Integer idreceiver) {

        getConnection();
        String message = "";
        try {
            // set query
            String query = "SELECT * FROM chats WHERE (idsender=? and idreceiver=?) "
                    + "or (idsender=? and idreceiver=?) ORDER BY cht_timestamp;";

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setInt(1, idsender);
            sql.setInt(2, idreceiver);

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
