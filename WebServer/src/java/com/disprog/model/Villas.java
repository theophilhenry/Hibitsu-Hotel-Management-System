/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disprog.model;

import com.mysql.jdbc.PreparedStatement;

/**
 *
 * @author ohanna
 */
public class Villas extends DbConnection {

    //<editor-fold defaultstate="collapsed" desc="Data Member">
    private int idvilla;
    private String name;
    private String address;
    private int total_bedroom;
    private int total_bathroom;
    private String unit_size;
    private String fasilities;
    private String photo;
    private String description;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Villas() {
        getConnection();
    }

    public Villas(int idvilla, String name, String address, int total_bedroom, int total_bathroom, String unit_size, String fasilities, String photo, String description) {
        getConnection();
        this.idvilla = idvilla;
        this.name = name;
        this.address = address;
        this.total_bedroom = total_bedroom;
        this.total_bathroom = total_bathroom;
        this.unit_size = unit_size;
        this.fasilities = fasilities;
        this.photo = photo;
        this.description = description;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getIdvilla() {
        return idvilla;
    }

    public void setIdvilla(int idvilla) {
        this.idvilla = idvilla;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal_bedroom() {
        return total_bedroom;
    }

    public void setTotal_bedroom(int total_bedroom) {
        this.total_bedroom = total_bedroom;
    }

    public int getTotal_bathroom() {
        return total_bathroom;
    }

    public void setTotal_bathroom(int total_bathroom) {
        this.total_bathroom = total_bathroom;
    }

    public String getUnit_size() {
        return unit_size;
    }

    public void setUnit_size(String unit_size) {
        this.unit_size = unit_size;
    }

    public String getFasilities() {
        return fasilities;
    }

    public void setFasilities(String fasilities) {
        this.fasilities = fasilities;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public String DisplayVillaAll() {
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT `idvilla`,`name`,`description`,`photo` FROM `villas`";

                //set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                result = sql.executeQuery();

                //nanti mau diatur lagi return nya seperti apa
                while (result.next()) {
//                    Blob blob = result.getBlob(cloumnName[i]);
//                    byte[] bdata = blob.getBytes(1, (int) blob.length());
//                    String s = new String(bdata);
                    String ket = "[1]idvilla,[2]name,[3]description,[4]photo;;";
                    String hasil = String.valueOf(result.getInt("idvilla")) + ";;"
                            + result.getString("name") + ";;"
                            + result.getString("description") + ";;"
                            + String.valueOf(result.getBlob("photo")) + "||";
                    return ket + hasil;
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaAll" + ex);
        }
        return null;
    }

    //[BOOKNOW - 2]
    public String DisplayVillaId(Integer idvilla) {
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT * FROM `villas` WHERE idvilla = ?";

                //set preparedStatement
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                sql.setInt(1, idvilla);
                result = sql.executeQuery();

                //nanti mau diatur lagi return nya seperti apa
                if (result.next()) {
                    String ket = "[1]hasilDisplayVillaId,[2]idvilla,[3]name,"
                            + "[4]address,[5]total_bedroom,[5]total_bathroom,"
                            + "[6]facilities,[7]unit_size,[8]photo,[9]price,[10]description;;";
                    String hasil = String.valueOf(result.getInt("idvilla")) + ";;"
                            + result.getString("name") + ";;"
                            + result.getString("address") + ";;"
                            + String.valueOf(result.getInt("total_bedroom")) + ";;"
                            + String.valueOf(result.getInt("total_bathroom")) + ";;"
                            + result.getString("facilities") + ";;"
                            + result.getString("unit_size") + ";;"
                            + String.valueOf(result.getBlob("photo")) + ";;"
                            + String.valueOf(result.getInt("price")) + ";;"
                            + result.getString("description") + ";;";

                    return ket + "true;;" + hasil;
                } else {
                    String ket = "[1]hasilDisplayVillaId";
                    return ket + "false";
                }
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaId: " + ex);
        }
        return null;
    }
    //</editor-fold>
}