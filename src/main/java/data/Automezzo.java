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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Automezzo")
@NamedQueries({
    @NamedQuery(name = "Automezzo.findAll", query = "SELECT a FROM Automezzo a"),
    @NamedQuery(name = "Automezzo.findByTarga", query = "SELECT a FROM Automezzo a WHERE a.targa = :targa"),
    @NamedQuery(name = "Automezzo.findByMarca", query = "SELECT a FROM Automezzo a WHERE a.marca = :marca"),
    @NamedQuery(name = "Automezzo.findByAssicurazione", query = "SELECT a FROM Automezzo a WHERE a.assicurazione = :assicurazione"),
    @NamedQuery(name = "Automezzo.findByPrezzo", query = "SELECT a FROM Automezzo a WHERE a.prezzo = :prezzo")})
public class Automezzo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "targa")
    private String targa;
    @Column(name = "marca")
    private String marca;
    @Column(name = "assicurazione")
    private String assicurazione;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prezzo")
    private BigDecimal prezzo;
    @ManyToMany(mappedBy = "automezzoCollection")
    private Collection<Fornitore> fornitoreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "automezzo")
    private Collection<Preventivo> preventivoCollection;
    @OneToMany(mappedBy = "automezzoDaOrdinare")
    private Collection<Ordine> ordineCollection;

    public Automezzo() {
    }

    public Automezzo(String targa) {
        this.targa = targa;
    }

    public Automezzo(String targa, String marca, String assicurazione, BigDecimal prezzo) {
        this.targa = targa;
        this.marca = marca;
        this.assicurazione = assicurazione;
        this.prezzo = prezzo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAssicurazione() {
        return assicurazione;
    }

    public void setAssicurazione(String assicurazione) {
        this.assicurazione = assicurazione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Collection<Fornitore> getFornitoreCollection() {
        return fornitoreCollection;
    }

    public void setFornitoreCollection(Collection<Fornitore> fornitoreCollection) {
        this.fornitoreCollection = fornitoreCollection;
    }

    public Collection<Preventivo> getPreventivoCollection() {
        return preventivoCollection;
    }

    public void setPreventivoCollection(Collection<Preventivo> preventivoCollection) {
        this.preventivoCollection = preventivoCollection;
    }

    public Collection<Ordine> getOrdineCollection() {
        return ordineCollection;
    }

    public void setOrdineCollection(Collection<Ordine> ordineCollection) {
        this.ordineCollection = ordineCollection;
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
        if (!(object instanceof Automezzo)) {
            return false;
        }
        Automezzo other = (Automezzo) object;
        return (this.targa != null || other.targa == null) && (this.targa == null || this.targa.equals(other.targa));
    }

    @Override
    public String toString() {
        return targa;
    }
    
}
