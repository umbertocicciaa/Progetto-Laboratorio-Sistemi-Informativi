/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Corsa")
@NamedQueries({
    @NamedQuery(name = "Corsa.findAll", query = "SELECT c FROM Corsa c"),
    @NamedQuery(name = "Corsa.findByIDCorsa", query = "SELECT c FROM Corsa c WHERE c.corsaPK.iDCorsa = :iDCorsa"),
    @NamedQuery(name = "Corsa.findByDataCorsa", query = "SELECT c FROM Corsa c WHERE c.dataCorsa = :dataCorsa"),
    @NamedQuery(name = "Corsa.findByAutobus", query = "SELECT c FROM Corsa c WHERE c.corsaPK.autobus = :autobus")})
public class Corsa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CorsaPK corsaPK;
    @Basic(optional = false)
    @Column(name = "DataCorsa")
    @Temporal(TemporalType.DATE)
    private Date dataCorsa;
    @JoinColumn(name = "Autobus", referencedColumnName = "targa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autobus autobus1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corsa")
    private Collection<ListaFermate> listaFermateCollection;

    public Corsa() {
    }

    public Corsa(CorsaPK corsaPK) {
        this.corsaPK = corsaPK;
    }

    public Corsa(CorsaPK corsaPK, Date dataCorsa, Autobus autobus1) {
        this.corsaPK = corsaPK;
        this.dataCorsa = dataCorsa;
        this.autobus1 = autobus1;
    }

    public Corsa(CorsaPK corsaPK, Date dataCorsa) {
        this.corsaPK = corsaPK;
        this.dataCorsa = dataCorsa;
    }

    public Corsa(int iDCorsa, String autobus) {
        this.corsaPK = new CorsaPK(iDCorsa, autobus);
    }

    public CorsaPK getCorsaPK() {
        return corsaPK;
    }

    public void setCorsaPK(CorsaPK corsaPK) {
        this.corsaPK = corsaPK;
    }

    public Date getDataCorsa() {
        return dataCorsa;
    }

    public void setDataCorsa(Date dataCorsa) {
        this.dataCorsa = dataCorsa;
    }

    public Autobus getAutobus1() {
        return autobus1;
    }

    public void setAutobus1(Autobus autobus1) {
        this.autobus1 = autobus1;
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
        hash += (corsaPK != null ? corsaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corsa)) {
            return false;
        }
        Corsa other = (Corsa) object;
        return (this.corsaPK != null || other.corsaPK == null) && (this.corsaPK == null || this.corsaPK.equals(other.corsaPK));
    }

    @Override
    public String toString() {
        return "data.Corsa[ corsaPK=" + corsaPK + " ]";
    }
    
}
