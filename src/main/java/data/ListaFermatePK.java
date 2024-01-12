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
public class ListaFermatePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDCorsa")
    private int iDCorsa;
    @Basic(optional = false)
    @Column(name = "TargaAutobus")
    private String targaAutobus;
    @Basic(optional = false)
    @Column(name = "ViaFermata")
    private String viaFermata;
    @Basic(optional = false)
    @Column(name = "CittaFermata")
    private String cittaFermata;

    public ListaFermatePK() {
    }

    public ListaFermatePK(int iDCorsa, String targaAutobus, String viaFermata, String cittaFermata) {
        this.iDCorsa = iDCorsa;
        this.targaAutobus = targaAutobus;
        this.viaFermata = viaFermata;
        this.cittaFermata = cittaFermata;
    }

    public int getIDCorsa() {
        return iDCorsa;
    }

    public void setIDCorsa(int iDCorsa) {
        this.iDCorsa = iDCorsa;
    }

    public String getTargaAutobus() {
        return targaAutobus;
    }

    public void setTargaAutobus(String targaAutobus) {
        this.targaAutobus = targaAutobus;
    }

    public String getViaFermata() {
        return viaFermata;
    }

    public void setViaFermata(String viaFermata) {
        this.viaFermata = viaFermata;
    }

    public String getCittaFermata() {
        return cittaFermata;
    }

    public void setCittaFermata(String cittaFermata) {
        this.cittaFermata = cittaFermata;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += iDCorsa;
        hash += (targaAutobus != null ? targaAutobus.hashCode() : 0);
        hash += (viaFermata != null ? viaFermata.hashCode() : 0);
        hash += (cittaFermata != null ? cittaFermata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaFermatePK)) {
            return false;
        }
        ListaFermatePK other = (ListaFermatePK) object;
        if (this.iDCorsa != other.iDCorsa) {
            return false;
        }
        if ((this.targaAutobus == null && other.targaAutobus != null) || (this.targaAutobus != null && !this.targaAutobus.equals(other.targaAutobus))) {
            return false;
        }
        if ((this.viaFermata == null && other.viaFermata != null) || (this.viaFermata != null && !this.viaFermata.equals(other.viaFermata))) {
            return false;
        }
        return (this.cittaFermata != null || other.cittaFermata == null) && (this.cittaFermata == null || this.cittaFermata.equals(other.cittaFermata));
    }

    @Override
    public String toString() {
        return "data.ListaFermatePK[ iDCorsa=" + iDCorsa + ", targaAutobus=" + targaAutobus + ", viaFermata=" + viaFermata + ", cittaFermata=" + cittaFermata + " ]";
    }
    
}
