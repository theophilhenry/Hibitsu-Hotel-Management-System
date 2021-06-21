/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ohanna
 */
public class Chats extends DbConnection {

    //<editor-fold defaultstate="collapsed" desc="Data Member">
    private int idchat;
    private Timestamp cht_timestamp;
    private String messages;
    private int idsender;
    private int idreceiver;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Chats() {
        getConnection();
    }

    public Chats(int idchat, String messages, int idsender, int idreceiver) {
        getConnection();
        this.idchat = idchat;
        this.messages = messages;
        this.idsender = idsender;
        this.idreceiver = idreceiver;
        this.cht_timestamp = null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getIdchat() {
        return idchat;
    }

    public void setIdchat(int idchat) {
        this.idchat = idchat;
    }

    public Timestamp getCht_timestamp() {
        return cht_timestamp;
    }

    public void setCht_timestamp(Timestamp cht_timestamp) {
        this.cht_timestamp = cht_timestamp;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public int getIdsender() {
        return idsender;
    }

    public void setIdsender(int idsender) {
        this.idsender = idsender;
    }

    public int getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(int idreceiver) {
        this.idreceiver = idreceiver;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public String InsertChat(String email_sender, String email_receiver, String messages) {
        try {
            String query = "";
           
            if (!connect.isClosed()) {
                
                query = "INSERT INTO chats (idsender, idreceiver, messages) "
                        + "values ((SELECT iduser FROM users WHERE email =?),"
                        + "(SELECT iduser FROM users WHERE email =?), ?)";

                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                //set paramater
                sql.setString(1, email_sender);
                sql.setString(2, email_receiver);
                sql.setString(3, messages);

                //nanti mau diatur lagi return nya seperti 
                String ket = "[1]hasilInsertChats;;";
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
            Logger.getLogger(Chats.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String DisplayChat(String email_sender, String email_receiver) {
        String grandHasil = "";
        System.out.println("Masuk DisplayChat 126");
        try {
            if (!connect.isClosed()) {
                String query = "SELECT * FROM chats "
                        + "WHERE (idsender =(SELECT iduser FROM users where email=?) "
                        + "and idreceiver =(SELECT iduser FROM users where email=?)) "
                        + "or (idsender=(SELECT iduser FROM users where email=?) "
                        + "and idreceiver=(SELECT iduser FROM users where email=?)) "
                        + "ORDER BY cht_timestamp;";
                // set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                sql.setString(1, email_sender);
                sql.setString(2, email_receiver);
                sql.setString(3, email_receiver);
                sql.setString(4, email_sender);

                result = sql.executeQuery();
                String ket = "[1]hasilInsertChats[2]idsender[3]messages;;";
                while (result.next()) {
                    String hasil = String.valueOf(result.getInt("idsender"))
                            + ";;" +  result.getString("messages") + "||";
                    grandHasil += ket + hasil;
                }
                return grandHasil;
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (SQLException ex) {
            System.out.println("Error Insert Chat: " + ex);
        }
        return null;
    }

    public String DisplayContacts(Integer iduser) {
        getConnection();
        try {
            String query = "SELECT u.iduser, u.display_name, a.idsender, a.cht_timestamp, a.messages "
                    + "FROM users u LEFT JOIN( "
                    + "SELECT c.*, u1.display_name AS sender, u2.display_name AS recipient, "
                    + "CASE WHEN c.idsender = ? THEN u2.iduser "
                    + "WHEN c.idreceiver = ? THEN u1.iduser "
                    + "ELSE 'none' END AS idcontact "
                    + "FROM chats c "
                    + "INNER JOIN users u1 ON c.idsender= u1.iduser "
                    + "INNER JOIN users u2 ON c.idreceiver = u2.iduser "
                    + "WHERE c.idchat = ANY ( "
                    + "SELECT MAX(idchat) "
                    + "FROM chats "
                    + "WHERE idsender = ? OR idreceiver = ? "
                    + "GROUP BY idconversation) ORDER BY c.idchat DESC) AS a "
                    + "ON u.iduser = a.idcontact WHERE u.iduser != ? AND u.role != 'ADMIN'";

            // set preparedStatement
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

            //set paramater
            sql.setInt(1, iduser);
            sql.setInt(2, iduser);
            sql.setInt(3, iduser);
            sql.setInt(4, iduser);
            sql.setInt(5, iduser);

            result = sql.executeQuery();
            while (result.next()) {
                //code di sini untuk get nya
            }
        } catch (SQLException ex) {
            System.out.println("Error Insert Chat: " + ex);
        }
        return null;
    }
    //</editor-fold>
}
