/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Turno")
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByData", query = "SELECT t FROM Turno t WHERE t.turnoPK.data = :data"),
    @NamedQuery(name = "Turno.findByOra", query = "SELECT t FROM Turno t WHERE t.turnoPK.ora = :ora"),
    @NamedQuery(name = "Turno.findByDipendente", query = "SELECT t FROM Turno t WHERE t.turnoPK.dipendente = :dipendente")})
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TurnoPK turnoPK;
    @JoinColumn(name = "Dipendente", referencedColumnName = "codiceFiscale", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dipendente dipendente1;

    public Turno() {
    }

    public Turno(TurnoPK turnoPK) {
        this.turnoPK = turnoPK;
    }

    public Turno(Date data, Date ora, String dipendente) {
        this.turnoPK = new TurnoPK(data, ora, dipendente);
    }

    public TurnoPK getTurnoPK() {
        return turnoPK;
    }

    public void setTurnoPK(TurnoPK turnoPK) {
        this.turnoPK = turnoPK;
    }

    public Dipendente getDipendente1() {
        return dipendente1;
    }

    public void setDipendente1(Dipendente dipendente1) {
        this.dipendente1 = dipendente1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnoPK != null ? turnoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        return (this.turnoPK != null || other.turnoPK == null) && (this.turnoPK == null || this.turnoPK.equals(other.turnoPK));
    }

    @Override
    public String toString() {
        return "data.Turno[ turnoPK=" + turnoPK + " ]";
    }
    
}
