
package com.ubaya.disprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisplayReservationAll complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DisplayReservationAll"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="kriteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dicari" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DisplayReservationAll", propOrder = {
    "kriteria",
    "dicari"
})
public class DisplayReservationAll {

    protected String kriteria;
    protected String dicari;

    /**
     * Gets the value of the kriteria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKriteria() {
        return kriteria;
    }

    /**
     * Sets the value of the kriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKriteria(String value) {
        this.kriteria = value;
    }

    /**
     * Gets the value of the dicari property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDicari() {
        return dicari;
    }

    /**
     * Sets the value of the dicari property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDicari(String value) {
        this.dicari = value;
    }

}
