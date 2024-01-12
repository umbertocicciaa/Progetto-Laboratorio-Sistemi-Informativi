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
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Bene")
@NamedQueries({
    @NamedQuery(name = "Bene.findAll", query = "SELECT b FROM Bene b"),
    @NamedQuery(name = "Bene.findById", query = "SELECT b FROM Bene b WHERE b.id = :id"),
    @NamedQuery(name = "Bene.findByDescrizione", query = "SELECT b FROM Bene b WHERE b.descrizione = :descrizione")})
public class Bene implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Descrizione")
    private String descrizione;

    public Bene() {
    }

    public Bene(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bene)) {
            return false;
        }
        Bene other = (Bene) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "data.Bene[ id=" + id + " ]";
    }
    
}
