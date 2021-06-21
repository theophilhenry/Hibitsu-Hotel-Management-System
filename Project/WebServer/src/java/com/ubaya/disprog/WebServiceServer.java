/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubaya.disprog;

import com.disprog.model.DbConnection;
import com.disprog.model.*;
import java.sql.Date;
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

    //---------------------------WEB------------------------------
    //[APP]
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

    //[BOOKNOW - 1]
    @WebMethod(operationName = "DisplayVillaAllWeb")
    public String DisplayVillaAllWeb() {
        try {
            String result = model_Villa.DisplayVillaAllWeb();
            return result;
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaAllWeb: " + ex);
        }
        return null;
    }

    //[APP]
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

    //[BOOKNOW - 2]
    @WebMethod(operationName = "DisplayVillaIdWeb")
    public String DisplayVillaIdWeb(@WebParam(name = "idvilla") Integer idvilla) {
        try {
            String result = model_Villa.DisplayVillaIdWeb(idvilla);
            return result;
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaId Web: " + ex);
        }
        return null;
    }

    //[BOOKNOW - 2], [APP - DASHBOARD]
    @WebMethod(operationName = "CheckAvailability")
    public String CheckAvailability(@WebParam(name = "idvilla") Integer idvilla,
            @WebParam(name = "checkin") String checkIn,
            @WebParam(name = "checkout") String checkOut) {
        try {
            Date checkInDate = Date.valueOf(checkIn);
            Date checkOutDate = Date.valueOf(checkOut);
            String result = model_Reservation.CheckAvailability(idvilla, checkInDate, checkOutDate);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Web Service Check Availability : " + ex);
        }
        return null;
    }

    @WebMethod(operationName = "CalculateTotalPrice")
    public String CalculateTotalPrice(@WebParam(name = "idvilla") Integer idvilla,
            @WebParam(name = "checkin") String checkIn,
            @WebParam(name = "checkout") String checkOut) {
        try {
            Date checkInDate = Date.valueOf(checkIn);
            Date checkOutDate = Date.valueOf(checkOut);
            Integer result = model_Reservation.CalculateTotalPrice(idvilla, checkInDate, checkOutDate);
            String result_int = String.valueOf(result);
            return result_int;
        } catch (Exception ex) {
            System.out.println("Error Web Service Check Availability : " + ex);
        }
        return null;
    }

    //[BOOKNOW - 2], [APP - DASHBOARD]
    @WebMethod(operationName = "InsertReservation")
    public String InsertReservation(@WebParam(name = "checkin_date") String checkIn,
            @WebParam(name = "checkout_date") String checkOut,
            @WebParam(name = "total_guest") Integer total_guest,
            @WebParam(name = "notes") String notes,
            @WebParam(name = "iduser") Integer iduser,
            @WebParam(name = "idvilla") Integer idvilla) {
        try {
            Date checkInDate = Date.valueOf(checkIn);
            Date checkOutDate = Date.valueOf(checkOut);
            String result = model_Reservation.InsertReservation(checkInDate, checkOutDate, total_guest, notes, iduser, idvilla);
            return result;
        } catch (Exception ex) {
            System.out.println("Error CheckAvailability: " + ex);
        }
        return null;
    }

    //[BOOKNOW - 3] 
    @WebMethod(operationName = "TrackOrder2")
    public String TrackOrder2(@WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.TrackOrderBook3(idreservation);
        return result;
    }

    //[TRACKORDER]
    @WebMethod(operationName = "TrackOrder1")
    public String TrackOrder1(@WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.TrackOrderMainWeb(idreservation);
        return result;
    }

    //[UPLOADPAYMENT]
    @WebMethod(operationName = "UploadPayment")
    public String UploadPayment(@WebParam(name = "url_bukti_pembayaran") String url_bukti_pembayaran,
            @WebParam(name = "idreservation") Integer idreservation) {
        String result = model_Reservation.UploadPayment(url_bukti_pembayaran, idreservation);
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
            @WebParam(name = "no_ktp") String no_ktp) {

        String result = model_User.Registration(fullname, display_name, phoneNumber, email, password, no_ktp);
        return result;
    }

    //---------------------------------APP--------------------------------------
    //[LOGIN ADMIN]
    @WebMethod(operationName = "DisplayAllClient")
    public String DisplayAllClient() {
        try {
            String result = model_User.DisplayAllClient();
            return result;
        } catch (Exception ex) {
            System.out.println("Error DisplayAllClient: " + ex);
        }
        return null;
    }

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
    public String DisplayReservationAll(@WebParam(name = "kriteria") String kriteria,
            @WebParam(name = "dicari") String dicari) {
        try {
            String result = model_Reservation.DisplayReservationAll(kriteria, dicari);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Display Reservation All: " + ex);
        }
        return null;
    }

    //[TRACKORDER]
    @WebMethod(operationName = "TrackOrderApp")
    public String TrackOrderApp(@WebParam(name = "idreservation") Integer idreservation) {
        try {
            String result = model_Reservation.TrackOrder(idreservation);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Login Admin: " + ex);
        }
        return null;
    }

    //[ORDERDETAILS]
    @WebMethod(operationName = "ChangeStatus")
    public String ChangeStatus(@WebParam(name = "status") String status,
            @WebParam(name = "idreservation") Integer idreservation) {
        try {
            String result = model_Reservation.ChangeStatus(status, idreservation);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Login Admin: " + ex);
        }
        return null;
    }

    //[ORDERDETAILS]
    @WebMethod(operationName = "UpdateReservation")
    public String UpdateReservation(@WebParam(name = "email") String email,
            @WebParam(name = "total_guest") Integer total_guest,
            @WebParam(name = "notes") String notes,
            @WebParam(name = "orderId") Integer orderId) {
            String resultStr = "";
        try {
            resultStr = model_Reservation.UpdateReservation(email,total_guest, notes, orderId);
            return resultStr;
        } catch (Exception ex) {
            System.out.println("Error UpdateReservation: " + ex +";;"+resultStr);
        }
        return null;
    }

    //[ORDERDETAILS]
    @WebMethod(operationName = "DisplayPayment")
    public String DisplayPayment(@WebParam(name = "idreservation") Integer idreservation) {
        try {
            String result = model_Reservation.DisplayPayment(idreservation);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Login Admin: " + ex);
        }
        return null;
    }

    //[CHATS]
    @WebMethod(operationName = "InsertChat")
    public String InsertChat(
            @WebParam(name = "email_sender") String email_sender,
            @WebParam(name = "email_receiver") String email_receiver,
            @WebParam(name = "messages") String messages) {
        try {
            String result = model_Chat.InsertChat(email_sender, email_receiver, messages);
            return result;
        } catch (Exception ex) {
            System.out.println("Error Insert Chat: " + ex);
        }
        return null;
    }

    //[CHATS]
    @WebMethod(operationName = "DisplayChat")
    public String DisplayChat(
            @WebParam(name = "email_sender") String email_sender,
            @WebParam(name = "email_receiver") String email_receiver) {
        try {
            String result = model_Chat.DisplayChat(email_sender, email_receiver);
            return result;
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

    @WebMethod(operationName = "GetUserIdBasedOnEmail")
    public String GetUserIdBasedOnEmail(@WebParam(name = "email") String email) {
        try {
            String result = model_User.GetUserIdBasedOnEmail(email);
            return result;
        } catch (Exception ex) {
            System.out.println("Error GetUserIdBasedOnEmail: " + ex);
        }
        return null;
    }

}
