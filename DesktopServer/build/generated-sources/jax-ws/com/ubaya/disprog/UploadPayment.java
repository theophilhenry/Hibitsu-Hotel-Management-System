
package com.ubaya.disprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UploadPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UploadPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="url_bukti_pembayaran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idreservation" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UploadPayment", propOrder = {
    "urlBuktiPembayaran",
    "idreservation"
})
public class UploadPayment {

    @XmlElement(name = "url_bukti_pembayaran")
    protected String urlBuktiPembayaran;
    protected Integer idreservation;

    /**
     * Gets the value of the urlBuktiPembayaran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlBuktiPembayaran() {
        return urlBuktiPembayaran;
    }

    /**
     * Sets the value of the urlBuktiPembayaran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlBuktiPembayaran(String value) {
        this.urlBuktiPembayaran = value;
    }

    /**
     * Gets the value of the idreservation property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdreservation() {
        return idreservation;
    }

    /**
     * Sets the value of the idreservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdreservation(Integer value) {
        this.idreservation = value;
    }

}
