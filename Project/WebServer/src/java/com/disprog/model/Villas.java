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
    private String url_photo;
    private String description;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Villas() {
        getConnection();
    }

    public Villas(int idvilla, String name, String address, int total_bedroom, int total_bathroom, String unit_size, String fasilities, String url_photo, String description) {
        getConnection();
        this.idvilla = idvilla;
        this.name = name;
        this.address = address;
        this.total_bedroom = total_bedroom;
        this.total_bathroom = total_bathroom;
        this.unit_size = unit_size;
        this.fasilities = fasilities;
        this.url_photo = url_photo;
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

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
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
                String query = "SELECT `idvilla`,`name`,`description`,`url_photo` FROM `villas`";
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                result = sql.executeQuery();
                String grandResult = "";
                while (result.next()) {
                    String ket = "[1]idvilla,[2]name,[3]description,[4]url_photo;;";
                    String hasil = String.valueOf(result.getInt("idvilla")) + ";;"
                            + result.getString("name") + ";;"
                            + result.getString("description") + ";;"
                            + result.getString("url_photo") + "||";
                    grandResult += ket + hasil;
                }
                return grandResult;
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaAll" + ex);
        }
        return null;
    }

    public String DisplayVillaAllWeb() {
        String ket = "[1]hasilTrue/False[2]DOM;;";
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT `idvilla`,`name`,`description`,`url_photo` FROM `villas`";
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                result = sql.executeQuery();
                String grandResult = "";
                while (result.next()) {
                    String hasil = ""
                            + "<div class='grid-container grid-book1 box-model box-model1' style='padding: 0'> "
                            + "<div class='grid-left' style='padding: 0'> "
                            + "<img src='" + result.getString("url_photo") + "' class='img-hotel' alt=''> "
                            + "</div> "
                            + "<div class='grid-right'> "
                            + "<p class='rubik-bold' style='font-size: 1.4em;'>" + result.getString("name") + "</p> "
                            + "<p class='karla-normal'> " + result.getString("description") + " </p> "
                            + "<form method='GET' action='book2.jsp'> "
                            + "<input type='hidden' name='idVilla' value='" + String.valueOf(result.getInt("idvilla")) + "'> "
                            + "<button class='btn btn-success rubik-bold color-white background-green' type='submit'>View Details</button> "
                            + "</form> "
                            + "</div> "
                            + "</div> ";
                    grandResult += hasil;
                }
                return ket + "true;;" + grandResult;
            } else {
                System.out.println("Tidak terkoneksi database");
            }
        } catch (Exception ex) {
            System.out.println("Error DisplayVillaAll" + ex);
        }
        return ket + "false";
    }

    //[APP]
    public String DisplayVillaId(Integer idvilla) {
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT * FROM `villas` WHERE idvilla = ?";
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                sql.setInt(1, idvilla);
                result = sql.executeQuery();
                if (result.next()) {
                    String ket = "[1]hasilDisplayVillaId,[2]idvilla,[3]name,"
                            + "[4]address,[5]total_bedroom,[5]total_bathroom,"
                            + "[6]facilities,[7]unit_size,[8]url_photo,[9]price,[10]description;;";
                    String hasil = String.valueOf(result.getInt("idvilla")) + ";;"
                            + result.getString("name") + ";;"
                            + result.getString("address") + ";;"
                            + String.valueOf(result.getInt("total_bedroom")) + ";;"
                            + String.valueOf(result.getInt("total_bathroom")) + ";;"
                            + result.getString("facilities") + ";;"
                            + result.getString("unit_size") + ";;"
                            + result.getString("url_photo") + ";;"
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

    public String DisplayVillaIdWeb(Integer idvilla) {
        try {
            if (!connect.isClosed()) {
                //set query
                String query = "SELECT * FROM `villas` WHERE idvilla = ?";
                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
                sql.setInt(1, idvilla);
                result = sql.executeQuery();
                if (result.next()) {
                    String ket = "[1]Keterangan Hasil [2] DOM;;";
                    String hasil = ""
                            + "            <img src='" + result.getString("url_photo") + "' class='img-hotel' alt='Foto Hotel'>"
                            + "            <div class='villa-description'> "
                            + "                <p class='rubik-bold'>" + result.getString("name") + "</p> "
                            + "                <p>" + result.getString("description") + "</p>"
                            + "            </div> "
                            + "            <div class='villa-details mb-4'> "
                            + "                <div class='villa-description'> "
                            + "                    <div class='information'> "
                            + "                        <p class='rubik-bold'>Located at</p> "
                            + "                        <p>" + result.getString("address") + "</p> "
                            + "                    </div> "
                            + "                    <div class='grid-container'> "
                            + "                        <div class='grid-left'> "
                            + "                            <div class='information'> "
                            + "                                <p class='rubik-bold'>Total Bedroom</p> "
                            + "                                <p>" + String.valueOf(result.getInt("total_bedroom")) + "</p> "
                            + "                            </div> "
                            + "                        </div> "
                            + "                        <div class='grid-right'> "
                            + "                            <div class='information'> "
                            + "                                <p class='rubik-bold'>Total Bathroom</p> "
                            + "                                <p>" + String.valueOf(result.getInt("total_bathroom")) + "</p> "
                            + "                            </div> "
                            + "                        </div> "
                            + "                    </div> "
                            + "                    <div class='information'> "
                            + "                        <p class='rubik-bold'>Facilities</p> "
                            + "                        <p>" + result.getString("facilities") + "</p> "
                            + "                    </div> "
                            + "                    <div class='information'> "
                            + "                        <p class='rubik-bold'>Unit Size</p> "
                            + "                        <p>" + result.getString("unit_size") + " m<sup>2</sup></p> "
                            + "                    </div> "
                            + "                    <div class='information'> "
                            + "                        <p class='rubik-bold'>Price</p> "
                            + "                        <p>" + String.valueOf(result.getInt("price")) + " / Day</p> "
                            + "                    </div> "
                            + "                </div> "
                            + "            </div> ";

                    return ket + "true;;" + hasil;
                } else {
                    String ket = "[1]hasilDisplayVillaId;;";
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
