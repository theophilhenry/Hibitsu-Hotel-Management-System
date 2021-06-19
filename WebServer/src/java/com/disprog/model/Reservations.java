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
import java.text.SimpleDateFormat;
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
    public Integer CalculateTotalPrice(Integer idvilla, Date checkIn, Date checkout) {
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
                Integer totalPrice = this.CalculateTotalPrice(idvilla, checkIn, checkOut);

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

    public String UpdateReservation(String email, Integer total_guest, String notes, Integer orderId) {

        String message = "";
        String query = "";
        String ket = "[1]hasilUpdateReservation;;";
        try {
            if (!connect.isClosed()) {
                //get totalPrice

                // set query
                query = "UPDATE `reservations` r INNER JOIN `users` u ON r.iduser = u.iduser "
                        + "SET `total_guest`=?, "
                        + "`notes`= ? "
                        + "WHERE u.email = ? AND  r.idreservation= ?";
                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

                //set paramater
                sql.setInt(1, total_guest);
                sql.setString(2, notes);               
                sql.setString(3, email);
                sql.setInt(4, orderId);

                int affectedResult = sql.executeUpdate();

                if (affectedResult > 0) {
                    return ket + "true";
                } else {
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error UpdateReservationClass: " + ex);
        }
        return message;
    }

    public String ChangeStatus(String status, Integer idreservation) {
        String message = "";
        try {
            if (!connect.isClosed()) {
                String ket = "[1]hasilChangeStatus;;";
                // set query
                String query = "UPDATE reservations SET status =? where idreservation =?";

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
                sql.setString(1, url_bukti_pembayaran);
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
                String query = "Select url_bukti_pembayaran FROM reservations WHERE idreservation=?";

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

                //check tanggal dulu
                if (checkIn.after(checkOut)) {
                    String ket = "[1]hasilCheckAvailability;;";
                    return ket + "Please input checkout date greater than checkin date";
                }

                String query = "SELECT idreservation FROM reservations WHERE idvilla = ? &&"
                        + "checkin_date < ? AND checkout_date > ? AND (status ='PENDING' or status='APPROVED')";
                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                sql.setInt(1, idvilla);
                sql.setDate(2, checkOut);
                sql.setDate(3, checkIn);

                result = sql.executeQuery();

                if (result.next()) {
                    String ket = "[1]hasilCheckAvailability,[2]orderid;;";
                    return ket + "false;;" + result.getString("idreservation");//karena ditemukan yg bentrok
                } else {
                    String ket = "[1]hasilCheckAvailability;;";
                    return ket + "true";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error Class Reservation Check Avaibility: " + ex);
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
                sql.setInt(1, idreservation);

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

    public String TrackOrderMainWeb(Integer idreservation) {
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
                sql.setInt(1, idreservation);
                result = sql.executeQuery();

                if (result.next()) {
//                    String ket = "[1]hasilTrackOrder,[2]idreservation,[3]res_timestamp,"
//                            + "[4]chcekin_date,[5]checkout_date,[6]status,[7]total_guest,[8]total_price,"
//                            + "[9]notes,[10]url_bukti_pembayaran,[11]idvilla,[12]villa_name,"
//                            + "[13]iduser,[14]fullname,[15]display_name[15],phone_number,[16]email,[17]no_ktp;;";
//
//                    String hasil = String.valueOf(result.getInt("idreservation")) + ";;"
//                            + String.valueOf(result.getTimestamp("res_timestamp")) + ";;"
//                            + result.getDate("checkin_date").toString() + ";;"
//                            + result.getDate("checkout_date").toString() + ";;"
//                            + result.getString("status") + ";;"
//                            + String.valueOf(result.getInt("total_guest")) + ";;"
//                            + String.valueOf(result.getInt("total_price")) + ";;"
//                            + result.getString("notes") + ";;"
//                            + result.getString("url_bukti_pembayaran") + ";;"
//                            + String.valueOf(result.getInt("idvilla")) + ";;"
//                            + result.getString("name") + ";;"
//                            + String.valueOf(result.getInt("iduser")) + ";;"
//                            + result.getString("fullname") + ";;"
//                            + result.getString("display_name") + ";;"
//                            + result.getString("phone_number") + ";;"
//                            + result.getString("email") + ";;"
//                            + result.getString("no_ktp");

                    String statusValue = result.getString("status");
                    String statusClass = "status-" + statusValue.toLowerCase();

                    String paymentValue = result.getString("url_bukti_pembayaran");
                    String statusCheck = "";

                    if (paymentValue == null) {
                        statusCheck = ""
                                + "<div class='mb-3'>"
                                + "<label for='formFileSm' class='form-label karla-normal' style='text-align: left;'>Payment Slip hasn't uploaded yet. <br>To Upload payment slip, you need to <br>1. Upload your payment slip photo to Google Drive<br>2. Share and get the link<br>3. Set the link to anyone can view<br>4. Copy the link and paste it here.</label>"
                                + "<form method='POST' action='track-order-handler.jsp'>"
                                + "<input type='hidden' name='command' value='uploadBuktiPembayaran'>"
                                + "<input type='hidden' name='idReservation' value='" + idreservation + "'>"
                                + "<input type='text' name='urlBuktiPembayaran' class='form-control mb-3' id='exampleFormControlInput1' placeholder='https://drive.google.com/file/d/xxx'>"
                                + "<button class='btn btn-success rubik-bold color-white background-green mb-3' type='submit' style='width: 100%;'>Upload</button>"
                                + "</form>"
                                + "</div>";
                    }

                    String hasilDOM = ""
                            + "<p>ORDER ID : " + String.valueOf(result.getInt("idreservation")) + "</p>"
                            + "<div class='track-details mb-4'>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>YOUR NAME</p>"
                            + "<p>" + result.getString("fullname") + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>VILLAS</p>"
                            + "<p>" + result.getString("name") + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>DATE</p>"
                            + "<p>"
                            + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("checkin_date").toString()))
                            + " - "
                            + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("checkout_date").toString()))
                            + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>TOTAL GUEST</p>"
                            + "<p>" + String.valueOf(result.getInt("total_guest")) + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>DATE OF ORDER</p>"
                            + "<p>" + String.valueOf(result.getTimestamp("res_timestamp")) + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>TOTAL PRICE</p>"
                            + "<p>" + String.valueOf(result.getInt("total_price")) + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>NOTES</p>"
                            + "<p>" + result.getString("notes") + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>STATUS</p>"
                            + "<p class='rubik-bold " + statusClass + "'>" + statusValue + "</p>"
                            + statusCheck
                            + "</div>"
                            + "</div>";
                    return hasilDOM;
                } else {
//                    String ket = "[1]hasilTrackOrder;;";
                    return "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String TrackOrderBook3(Integer idreservation) {
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
                sql.setInt(1, idreservation);
                result = sql.executeQuery();

                if (result.next()) {
//                    String ket = "[1]hasilTrackOrder,[2]idreservation,[3]res_timestamp,"
//                            + "[4]chcekin_date,[5]checkout_date,[6]status,[7]total_guest,[8]total_price,"
//                            + "[9]notes,[10]url_bukti_pembayaran,[11]idvilla,[12]villa_name,"
//                            + "[13]iduser,[14]fullname,[15]display_name[15],phone_number,[16]email,[17]no_ktp;;";
//
//                    String hasil = String.valueOf(result.getInt("idreservation")) + ";;"
//                            + String.valueOf(result.getTimestamp("res_timestamp")) + ";;"
//                            + result.getDate("checkin_date").toString() + ";;"
//                            + result.getDate("checkout_date").toString() + ";;"
//                            + result.getString("status") + ";;"
//                            + String.valueOf(result.getInt("total_guest")) + ";;"
//                            + String.valueOf(result.getInt("total_price")) + ";;"
//                            + result.getString("notes") + ";;"
//                            + result.getString("url_bukti_pembayaran") + ";;"
//                            + String.valueOf(result.getInt("idvilla")) + ";;"
//                            + result.getString("name") + ";;"
//                            + String.valueOf(result.getInt("iduser")) + ";;"
//                            + result.getString("fullname") + ";;"
//                            + result.getString("display_name") + ";;"
//                            + result.getString("phone_number") + ";;"
//                            + result.getString("email") + ";;"
//                            + result.getString("no_ktp");

                    String statusValue = result.getString("status");
                    String statusClass = "status-" + statusValue.toLowerCase();

                    String hasilDOM = ""
                            + "<p>ORDER ID : " + String.valueOf(result.getInt("idreservation")) + "</p>"
                            + "<div class='client-order-card'>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>YOUR NAME</p>"
                            + "<p>" + result.getString("fullname") + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>VILLAS</p>"
                            + "<p>" + result.getString("name") + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>DATE</p>"
                            + "<p>"
                            + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("checkin_date").toString()))
                            + " - "
                            + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("checkout_date").toString()))
                            + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>TOTAL GUEST</p>"
                            + "<p>" + String.valueOf(result.getInt("total_guest")) + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>DATE OF ORDER</p>"
                            + "<p>" + String.valueOf(result.getTimestamp("res_timestamp")) + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>TOTAL PRICE</p>"
                            + "<p>" + String.valueOf(result.getInt("total_price")) + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>NOTES</p>"
                            + "<p>" + result.getString("notes") + "</p>"
                            + "</div>"
                            + "<div class='information'>"
                            + "<p class='rubik-bold'>STATUS</p>"
                            + "<p class='rubik-bold " + statusClass + "'>" + statusValue + "</p>"
                            + "</div>"
                            + "</div>";
                    return hasilDOM;
                } else {
//                    String ket = "[1]hasilTrackOrder;;";
                    return "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String DisplayReservationAll(String kriteria, String dicari) {
        try {
            if (!connect.isClosed()) {
                String query = "";
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
                            + "WHERE " + kriteria + " LIKE '%" + dicari + "%' AND checkout_date >= CURDATE();";
                }

                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                result = sql.executeQuery();
                String hasil = "";
                String ket = "[1]idreservation,[2]res_timestamp,"
                        + "[3]chcekin_date,[4]checkout_date,[5]status,[6]total_guest,[7]total_price,"
                        + "[8]notes,[19]url_bukti_pembayaran,[10]idvilla,[11]villa_name,"
                        + "[12]iduser,[13]fullname,[14]display_name[15],phone_number,[16]email,[17]no_ktp;;";
                while (result.next()) {
                    hasil = hasil + ket + String.valueOf(result.getInt("idreservation")) + ";;"
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
                            + result.getString("no_ktp") + "||";
                }
                return hasil;
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //</editor-fold> 
}
