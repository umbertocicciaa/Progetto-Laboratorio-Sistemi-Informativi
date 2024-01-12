/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "RichiestaCliente")
@NamedQueries({
        @NamedQuery(name = "RichiestaCliente.findAll", query = "SELECT r FROM RichiestaCliente r"),
        @NamedQuery(name = "RichiestaCliente.findByIDRichiesta", query = "SELECT r FROM RichiestaCliente r WHERE r.iDRichiesta = :iDRichiesta"),
        @NamedQuery(name = "RichiestaCliente.findByData", query = "SELECT r FROM RichiestaCliente r WHERE r.data = :data"),
        @NamedQuery(name = "RichiestaCliente.findByStato", query = "SELECT r FROM RichiestaCliente r WHERE r.stato = :stato"),
        @NamedQuery(name = "RichiestaCliente.findByTipo", query = "SELECT r FROM RichiestaCliente r WHERE r.tipo = :tipo")})
public class RichiestaCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDRichiesta")
    private Integer iDRichiesta;
    @Lob
    @Column(name = "Contenuto")
    private String contenuto;
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "Stato")
    private String stato;
    @Column(name = "Tipo")
    private String tipo;
    @JoinColumn(name = "CFCliente", referencedColumnName = "CF")
    @ManyToOne
    private Cliente cFCliente;

    public RichiestaCliente() {
    }

    public RichiestaCliente(Integer iDRichiesta, String contenuto, Date data, String stato, String tipo, Cliente cFCliente) {
        if (!tipo.equalsIgnoreCase("feedback") && !tipo.equalsIgnoreCase("reclamo"))
            throw new IllegalArgumentException("Il tipo deve essere deeback o reclamo");
        if (!stato.equalsIgnoreCase("aperto") && !stato.equalsIgnoreCase("chiuso") && !stato.equalsIgnoreCase("lavorazione"))
            throw new IllegalArgumentException("Lo stato deve essere aperto o chiuso o lavorazione");
        this.iDRichiesta = iDRichiesta;
        this.contenuto = contenuto;
        this.data = data;
        this.stato = stato;
        this.tipo = tipo;
        this.cFCliente = cFCliente;
    }

    public RichiestaCliente(Integer iDRichiesta) {
        this.iDRichiesta = iDRichiesta;
    }

    public Integer getIDRichiesta() {
        return iDRichiesta;
    }

    public void setIDRichiesta(Integer iDRichiesta) {
        this.iDRichiesta = iDRichiesta;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        if (!stato.equalsIgnoreCase("aperto") && !stato.equalsIgnoreCase("chiuso") && !stato.equalsIgnoreCase("lavorazione"))
            throw new IllegalArgumentException("Lo stato deve essere aperto o chiuso o lavorazione");
        this.stato = stato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCFCliente() {
        return cFCliente;
    }

    public void setCFCliente(Cliente cFCliente) {
        this.cFCliente = cFCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDRichiesta != null ? iDRichiesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RichiestaCliente)) {
            return false;
        }
        RichiestaCliente other = (RichiestaCliente) object;
        return (this.iDRichiesta != null || other.iDRichiesta == null) && (this.iDRichiesta == null || this.iDRichiesta.equals(other.iDRichiesta));
    }

    @Override
    public String toString() {
        return "data.RichiestaCliente[ iDRichiesta=" + iDRichiesta + " ]";
    }

}
