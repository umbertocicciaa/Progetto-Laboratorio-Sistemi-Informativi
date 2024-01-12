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
@Table(name = "Libro")
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.findByIDLibro", query = "SELECT l FROM Libro l WHERE l.iDLibro = :iDLibro"),
    @NamedQuery(name = "Libro.findByTipo", query = "SELECT l FROM Libro l WHERE l.tipo = :tipo")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDLibro")
    private Integer iDLibro;
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(mappedBy = "libro")
    private Collection<TransazioneFinanziaria> transazioneFinanziariaCollection;

    public Libro() {
    }

    public Libro(Integer iDLibro) {
        this.iDLibro = iDLibro;
    }

    public Integer getIDLibro() {
        return iDLibro;
    }

    public void setIDLibro(Integer iDLibro) {
        this.iDLibro = iDLibro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<TransazioneFinanziaria> getTransazioneFinanziariaCollection() {
        return transazioneFinanziariaCollection;
    }

    public void setTransazioneFinanziariaCollection(Collection<TransazioneFinanziaria> transazioneFinanziariaCollection) {
        this.transazioneFinanziariaCollection = transazioneFinanziariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLibro != null ? iDLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        return (this.iDLibro != null || other.iDLibro == null) && (this.iDLibro == null || this.iDLibro.equals(other.iDLibro));
    }

    @Override
    public String toString() {
        return "data.Libro[ iDLibro=" + iDLibro + " ]";
    }
    
}
