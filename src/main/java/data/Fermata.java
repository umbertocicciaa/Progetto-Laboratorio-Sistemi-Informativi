/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "Fermata")
@NamedQueries({
    @NamedQuery(name = "Fermata.findAll", query = "SELECT f FROM Fermata f"),
    @NamedQuery(name = "Fermata.findByVia", query = "SELECT f FROM Fermata f WHERE f.fermataPK.via = :via"),
    @NamedQuery(name = "Fermata.findByCitta", query = "SELECT f FROM Fermata f WHERE f.fermataPK.citta = :citta")})
public class Fermata implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FermataPK fermataPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fermata")
    private Collection<ListaFermate> listaFermateCollection;

    public Fermata() {
    }

    public Fermata(FermataPK fermataPK) {
        this.fermataPK = fermataPK;
    }

    public Fermata(String via, String citta) {
        this.fermataPK = new FermataPK(via, citta);
    }

    public FermataPK getFermataPK() {
        return fermataPK;
    }

    public void setFermataPK(FermataPK fermataPK) {
        this.fermataPK = fermataPK;
    }

    public Collection<ListaFermate> getListaFermateCollection() {
        return listaFermateCollection;
    }

    public void setListaFermateCollection(Collection<ListaFermate> listaFermateCollection) {
        this.listaFermateCollection = listaFermateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fermataPK != null ? fermataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fermata)) {
            return false;
        }
        Fermata other = (Fermata) object;
        return (this.fermataPK != null || other.fermataPK == null) && (this.fermataPK == null || this.fermataPK.equals(other.fermataPK));
    }

    @Override
    public String toString() {
        return "data.Fermata[ fermataPK=" + fermataPK + " ]";
    }
    
}
