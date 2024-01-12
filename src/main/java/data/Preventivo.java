/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "Preventivo")
@NamedQueries({
    @NamedQuery(name = "Preventivo.findAll", query = "SELECT p FROM Preventivo p"),
    @NamedQuery(name = "Preventivo.findByPIVAFornitore", query = "SELECT p FROM Preventivo p WHERE p.preventivoPK.pIVAFornitore = :pIVAFornitore"),
    @NamedQuery(name = "Preventivo.findByProdottoDaOrdinare", query = "SELECT p FROM Preventivo p WHERE p.preventivoPK.prodottoDaOrdinare = :prodottoDaOrdinare"),
    @NamedQuery(name = "Preventivo.findByAutomezzoDaOrdinare", query = "SELECT p FROM Preventivo p WHERE p.preventivoPK.automezzoDaOrdinare = :automezzoDaOrdinare"),
    @NamedQuery(name = "Preventivo.findByPrezzo", query = "SELECT p FROM Preventivo p WHERE p.prezzo = :prezzo"),
    @NamedQuery(name = "Preventivo.findByDataScadenza", query = "SELECT p FROM Preventivo p WHERE p.dataScadenza = :dataScadenza"),
    @NamedQuery(name = "Preventivo.findByDataScrittura", query = "SELECT p FROM Preventivo p WHERE p.dataScrittura = :dataScrittura")})
public class Preventivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreventivoPK preventivoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prezzo")
    private BigDecimal prezzo;
    @Column(name = "dataScadenza")
    @Temporal(TemporalType.DATE)
    private Date dataScadenza;
    @Column(name = "dataScrittura")
    @Temporal(TemporalType.DATE)
    private Date dataScrittura;
    @JoinColumn(name = "AutomezzoDaOrdinare", referencedColumnName = "targa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Automezzo automezzo;
    @JoinColumn(name = "PIVAFornitore", referencedColumnName = "PIVA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fornitore fornitore;
    @JoinColumn(name = "ProdottoDaOrdinare", referencedColumnName = "CodProdotto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prodotto prodotto;

    public Preventivo() {
    }

    public Preventivo(PreventivoPK preventivoPK) {
        this.preventivoPK = preventivoPK;
    }

    public Preventivo(String pIVAFornitore, int prodottoDaOrdinare, String automezzoDaOrdinare) {
        this.preventivoPK = new PreventivoPK(pIVAFornitore, prodottoDaOrdinare, automezzoDaOrdinare);
    }

    public PreventivoPK getPreventivoPK() {
        return preventivoPK;
    }

    public void setPreventivoPK(PreventivoPK preventivoPK) {
        this.preventivoPK = preventivoPK;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Date getDataScrittura() {
        return dataScrittura;
    }

    public void setDataScrittura(Date dataScrittura) {
        this.dataScrittura = dataScrittura;
    }

    public Automezzo getAutomezzo() {
        return automezzo;
    }

    public void setAutomezzo(Automezzo automezzo) {
        this.automezzo = automezzo;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preventivoPK != null ? preventivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preventivo)) {
            return false;
        }
        Preventivo other = (Preventivo) object;
        return (this.preventivoPK != null || other.preventivoPK == null) && (this.preventivoPK == null || this.preventivoPK.equals(other.preventivoPK));
    }

    @Override
    public String toString() {
        return "data.Preventivo[ preventivoPK=" + preventivoPK + " ]";
    }
    
}
