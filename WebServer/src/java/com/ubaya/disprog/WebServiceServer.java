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
import java.sql.Blob;

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

    //---------------------------WEB------------------------------
    //[BOOKNOW - 1]
    @WebMethod(operationName = "DisplayVillaAll")
    public String DisplayVillaAll() {
        try {
            String result = model_Villa.DisplayVillaAll();
            return result;
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaAll: " + ex);
        }
        return null;
    }

    //[BOOKNOW - 2]
    @WebMethod(operationName = "DisplayVillaId")
    public String DisplayVillaId(@WebParam(name = "idvilla") Integer idvilla) {
        try {
            String result = model_Villa.DisplayVillaId(idvilla);
            return result;
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaId: " + ex);
        }
        return null;
    }

    //[BOOKNOW - 2], [APP - DASHBOARD]
    @WebMethod(operationName = "CheckAvailability")
    public String CheckAvailability(@WebParam(name = "idvilla") Integer idvilla,
            @WebParam(name = "checkin") Date checkin,
            @WebParam(name = "checkout") Date checkout) {
        try {
            String result = model_Reservation.CheckAvailability(idvilla, checkin, checkout);
            return result;
        } catch (Exception ex) {
            System.out.println("Error CheckAvailability: " + ex);
        }
        return null;
    }

    //[BOOKNOW - 2], [APP - DASHBOARD]
    @WebMethod(operationName = "InsertReservation")
    public String InsertReservation(@WebParam(name = "checkin_date") Date checkin,
            @WebParam(name = "checkout_date") Date checkout,
            @WebParam(name = "total_guest") Integer total_guest,
            @WebParam(name = "notes") String notes,
            @WebParam(name = "iduser") Integer iduser,
            @WebParam(name = "idvilla") Integer idvilla) {
        try {
            String result = model_Reservation.InsertReservation(checkin, checkout, total_guest, notes, iduser, idvilla);
            return result;
        } catch (Exception ex) {
            System.out.println("Error CheckAvailability: " + ex);
        }
        return null;
    }
    //[BOOKNOW - 3]
    @WebMethod(operationName = "TrackOrder2")
    public String TrackOrder2(@WebParam(name = "idreservation") Integer idreservation) {

        //belum di code
        return null;
    }

    //[TRACKORDER]
    @WebMethod(operationName = "TrackOrder1")
    public String TrackOrder1(@WebParam(name = "idreservation") Integer idreservation) {

        //belum di code
        return null;
    }

    //[TRACKORDER]
    @WebMethod(operationName = "UploadPayment")
    public String UploadPayment(@WebParam(name = "bukti_pembayaran") Blob bukti_pembayaran,
            @WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.UploadPayment(bukti_pembayaran, idreservation);
        return result;
    }

    //[LOGIN CLIENT]
    @WebMethod(operationName = "LoginClient")
    public String LoginClient(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password) {
        try {
            String result = model_User.LoginClient(email, password);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Login Client: " + ex);
        }
        return null;
    }

    //[REGISTRATION CLIENT]
    @WebMethod(operationName = "Registration")
    public String Registration(@WebParam(name = "fullname") String fullname,
            @WebParam(name = "display_name") String display_name,
            @WebParam(name = "phoneNumber") String phoneNumber,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "ktp") String ktp) {

        String result = model_User.Registration(fullname, display_name, phoneNumber, email, password, ktp);
        return result;
    }

    //---------------------------------APP--------------------------------------
    //[LOGIN ADMIN]
    @WebMethod(operationName = "LoginAdmin")
    public String LoginAdmin(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password) {
        try {
            String result = model_User.LoginAdmin(email, password);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Login Admin: " + ex);
        }
        return null;
    }

    //[DASHBOARD]
    @WebMethod(operationName = "DisplayReservationAll")
    public String DisplayReservation(@WebParam(name = "kriteria") String kriteria,
            @WebParam(name = "dicari") String dicari) {
        //belum di code
        return null;
    }
    
    //[TRACKORDER]
    @WebMethod(operationName = "TrackOrderWeb")
    public String TrackOrderWeb(@WebParam(name = "idreservation") Integer idreservation) {

        //belum di code
        return null;
    }

    //[ORDERDETAILS]
    @WebMethod(operationName = "ChangeStatus")
    public String ChangeStatus(@WebParam(name = "status") String status,
            @WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.ChangeStatus(status, idreservation);
        return result;
    }

    //[ORDERDETAILS]
    @WebMethod(operationName = "UpdateReservation")
    public String UpdateReservation(@WebParam(name = "checkin_date") Date checkin,
            @WebParam(name = "checkout_date") Date checkout,
            @WebParam(name = "total_guest") Integer total_guest,
            @WebParam(name = "notes") String notes,
            @WebParam(name = "iduser") Integer iduser,
            @WebParam(name = "idvilla") Integer idvilla) {
        //code dibawah ini
        return null;
    }

    //[ORDERDETAILS]
    @WebMethod(operationName = "DisplayPayment")
    public String DisplayPayment(@WebParam(name = "idreservation") Integer idreservation) {
        return null;
    }

    //[CHATS]
    @WebMethod(operationName = "InsertChat")
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

    //[CHATS]
    @WebMethod(operationName = "DisplayChat")
    public String DisplayChat(
            @WebParam(name = "iduser") Integer iduser) {
        try {

        } catch (Exception ex) {
            System.out.println("Error Display Chat: " + ex);
        }
        return null;
    }

    //[CHATS]
    @WebMethod(operationName = "DisplayContacts")
    public String DisplayContacts(
            @WebParam(name = "iduser") Integer iduser) {
        try {
            String result = model_Chat.DisplayContacts(iduser);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Display Contacts: " + ex);
        }
        return null;
    }

}
