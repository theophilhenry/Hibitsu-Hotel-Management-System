
package com.ubaya.disprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckAvailability complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckAvailability"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idvilla" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="checkin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="checkout" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAvailability", propOrder = {
    "idvilla",
    "checkin",
    "checkout"
})
public class CheckAvailability {

    protected Integer idvilla;
    protected String checkin;
    protected String checkout;

    /**
     * Gets the value of the idvilla property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdvilla() {
        return idvilla;
    }

    /**
     * Sets the value of the idvilla property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdvilla(Integer value) {
        this.idvilla = value;
    }

    /**
     * Gets the value of the checkin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckin() {
        return checkin;
    }

    /**
     * Sets the value of the checkin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckin(String value) {
        this.checkin = value;
    }

    /**
     * Gets the value of the checkout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckout() {
        return checkout;
    }

    /**
     * Sets the value of the checkout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckout(String value) {
        this.checkout = value;
    }

}
