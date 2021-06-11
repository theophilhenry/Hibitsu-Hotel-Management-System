
package com.ubaya.disprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisplayChat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DisplayChat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="email_sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email_receiver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DisplayChat", propOrder = {
    "emailSender",
    "emailReceiver"
})
public class DisplayChat {

    @XmlElement(name = "email_sender")
    protected String emailSender;
    @XmlElement(name = "email_receiver")
    protected String emailReceiver;

    /**
     * Gets the value of the emailSender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSender() {
        return emailSender;
    }

    /**
     * Sets the value of the emailSender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSender(String value) {
        this.emailSender = value;
    }

    /**
     * Gets the value of the emailReceiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailReceiver() {
        return emailReceiver;
    }

    /**
     * Sets the value of the emailReceiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailReceiver(String value) {
        this.emailReceiver = value;
    }

}
