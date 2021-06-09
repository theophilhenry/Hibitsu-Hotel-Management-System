/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.jws.WebParam;

/**
 *
 * @author ohanna
 */
public class Chats extends DbConnection{ 

    //<editor-fold defaultstate="collapsed" desc="Data Member">
    private int idchat;
    private Timestamp cht_timestamp;
    private String messages;
    private int idsender;
    private int idreceiver;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Chats(){
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
    public String DisplayChat(Integer idserver, Integer idreceiverd ) {

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
    //</editor-fold>
}
