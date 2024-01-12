/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author umbertodomenicociccia
 */
@Embeddable
public class CorsaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDCorsa")
    private int iDCorsa;
    @Basic(optional = false)
    @Column(name = "Autobus")
    private String autobus;

    public CorsaPK() {
    }

    public CorsaPK(int iDCorsa, String autobus) {
        this.iDCorsa = iDCorsa;
        this.autobus = autobus;
    }

    public int getIDCorsa() {
        return iDCorsa;
    }

    public void setIDCorsa(int iDCorsa) {
        this.iDCorsa = iDCorsa;
    }

    public String getAutobus() {
        return autobus;
    }

    public void setAutobus(String autobus) {
        this.autobus = autobus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += iDCorsa;
        hash += (autobus != null ? autobus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorsaPK)) {
            return false;
        }
        CorsaPK other = (CorsaPK) object;
        if (this.iDCorsa != other.iDCorsa) {
            return false;
        }
        return (this.autobus != null || other.autobus == null) && (this.autobus == null || this.autobus.equals(other.autobus));
    }

    @Override
    public String toString() {
        return "data.CorsaPK[ iDCorsa=" + iDCorsa + ", autobus=" + autobus + " ]";
    }
    
}
