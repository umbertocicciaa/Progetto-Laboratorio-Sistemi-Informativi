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
import java.util.Collection;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Prodotto")
@NamedQueries({
    @NamedQuery(name = "Prodotto.findAll", query = "SELECT p FROM Prodotto p"),
    @NamedQuery(name = "Prodotto.findByCodProdotto", query = "SELECT p FROM Prodotto p WHERE p.codProdotto = :codProdotto"),
    @NamedQuery(name = "Prodotto.findByTipo", query = "SELECT p FROM Prodotto p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Prodotto.findByQuantitaNecessaria", query = "SELECT p FROM Prodotto p WHERE p.quantitaNecessaria = :quantitaNecessaria")})
public class Prodotto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CodProdotto")
    private Integer codProdotto;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "quantitaNecessaria")
    private Integer quantitaNecessaria;
    @ManyToMany(mappedBy = "prodottoCollection")
    private Collection<Fornitore> fornitoreCollection;
    @ManyToMany(mappedBy = "prodottoCollection")
    private Collection<Dipartimento> dipartimentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodotto")
    private Collection<Preventivo> preventivoCollection;
    @OneToMany(mappedBy = "prodottoDaOrdinare")
    private Collection<Ordine> ordineCollection;

    public Prodotto() {
    }

    public Prodotto(Integer codProdotto, String tipo, Integer quantitaNecessaria) {
        this.codProdotto = codProdotto;
        this.tipo = tipo;
        this.quantitaNecessaria = quantitaNecessaria;
    }

    public Prodotto(Integer codProdotto) {
        this.codProdotto = codProdotto;
    }

    public Integer getCodProdotto() {
        return codProdotto;
    }

    public void setCodProdotto(Integer codProdotto) {
        this.codProdotto = codProdotto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantitaNecessaria() {
        return quantitaNecessaria;
    }

    public void setQuantitaNecessaria(Integer quantitaNecessaria) {
        this.quantitaNecessaria = quantitaNecessaria;
    }

    public Collection<Fornitore> getFornitoreCollection() {
        return fornitoreCollection;
    }

    public void setFornitoreCollection(Collection<Fornitore> fornitoreCollection) {
        this.fornitoreCollection = fornitoreCollection;
    }

    public Collection<Dipartimento> getDipartimentoCollection() {
        return dipartimentoCollection;
    }

    public void setDipartimentoCollection(Collection<Dipartimento> dipartimentoCollection) {
        this.dipartimentoCollection = dipartimentoCollection;
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
        hash += (codProdotto != null ? codProdotto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prodotto)) {
            return false;
        }
        Prodotto other = (Prodotto) object;
        return (this.codProdotto != null || other.codProdotto == null) && (this.codProdotto == null || this.codProdotto.equals(other.codProdotto));
    }

    @Override
    public String toString() {
        return "data.Prodotto[ codProdotto=" + codProdotto + " ]";
    }
    
}
