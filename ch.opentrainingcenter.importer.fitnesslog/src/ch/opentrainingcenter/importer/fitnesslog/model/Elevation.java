//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.11 at 09:02:07 AM CET 
//


package ch.opentrainingcenter.importer.fitnesslog.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="DescendMeters" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AscendMeters" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Elevation")
public class Elevation {

    @XmlAttribute(name = "DescendMeters")
    protected BigDecimal descendMeters;
    @XmlAttribute(name = "AscendMeters")
    protected BigDecimal ascendMeters;

    /**
     * Gets the value of the descendMeters property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDescendMeters() {
        return descendMeters;
    }

    /**
     * Sets the value of the descendMeters property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDescendMeters(BigDecimal value) {
        this.descendMeters = value;
    }

    /**
     * Gets the value of the ascendMeters property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAscendMeters() {
        return ascendMeters;
    }

    /**
     * Sets the value of the ascendMeters property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAscendMeters(BigDecimal value) {
        this.ascendMeters = value;
    }

}
