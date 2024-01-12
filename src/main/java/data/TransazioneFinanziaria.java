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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "TransazioneFinanziaria")
@NamedQueries({
    @NamedQuery(name = "TransazioneFinanziaria.findAll", query = "SELECT t FROM TransazioneFinanziaria t"),
    @NamedQuery(name = "TransazioneFinanziaria.findById", query = "SELECT t FROM TransazioneFinanziaria t WHERE t.id = :id"),
    @NamedQuery(name = "TransazioneFinanziaria.findByImporto", query = "SELECT t FROM TransazioneFinanziaria t WHERE t.importo = :importo"),
    @NamedQuery(name = "TransazioneFinanziaria.findByTipo", query = "SELECT t FROM TransazioneFinanziaria t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TransazioneFinanziaria.findByData", query = "SELECT t FROM TransazioneFinanziaria t WHERE t.data = :data"),
    @NamedQuery(name = "TransazioneFinanziaria.findByOra", query = "SELECT t FROM TransazioneFinanziaria t WHERE t.ora = :ora")})
public class TransazioneFinanziaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importo")
    private BigDecimal importo;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "ora")
    @Temporal(TemporalType.TIME)
    private Date ora;
    @Lob
    @Column(name = "descrizione")
    private String descrizione;
    @JoinColumn(name = "conto", referencedColumnName = "IBAN")
    @ManyToOne
    private Conto conto;
    @JoinColumn(name = "libro", referencedColumnName = "IDLibro")
    @ManyToOne
    private Libro libro;

    public TransazioneFinanziaria() {
    }

    public TransazioneFinanziaria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getOra() {
        return ora;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
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
        if (!(object instanceof TransazioneFinanziaria)) {
            return false;
        }
        TransazioneFinanziaria other = (TransazioneFinanziaria) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "data.TransazioneFinanziaria[ id=" + id + " ]";
    }
    
}
