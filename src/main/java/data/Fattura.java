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
@Table(name = "Fattura")
@NamedQueries({
    @NamedQuery(name = "Fattura.findAll", query = "SELECT f FROM Fattura f"),
    @NamedQuery(name = "Fattura.findByIDFattura", query = "SELECT f FROM Fattura f WHERE f.iDFattura = :iDFattura"),
    @NamedQuery(name = "Fattura.findByData", query = "SELECT f FROM Fattura f WHERE f.data = :data"),
    @NamedQuery(name = "Fattura.findByOra", query = "SELECT f FROM Fattura f WHERE f.ora = :ora"),
    @NamedQuery(name = "Fattura.findByOggetto", query = "SELECT f FROM Fattura f WHERE f.oggetto = :oggetto"),
    @NamedQuery(name = "Fattura.findByIva", query = "SELECT f FROM Fattura f WHERE f.iva = :iva"),
    @NamedQuery(name = "Fattura.findByMittente", query = "SELECT f FROM Fattura f WHERE f.mittente = :mittente"),
    @NamedQuery(name = "Fattura.findByDestinatario", query = "SELECT f FROM Fattura f WHERE f.destinatario = :destinatario")})
public class Fattura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDFattura")
    private Integer iDFattura;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "ora")
    @Temporal(TemporalType.TIME)
    private Date ora;
    @Column(name = "oggetto")
    private String oggetto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "mittente")
    private String mittente;
    @Column(name = "destinatario")
    private String destinatario;

    public Fattura() {
    }

    public Fattura(Integer iDFattura) {
        this.iDFattura = iDFattura;
    }

    public Integer getIDFattura() {
        return iDFattura;
    }

    public void setIDFattura(Integer iDFattura) {
        this.iDFattura = iDFattura;
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

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDFattura != null ? iDFattura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fattura)) {
            return false;
        }
        Fattura other = (Fattura) object;
        return (this.iDFattura != null || other.iDFattura == null) && (this.iDFattura == null || this.iDFattura.equals(other.iDFattura));
    }

    @Override
    public String toString() {
        return "data.Fattura[ iDFattura=" + iDFattura + " ]";
    }
    
}
