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
public class PreventivoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PIVAFornitore")
    private String pIVAFornitore;
    @Basic(optional = false)
    @Column(name = "ProdottoDaOrdinare")
    private int prodottoDaOrdinare;
    @Basic(optional = false)
    @Column(name = "AutomezzoDaOrdinare")
    private String automezzoDaOrdinare;

    public PreventivoPK() {
    }

    public PreventivoPK(String pIVAFornitore, int prodottoDaOrdinare, String automezzoDaOrdinare) {
        this.pIVAFornitore = pIVAFornitore;
        this.prodottoDaOrdinare = prodottoDaOrdinare;
        this.automezzoDaOrdinare = automezzoDaOrdinare;
    }

    public String getPIVAFornitore() {
        return pIVAFornitore;
    }

    public void setPIVAFornitore(String pIVAFornitore) {
        this.pIVAFornitore = pIVAFornitore;
    }

    public int getProdottoDaOrdinare() {
        return prodottoDaOrdinare;
    }

    public void setProdottoDaOrdinare(int prodottoDaOrdinare) {
        this.prodottoDaOrdinare = prodottoDaOrdinare;
    }

    public String getAutomezzoDaOrdinare() {
        return automezzoDaOrdinare;
    }

    public void setAutomezzoDaOrdinare(String automezzoDaOrdinare) {
        this.automezzoDaOrdinare = automezzoDaOrdinare;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pIVAFornitore != null ? pIVAFornitore.hashCode() : 0);
        hash += (int) prodottoDaOrdinare;
        hash += (automezzoDaOrdinare != null ? automezzoDaOrdinare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreventivoPK)) {
            return false;
        }
        PreventivoPK other = (PreventivoPK) object;
        if ((this.pIVAFornitore == null && other.pIVAFornitore != null) || (this.pIVAFornitore != null && !this.pIVAFornitore.equals(other.pIVAFornitore))) {
            return false;
        }
        if (this.prodottoDaOrdinare != other.prodottoDaOrdinare) {
            return false;
        }
        return (this.automezzoDaOrdinare != null || other.automezzoDaOrdinare == null) && (this.automezzoDaOrdinare == null || this.automezzoDaOrdinare.equals(other.automezzoDaOrdinare));
    }

    @Override
    public String toString() {
        return "data.PreventivoPK[ pIVAFornitore=" + pIVAFornitore + ", prodottoDaOrdinare=" + prodottoDaOrdinare + ", automezzoDaOrdinare=" + automezzoDaOrdinare + " ]";
    }
    
}
