/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Autobus")
@NamedQueries({
    @NamedQuery(name = "Autobus.findAll", query = "SELECT a FROM Autobus a"),
    @NamedQuery(name = "Autobus.findByTarga", query = "SELECT a FROM Autobus a WHERE a.targa = :targa")})
public class Autobus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "targa")
    private String targa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autobus1")
    private Collection<Corsa> corsaCollection;

    public Autobus() {
    }

    public Autobus(String targa) {
        this.targa = targa;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Collection<Corsa> getCorsaCollection() {
        return corsaCollection;
    }

    public void setCorsaCollection(Collection<Corsa> corsaCollection) {
        this.corsaCollection = corsaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (targa != null ? targa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autobus)) {
            return false;
        }
        Autobus other = (Autobus) object;
        return (this.targa != null || other.targa == null) && (this.targa == null || this.targa.equals(other.targa));
    }

    @Override
    public String toString() {
        return "data.Autobus[ targa=" + targa + " ]";
    }
    
}
