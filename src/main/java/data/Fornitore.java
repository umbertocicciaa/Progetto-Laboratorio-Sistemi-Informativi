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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "Fornitore")
@NamedQueries({
    @NamedQuery(name = "Fornitore.findAll", query = "SELECT f FROM Fornitore f"),
    @NamedQuery(name = "Fornitore.findByPiva", query = "SELECT f FROM Fornitore f WHERE f.piva = :piva"),
    @NamedQuery(name = "Fornitore.findByNome", query = "SELECT f FROM Fornitore f WHERE f.nome = :nome"),
    @NamedQuery(name = "Fornitore.findByCitta", query = "SELECT f FROM Fornitore f WHERE f.citta = :citta")})
public class Fornitore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PIVA")
    private String piva;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "citta")
    private String citta;
    @JoinTable(name = "ListaProdotti", joinColumns = {
        @JoinColumn(name = "Fornitore", referencedColumnName = "PIVA")}, inverseJoinColumns = {
        @JoinColumn(name = "Prodotto", referencedColumnName = "CodProdotto")})
    @ManyToMany
    private Collection<Prodotto> prodottoCollection;
    @JoinTable(name = "ListaAutomezzi", joinColumns = {
        @JoinColumn(name = "Fornitore", referencedColumnName = "PIVA")}, inverseJoinColumns = {
        @JoinColumn(name = "Automezzo", referencedColumnName = "targa")})
    @ManyToMany
    private Collection<Automezzo> automezzoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornitore")
    private Collection<Preventivo> preventivoCollection;

    public Fornitore() {
    }

    public Fornitore(String piva) {
        this.piva = piva;
    }

    public Fornitore(String piva, String nome, String citta) {
        this.piva = piva;
        this.nome = nome;
        this.citta = citta;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Collection<Prodotto> getProdottoCollection() {
        return prodottoCollection;
    }

    public void setProdottoCollection(Collection<Prodotto> prodottoCollection) {
        this.prodottoCollection = prodottoCollection;
    }

    public Collection<Automezzo> getAutomezzoCollection() {
        return automezzoCollection;
    }

    public void setAutomezzoCollection(Collection<Automezzo> automezzoCollection) {
        this.automezzoCollection = automezzoCollection;
    }

    public Collection<Preventivo> getPreventivoCollection() {
        return preventivoCollection;
    }

    public void setPreventivoCollection(Collection<Preventivo> preventivoCollection) {
        this.preventivoCollection = preventivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piva != null ? piva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornitore)) {
            return false;
        }
        Fornitore other = (Fornitore) object;
        return (this.piva != null || other.piva == null) && (this.piva == null || this.piva.equals(other.piva));
    }

    @Override
    public String toString() {
        return "data.Fornitore[ piva=" + piva + " ]";
    }
    
}
