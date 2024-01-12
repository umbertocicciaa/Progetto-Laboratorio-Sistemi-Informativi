/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author umbertodomenicociccia
 */
@Embeddable
public class TurnoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "ora")
    @Temporal(TemporalType.TIME)
    private Date ora;
    @Basic(optional = false)
    @Column(name = "Dipendente")
    private String dipendente;

    public TurnoPK() {
    }

    public TurnoPK(Date data, Date ora, String dipendente) {
        this.data = data;
        this.ora = ora;
        this.dipendente = dipendente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getOra() {
        return ora;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public String getDipendente() {
        return dipendente;
    }

    public void setDipendente(String dipendente) {
        this.dipendente = dipendente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (data != null ? data.hashCode() : 0);
        hash += (ora != null ? ora.hashCode() : 0);
        hash += (dipendente != null ? dipendente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoPK)) {
            return false;
        }
        TurnoPK other = (TurnoPK) object;
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        if ((this.ora == null && other.ora != null) || (this.ora != null && !this.ora.equals(other.ora))) {
            return false;
        }
        return (this.dipendente != null || other.dipendente == null) && (this.dipendente == null || this.dipendente.equals(other.dipendente));
    }

    @Override
    public String toString() {
        return "data.TurnoPK[ data=" + data + ", ora=" + ora + ", dipendente=" + dipendente + " ]";
    }
    
}
