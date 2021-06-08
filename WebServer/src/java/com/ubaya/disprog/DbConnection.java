/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubaya.disprog;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ohanna
 */
public class DbConnection {
    Connection connect;
    Statement stat;
    ResultSet result;

    public java.sql.Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/disprog-uas", "root", "mysql");
        } catch (Exception ex) {
            System.out.println("Connection error : " + ex);
        }
        return connect;
    }
}
