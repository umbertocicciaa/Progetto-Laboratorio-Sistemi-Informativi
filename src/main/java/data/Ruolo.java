/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Basic;
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
@Table(name = "Ruolo")
@NamedQueries({
    @NamedQuery(name = "Ruolo.findAll", query = "SELECT r FROM Ruolo r"),
    @NamedQuery(name = "Ruolo.findByIDRuolo", query = "SELECT r FROM Ruolo r WHERE r.iDRuolo = :iDRuolo"),
    @NamedQuery(name = "Ruolo.findByNome", query = "SELECT r FROM Ruolo r WHERE r.nome = :nome")})
public class Ruolo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDRuolo")
    private Integer iDRuolo;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "ruolo")
    private Collection<Dipendente> dipendenteCollection;

    public Ruolo() {
    }

    public Ruolo(Integer iDRuolo) {
        this.iDRuolo = iDRuolo;
    }

    public Ruolo(Integer iDRuolo, String nome) {
        this.iDRuolo = iDRuolo;
        this.nome = nome;
    }

    public Integer getIDRuolo() {
        return iDRuolo;
    }

    public void setIDRuolo(Integer iDRuolo) {
        this.iDRuolo = iDRuolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        hash += (iDRuolo != null ? iDRuolo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruolo)) {
            return false;
        }
        Ruolo other = (Ruolo) object;
        return (this.iDRuolo != null || other.iDRuolo == null) && (this.iDRuolo == null || this.iDRuolo.equals(other.iDRuolo));
    }

    @Override
    public String toString() {
        return "data.Ruolo[ iDRuolo=" + iDRuolo + " ]";
    }
    
}
