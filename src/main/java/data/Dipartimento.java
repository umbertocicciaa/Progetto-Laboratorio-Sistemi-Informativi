/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
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
@Table(name = "Dipartimento")
@NamedQueries({
    @NamedQuery(name = "Dipartimento.findAll", query = "SELECT d FROM Dipartimento d"),
    @NamedQuery(name = "Dipartimento.findByCodice", query = "SELECT d FROM Dipartimento d WHERE d.codice = :codice"),
    @NamedQuery(name = "Dipartimento.findByNome", query = "SELECT d FROM Dipartimento d WHERE d.nome = :nome")})
public class Dipartimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codice")
    private Integer codice;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @JoinTable(name = "RichiestoDa", joinColumns = {
        @JoinColumn(name = "Dipartimento", referencedColumnName = "codice")}, inverseJoinColumns = {
        @JoinColumn(name = "Prodotto", referencedColumnName = "CodProdotto")})
    @ManyToMany
    private Collection<Prodotto> prodottoCollection;
    @OneToMany(mappedBy = "dipartimento")
    private Collection<Dipendente> dipendenteCollection;

    public Dipartimento() {
    }

    public Dipartimento(Integer codice) {
        this.codice = codice;
    }

    public Dipartimento(Integer codice, String nome) {
        this.codice = codice;
        this.nome = nome;
    }

    public Integer getCodice() {
        return codice;
    }

    public void setCodice(Integer codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Prodotto> getProdottoCollection() {
        return prodottoCollection;
    }

    public void setProdottoCollection(Collection<Prodotto> prodottoCollection) {
        this.prodottoCollection = prodottoCollection;
    }

    public Collection<Dipendente> getDipendenteCollection() {
        return dipendenteCollection;
    }

    public void setDipendenteCollection(Collection<Dipendente> dipendenteCollection) {
        this.dipendenteCollection = dipendenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codice != null ? codice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dipartimento)) {
            return false;
        }
        Dipartimento other = (Dipartimento) object;
        return (this.codice != null || other.codice == null) && (this.codice == null || this.codice.equals(other.codice));
    }

    @Override
    public String toString() {
        return "data.Dipartimento[ codice=" + codice + " ]";
    }
    
}
