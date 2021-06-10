/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import java.sql.Date;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            if (!connect.isClosed()) {
                // calculate different days
                int diffInDays = (int) ((checkout.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24)
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
            } else {
                System.out.println("Tidak terkoneksi database");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String InsertReservation(Date checkIn, Date checkOut, Integer total_guest, String notes, Integer iduser, Integer idvilla) {
        getConnection();
        try {
            if (!connect.isClosed()) {

                //get totalPrice
                Integer totalPrice = this.CalculateTotalPrice(checkIn, checkOut, idvilla);

                String check = this.CheckAvailability(idvilla, checkIn, checkOut);
                if (check.contains("false")) {
                    String ket = "[1]hasilInsertReservation;;";
                    return ket + "false";
                }
                // set query
                String query = "INSERT INTO reservations(`checkin_date`,`checkout_date`,"
                        + "`total_guest`,`notes`,`total_price`,`iduser`,`idvilla`) "
                        + "VALUES(?,?,?,?,?,?,?)";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                //set paramater
                sql.setDate(1, checkIn);
                sql.setDate(2, checkOut);
                sql.setInt(3, total_guest);
                sql.setString(4, notes);
                sql.setInt(5, totalPrice);
                sql.setInt(6, iduser);
                sql.setInt(7, idvilla);

                int affectedResult = sql.executeUpdate();

                if (affectedResult > 0) {
                    String ket = "[1]hasilInsertReservation,[2]idreservation;;";
                    ResultSet generatedKeys = sql.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        idreservation = generatedKeys.getInt(1);
                    }
                    return ket + "true;;" + idreservation;

                } else {
                    String ket = "[1]hasilInsertReservation;;";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error InsertReservation: " + ex);
        }
        return null;
    }

    public String UpdateReservation(String email, Date checkIn, Date checkOut, Integer total_guest, String notes, Integer idvilla) {

        String message = "";
        try {
            if (!connect.isClosed()) {

                //get totalPrice
                Integer totalPrice = this.CalculateTotalPrice(checkIn, checkOut, idvilla);

                String check = this.CheckAvailability(idvilla, checkIn, checkOut);
                if (check.contains("false")) {
                    String ket = "[1]hasilUpdateReservation;;";
                    return ket + "false";
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
                sql.setDate(2, checkOut);
                sql.setInt(3, total_guest);
                sql.setString(4, notes);
                sql.setInt(5, totalPrice);
                sql.setString(6, email);
                sql.setInt(7, idvilla);

                int affectedResult = sql.executeUpdate();

                if (affectedResult > 0) {
                    String ket = "[1]hasilUpdateReservation,[2]idreservation;;";
                    ResultSet generatedKeys = sql.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        idreservation = generatedKeys.getInt(1);
                    }
                    return ket + "true;;" + idreservation;
                } else {
                    String ket = "[1]hasilInsertReservation;;";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error InsertReservation: " + ex);
        }
        return message;
    }

    public String ChangeStatus(String status, Integer idreservation) {
        String message = "";
        try {
            if (!connect.isClosed()) {
                String ket = "[1]hasilChangeStatus;;";
                // set query
                String query = "UPDATE reservation SET status =? where idreservation =?";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater  
                sql.setString(1, status);
                sql.setInt(2, idreservation);
                int affectedResult = sql.executeUpdate();

                if (affectedResult > 0) {
                    return ket + "true";
                } else {
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            System.out.println("Error ChangeStatus: " + ex);
        }
        return message;
    }
    //masih bingung

    public String UploadPayment(String url_bukti_pembayaran, Integer idreservation) {
        String message = "";
        try {
            if (!connect.isClosed()) {
                // set query
                String query = "UPDATE reservations SET url_bukti_pembayaran =? WHERE idreservation=?";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                sql.setInt(2, idreservation);

                int affectedResult = sql.executeUpdate();
                String ket = "[1]hasilUploadPayment;;";
                if (affectedResult > 0) {
                    return ket + "true";
                } else {
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            System.out.println("Error Upload Payment: " + ex);
        }
        return message;
    }

    public String DisplayPayment(Integer idreservation) {
        String message = "";
        try {
            if (!connect.isClosed()) {
                // set query
                String query = "Select url_bukti_pembayaran WHERE idreservation=?";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setInt(1, idreservation);

                result = sql.executeQuery();

                String ket = "[1]hasilDisplayPayment,[2]url_bukti_pembayaran;;";
                if (result.next()) {
                    return ket + "true;;" + result.getString("url_bukti_pembayaran");
                } else {
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error Upload Payment: " + ex);
        }
        return message;
    }

    public String CheckAvailability(Integer idvilla, Date checkIn, Date checkOut) {
        String message = "";
        try {
            if (!connect.isClosed()) {
                String ket = "[1]hasilUploadPayment;;";
                //check tanggal dulu

                if (checkIn.after(checkOut)) {
                    return ket + "Pleaase input checkout date greater than checkin date";
                }
                // set query
                String query = "SELECT * FROM reservations WHERE idvilla = ? &&"
                        + "((checkin_date <= ? AND checkout_date >= ?) || "
                        + "(checkin_date <= ? AND checkout_date >= ?)|| "
                        + "(checkin_date >= ? AND checkout_date <= ?))";

//                String query = "SELECT * FROM reservations WHERE idvilla = ? &&"
//                        + "checkin <= ? AND checkout >= ?";
                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                sql.setDate(2, checkIn);
                sql.setDate(1, checkOut);

                //set paramater
//                sql.setInt(1, idvilla);
//                sql.setDate(2, checkIn);
//                sql.setDate(3, checkIn);
//                sql.setDate(4, checkOut);
//                sql.setDate(5, checkOut);
//                sql.setDate(6, checkIn);
//                sql.setDate(7, checkOut);
                result = sql.executeQuery();

                if (result.next()) {
                    return ket + "";//karena ditemukan yg bentrok
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
            if (!connect.isClosed()) {
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
                            + "[4]chcekin_date,[5]checkout_date,[6]status,[7]total_guest,[8]total_price,"
                            + "[9]notes,[10]url_bukti_pembayaran,[11]idvilla,[12]villa_name,"
                            + "[13]iduser,[14]fullname,[15]display_name[15],phone_number,[16]email,[17]no_ktp;;";

                    String hasil = String.valueOf(result.getInt("idreservation")) + ";;"
                            + String.valueOf(result.getTimestamp("res_timestamp")) + ";;"
                            + result.getDate("checkin_date").toString() + ";;"
                            + result.getDate("checkout_date").toString() + ";;"
                            + result.getString("status") + ";;"
                            + String.valueOf(result.getInt("total_guest")) + ";;"
                            + String.valueOf(result.getInt("total_price")) + ";;"
                            + result.getString("notes") + ";;"
                            + result.getString("url_bukti_pembayaran") + ";;"
                            + String.valueOf(result.getInt("idvilla")) + ";;"
                            + result.getString("name") + ";;"
                            + String.valueOf(result.getInt("iduser")) + ";;"
                            + result.getString("fullname") + ";;"
                            + result.getString("display_name") + ";;"
                            + result.getString("phone_number") + ";;"
                            + result.getString("email") + ";;"
                            + result.getString("no_ktp");
                    return ket + "true;;" + hasil;
                } else {
                    String ket = "[1]hasilTrackOrder;;";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> DisplayReservationAll(String kriteria, String dicari) {
        try {
            if (!connect.isClosed()) {
                String query = "";
                ArrayList<String> listOfReservation = new ArrayList<>();
                if (kriteria.equals("") || dicari.equals("")) {
                    query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                            + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                            + "v.idvilla, v.name, "
                            + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.no_ktp "
                            + "FROM reservations r "
                            + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                            + "INNER JOIN users u ON r.iduser = u.iduser "
                            + "WHERE checkout_date >= CURDATE();";
                } else {
                    query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                            + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                            + "v.idvilla, v.name, "
                            + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.no_ktp "
                            + "FROM reservations r "
                            + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                            + "INNER JOIN users u ON r.iduser = u.iduser "
                            + "WHERE " + kriteria + " = " + dicari + " AND checkout_date >= CURDATE();";
                }

                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                sql.setString(1, kriteria);
                sql.setString(2, dicari);
                result = sql.executeQuery();

                while (result.next()) {
                    String ket = "[1]hasilTrackOrder,[2]idreservation,[3]res_timestamp,"
                            + "[4]chcekin_date,[5]checkout_date,[6]status,[7]total_guest,[8]total_price,"
                            + "[9]notes,[10]url_bukti_pembayaran,[11]idvilla,[12]villa_name,"
                            + "[13]iduser,[14]fullname,[15]display_name[15],phone_number,[16]email,[17]no_ktp;;";

                    String hasil = String.valueOf(result.getInt("idreservation")) + ";;"
                            + String.valueOf(result.getTimestamp("res_timestamp")) + ";;"
                            + result.getDate("checkin_date").toString() + ";;"
                            + result.getDate("checkout_date").toString() + ";;"
                            + result.getString("status") + ";;"
                            + String.valueOf(result.getInt("total_guest")) + ";;"
                            + String.valueOf(result.getInt("total_price")) + ";;"
                            + result.getString("notes") + ";;"
                            + result.getString("url_bukti_pembayaran") + ";;"
                            + String.valueOf(result.getInt("idvilla")) + ";;"
                            + result.getString("name") + ";;"
                            + String.valueOf(result.getInt("iduser")) + ";;"
                            + result.getString("fullname") + ";;"
                            + result.getString("display_name") + ";;"
                            + result.getString("phone_number") + ";;"
                            + result.getString("email") + ";;"
                            + result.getString("no_ktp");
                    listOfReservation.add(ket + "true;;" + hasil);
                }
                return listOfReservation;
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //G PAKEK -----------------------------------------------------------------------------
    public ArrayList<String> DisplayReservation(String kriteria, String dicari) {
        String message = "";
        String query = "";
        try {
            // set query
            if (kriteria.equals("") && dicari.equals("")) {
                query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                        + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                        + "v.idvilla, v.name, v.price, v.description"
                        + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.no_ktp "
                        + "FROM reservations r "
                        + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                        + "INNER JOIN users u ON r.iduser = u.iduser AND checkout_date>= CURDATE();";
            } else {
                query = "SELECT r.idreservation, r.res_timestamp, r.checkin_date, "
                        + "r.checkout_date, r.status, r.total_guest, r.total_price, r.notes, r.url_bukti_pembayaran, "
                        + "v.idvilla, v.name, v.price, v.description"
                        + "u.iduser, u.fullname, u.display_name, u.phone_number, u.email, u.no_ktp "
                        + "FROM reservations r "
                        + "INNER JOIN villas v ON r.idvilla = v.idvilla "
                        + "INNER JOIN users u ON r.iduser = u.iduser "
                        + "WHERE " + kriteria + " = " + dicari + "AND checkout_date>= CURDATE();";
            }
            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
            result = sql.executeQuery();
            String ket = "[1]hasilDisplayReservasi[,2]r.idreservation,[3]r.res_timestamp,[4]r.checkin_date,"
                    + "[5]r.checkout_date,[6]r.status,[7]r.total_guest,[8]r.total_price,[9]r.notes,[10]r.url_bukti_pembayaran, "
                    + "[11]v.idvilla,[12]v.name,[13]v.price,[14] v.description"
                    + "[15]u.iduser,[16] u.fullname,[17]u.display_name,[18]u.phone_number,[19]u.email";
            while (result.next()) {
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
                // ini belum di code
                String nanti = "||";

            }
            return null;
        } catch (Exception ex) {
            System.out.println("Error Baca Data Reservasi: " + ex);
        }
        return null;
    }
    //</editor-fold> 
}
