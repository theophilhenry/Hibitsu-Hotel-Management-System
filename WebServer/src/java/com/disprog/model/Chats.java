/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import java.sql.Timestamp;

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
    
    //</editor-fold>
}
