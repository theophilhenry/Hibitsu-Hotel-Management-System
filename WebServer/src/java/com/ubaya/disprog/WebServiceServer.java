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

    Users model_User = new Users();
    Villas model_Villa = new Villas();
    Reservations model_Reservation = new Reservations();
    Chats model_Chat = new Chats();
//    ArrayList<Users> listOfUser = new ArrayList<Users>();

    @WebMethod(operationName = "login")
    public String Login(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password) {
        try {
            String result = model_User.Login(email, password);
            if(!result.contains("true;;"))
            {
                result = "Sorry Invalid username or password";
            }
            return result;
        } catch (Exception ex) {
            System.out.println("Error LogIn: " + ex);
        }
        return null;
    }

    @WebMethod(operationName = "registration")
    public String Registration(@WebParam(name = "fullname") String fullname,
            @WebParam(name = "display_name") String display_name,
            @WebParam(name = "phoneNumber") String phoneNumber,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "ktp") String ktp) {

        String result = model_User.Registration(fullname, display_name, phoneNumber, email, password, ktp);
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
    @WebMethod(operationName = "Upload Payment")
    public String UploadPayment(@WebParam(name = "bukti_pembayaran") String bukti_pembayaran,
            @WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.UploadPayment(bukti_pembayaran, idreservation);
        return result;
    }

    @WebMethod(operationName = "Change Status")
    public String ChangeStatus(@WebParam(name = "status") String status,
            @WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.ChangeStatus(status, idreservation);
        return result;
    }
    
    @WebMethod(operationName = "Track Order")
    public String TrackOrder(@WebParam(name = "idreservation") Integer idreservation){
        //belum di code
        return null;
    }

    @WebMethod(operationName = "DisplayReservation")
    public String DisplayReservation(@WebParam(name = "kriteria") String kriteria,
            @WebParam(name = "dicari") String dicari){
        //belum di code
        return null;
    }
    
    //CHECK AVAILABILITY untuk cek apakah pada tanggal yg ditentukan ada yg sudah book
    @WebMethod(operationName = "Check Availability")
    public String CheckAvailability(@WebParam(name = "idvilla") Integer idvilla,
            @WebParam(name = "checkin") Date checkin,
            @WebParam(name = "checkout") Date checkout) {
        String result = model_Reservation.CheckAvailability(idvilla, checkin, checkout);
        return result;
    }

    //chats 
    @WebMethod(operationName = "Insert Chat")
    public String InsertChat(
            @WebParam(name = "messages") String messages,
            @WebParam(name = "idsender") Integer idsender,
            @WebParam(name = "idreceiver") Integer idreceiver) {
        try {
            String result = model_Chat.InsertChat(idsender, idreceiver, messages);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Insert Chat: " + ex);
        }
        return null;
    }

    //display chat
    @WebMethod(operationName = "Display Contacts")
    public String DisplayContacts(
            @WebParam(name = "iduser") Integer iduser) {
        try {
            String result = model_Chat.DisplayContacts(iduser);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Display Chat: " + ex);
        }
        return null;
    }
}
