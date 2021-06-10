/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
/**
         *
         * @author ohanna
         */

public class Reservations extends DbConnection {

    //<editor-fold defaultstate="collapsed" desc="Data Member">
    private Integer idreservation;
    private Timestamp res_timestamp;
    private Date chekin_date;
    private Date checkout_date;
    private String status;
    private Integer total_gusest;
    private String notes;
    private Integer total_price;
    private String url_bukti_pembayaran;
    private Integer idvilla;
    private Integer iduser;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Reservations() {
        getConnection();
    }

    public Reservations(Integer idreservation, Timestamp res_timestamp, Date chekin_date, Date checkout_date, String status, Integer total_gusest, String notes, Integer total_price, String url_bukti_pembayaran, Integer idvilla, Integer iduser) {
        getConnection();
        this.idreservation = idreservation;
        this.res_timestamp = res_timestamp;
        this.chekin_date = chekin_date;
        this.checkout_date = checkout_date;
        this.status = status;
        this.total_gusest = total_gusest;
        this.notes = notes;
        this.total_price = total_price;
        this.url_bukti_pembayaran = url_bukti_pembayaran;
        this.idvilla = idvilla;
        this.iduser = iduser;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public Integer getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public Timestamp getRes_timestamp() {
        return res_timestamp;
    }

    public void setRes_timestamp(Timestamp res_timestamp) {
        this.res_timestamp = res_timestamp;
    }

    public Date getChekin_date() {
        return chekin_date;
    }

    public void setChekin_date(Date chekin_date) {
        this.chekin_date = chekin_date;
    }

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal_gusest() {
        return total_gusest;
    }

    public void setTotal_gusest(Integer total_gusest) {
        this.total_gusest = total_gusest;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public String getUrl_bukti_pembayaran() {
        return url_bukti_pembayaran;
    }

    public void setUrl_bukti_pembayaran(String url_bukti_pembayaran) {
        this.url_bukti_pembayaran = url_bukti_pembayaran;
    }

    public Integer getIdvilla() {
        return idvilla;
    }

    public void setIdvilla(Integer idvilla) {
        this.idvilla = idvilla;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public Integer CalculateTotalPrice(Date checkIn, Date checkout, Integer idvilla) {
        try {
            // calculate different days
            int diffInDays = (int) (checkout.getTime() - checkIn.getTime() / (1000 * 60 * 60 * 24)
                    % 365);

            // calculate price
            //1. get the price of the villa
            String query = "SELECT price FROM villas WHERE idvilla =?";
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setInt(1, idvilla);
            result = sql.executeQuery();
            if (result.next()) {
                return result.getInt("price") * diffInDays;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String InsertReservation(Date checkIn, Date checkout, Integer total_guest, String notes, Integer iduser, Integer idvilla) {
        getConnection();
        String message = "";
        try {
            if (!connect.isClosed()) {
                //get totalPrice
                Integer totalPrice = this.CalculateTotalPrice(checkIn, checkout, idvilla);

                String check = this.CheckAvailability(idvilla, checkIn, checkout);
                if (check.equals("false")) {
                    return "false";
                }
                // set query
                String query = "INSERT INTO reservations(`checkin_date`,`checkout_date`,"
                        + "`total_guest`,`notes`,`total_price`,`iduser`,`idvilla`) "
                        + "VALUES(?,?,?,?,?,?,?)";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setDate(1, checkIn);
                sql.setDate(2, checkout);
                sql.setInt(3, total_guest);
                sql.setString(4, notes);
                sql.setInt(5, totalPrice);
                sql.setInt(6, iduser);
                sql.setInt(7, idvilla);

                result = sql.executeQuery();
                
                if (result.next()) {
                    String ket = "[1]hasilInsertReservation,[2]idreservation;;";
                    int idreservation = result.getInt(1);
                    return ket + "true;;"+idreservation;
                } else {
                    String ket = "[1]hasilInsertReservation;;";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            System.out.println("Error InsertReservation: " + ex);
        }
        return message;
    }
    //-----------------------------------------------
    public String UpdateReservation(String email, Date checkIn, Date checkout, Integer total_guest, String notes, Integer idvilla) {
        getConnection();
        String message = "";
        try {
            if (!connect.isClosed()) {
                //get totalPrice
                Integer totalPrice = this.CalculateTotalPrice(checkIn, checkout, idvilla);

                String check = this.CheckAvailability(idvilla, checkIn, checkout);
                if (check.equals("false")) {
                    return "false";
                }
                
                // set query
                String query = "UPDATE reservation r SET `r.checkin_date`=?,`r.checkout_date`=?,"
                        + "`r.total_guest`=?,`r.notes`=?,`r.total_price`=?,`r.idvilla`=? "
                        + "FROM reservations r INNER JOIN villas v ON r.idvilla = v.idvilla "
                        + "INNER JOIN users u ON r.idvilla = u.idvilla "
                        + "WHERE u.email = ? AND r.idreservation = ?";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setDate(1, checkIn);
                sql.setDate(2, checkout);
                sql.setInt(3, total_guest);
                sql.setString(4, notes);
                sql.setInt(5, totalPrice);
//                sql.setInt(5, );
                sql.setString(6, email);
                sql.setInt(7, idvilla);

                result = sql.executeQuery();
                
                if (result.next()) {
                    String ket = "[1]hasilInsertReservation,[2]idreservation;;";
                    int idreservation = result.getInt(1);
                    return ket + "true;;"+idreservation;
                } else {
                    String ket = "[1]hasilInsertReservation;;";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            System.out.println("Error InsertReservation: " + ex);
        }
        return message;
    }

    //masih bingung
    public String UploadPayment(String url_bukti_pembayaran, Integer idreservation) {
        String message = "";
        try {
            // set query
            String query = "UPDATE reservations SET url_bukti_pembayaran =? WHERE idreservation=?";

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            sql.setInt(2, idreservation);

            result = sql.executeQuery();
            String ket = "[1]hasilUploadPayment;;";
            if (result.next()) {
                return ket + "true";
            } else {
                return ket + "false";
            }
        } catch (SQLException ex) {
            System.out.println("Error Upload Payment: " + ex);
        }
        return message;
    }

    public String ChangeStatus(String status, Integer idreservation) {
        String message = "";
        try {
            // set query
            String query = "UPDATE reservations SET status =? WHERE idreservation=?";

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //cek status
            if (!(status.equals("PENDING") || status.equals("ACCEPTED") || status.equals("DECLINED") || status.equals("CANCELED"))) {
                return "false";
            }

            //set paramater
            sql.setString(1, status);
            sql.setInt(2, idreservation);

            String ket = "[1]hasilChangeStatus;;";
            if (result.next()) {
                return ket + "true";
            } else {
                return ket + "false";
            }
        } catch (Exception ex) {
            System.out.println("Error Upload Payment: " + ex);
        }
        return message;
    }

    public String CheckAvailability(Integer idvilla, Date checkin, Date checkout) {
        String message = "";
        try {
            if (!connect.isClosed()) {
                // set query
                String query = "SELECT * FROM reservations WHERE idvilla = ? &&"
                        + "((checkin_date <= ? AND checkout_date >= ?) || "
                        + "(checkin_date <= ? AND checkout_date >= ?)|| "
                        + "(checkin_date >= ? AND checkout_date <= ?))";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setInt(1, idvilla);
                sql.setDate(2, checkin);
                sql.setDate(3, checkin);
                sql.setDate(4, checkout);
                sql.setDate(5, checkout);
                sql.setDate(6, checkin);
                sql.setDate(7, checkout);

                result = sql.executeQuery();
                String ket = "[1]hasilUploadPayment;;";
                if (result.next()) {
                    return ket + "false";//karena ditemukan yg bentrok
                } else {
                    return ket + "true";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error Check Avaibility: " + ex);
        }
        return message;
    }

    //BELUM SELESAI BLOB
    public String TrackOrder(Integer idreservation) {
        try {
            String query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                    + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                    + "v.idvilla, v.name, "
                    + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.no_ktp "
                    + "FROM reservations r "
                    + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                    + "INNER JOIN users u ON r.iduser = u.iduser "
                    + "WHERE idreservation = ?;";
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
            result = sql.executeQuery();

            if (result.next()) {
                String ket = "[1]hasilTrackOrder,[2]idreservation,[3]res_timestamp,"
                        + "[4]chcekin_date,[5]checkout_date,[6]status,[7]total_guest,"
                        + "[8]notes,[9]url_bukti_pembayaran,[10]idvilla,[11]villa_name,"
                        + "[12]iduser,[13]fullname,[14]email,[15]no_ktp;;";
//                Blob url_bukti_pembayaran = connect.createBlob();
                
                String hasil = String.valueOf(result.getInt("idreservation")) + ";;"
                        + String.valueOf(result.getTimestamp("res_timestamp")) + ";;"
                        + result.getDate("checkin_date").toString() + ";;"
                        + result.getDate("checkout_date").toString() + ";;"
                        + result.getString("status") + ";;"
                        + String.valueOf(result.getInt("total_guest")) + ";;"
                        + result.getString("notes") + ";;"
                        + result.getString("url_bukti_pembayaran") + ";;"
                        + String.valueOf(result.getInt("idvilla")) + ";;"
                        + result.getString("name") + ";;"
                        + String.valueOf(result.getInt("iduser")) + ";;"
                        + result.getString("fullname") + ";;"
                        + result.getString("email") + ";;"
                        + result.getString("no_ktp");
                return ket + "true";
            } else {
                String ket = "[1]hasilTrackOrder;;";
                return ket + "false";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //masih belum 
    public String BacaData(String kriteria, String dicari) {
        String message = "";
        String query = "";
        try {
            // set query
            if (kriteria.equals("") && dicari.equals("")) {
                query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                        + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                        + "v.idvilla, v.name, v.address, v.total_bedroom, v.total_bathroom, v.facilities, v.unit_size, v.photo, v.price, v.description"
                        + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.role, u.no_ktp "
                        + "FROM reservations r "
                        + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                        + "INNER JOIN users u ON r.iduser = u.iduser ";
            } else {
                query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                        + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                        + "v.idvilla, v.name, v.address, v.total_bedroom, v.total_bathroom, v.facilities, v.unit_size, v.photo, v.price, v.description"
                        + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.role, u.no_ktp "
                        + "FROM reservations r "
                        + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                        + "INNER JOIN users u ON r.iduser = u.iduser "
                        + "WHERE " + kriteria + " = " + dicari + ";";
            }
            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
            result = sql.executeQuery();
            while (result.next()) {
                // ini belum di code
                String nanti = "||";
                return "true";

            }
        } catch (Exception ex) {
            System.out.println("Error Baca Data Reservasi: " + ex);
        }
        return message;
    }
    //</editor-fold> 
}
