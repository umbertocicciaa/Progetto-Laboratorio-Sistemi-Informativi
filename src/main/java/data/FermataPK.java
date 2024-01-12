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
public class FermataPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "via")
    private String via;
    @Basic(optional = false)
    @Column(name = "citta")
    private String citta;

    public FermataPK() {
    }

    public FermataPK(String via, String citta) {
        this.via = via;
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (via != null ? via.hashCode() : 0);
        hash += (citta != null ? citta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FermataPK)) {
            return false;
        }
        FermataPK other = (FermataPK) object;
        if ((this.via == null && other.via != null) || (this.via != null && !this.via.equals(other.via))) {
            return false;
        }
        return (this.citta != null || other.citta == null) && (this.citta == null || this.citta.equals(other.citta));
    }

    @Override
    public String toString() {
        return "data.FermataPK[ via=" + via + ", citta=" + citta + " ]";
    }
    
}
