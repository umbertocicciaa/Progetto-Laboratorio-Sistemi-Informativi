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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author umbertodomenicociccia
 */
@Entity
@Table(name = "Dipendente")
@NamedQueries({
    @NamedQuery(name = "Dipendente.findAll", query = "SELECT d FROM Dipendente d"),
    @NamedQuery(name = "Dipendente.findByCodiceFiscale", query = "SELECT d FROM Dipendente d WHERE d.codiceFiscale = :codiceFiscale"),
    @NamedQuery(name = "Dipendente.findByNome", query = "SELECT d FROM Dipendente d WHERE d.nome = :nome"),
    @NamedQuery(name = "Dipendente.findByCognome", query = "SELECT d FROM Dipendente d WHERE d.cognome = :cognome"),
    @NamedQuery(name = "Dipendente.findByDataN", query = "SELECT d FROM Dipendente d WHERE d.dataN = :dataN"),
    @NamedQuery(name = "Dipendente.findBySalario", query = "SELECT d FROM Dipendente d WHERE d.salario = :salario")})
public class Dipendente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codiceFiscale")
    private String codiceFiscale;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "cognome")
    private String cognome;
    @Basic(optional = false)
    @Column(name = "dataN")
    @Temporal(TemporalType.DATE)
    private Date dataN;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "salario")
    private BigDecimal salario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dipendente1")
    private Collection<Turno> turnoCollection;
    @JoinColumn(name = "Dipartimento", referencedColumnName = "codice")
    @ManyToOne
    private Dipartimento dipartimento;
    @JoinColumn(name = "Ruolo", referencedColumnName = "IDRuolo")
    @ManyToOne
    private Ruolo ruolo;

    public Dipendente() {
    }

    public Dipendente(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Dipendente(String codiceFiscale, String nome, String cognome, Date dataN, BigDecimal salario) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataN = dataN;
        this.salario = salario;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataN() {
        return dataN;
    }

    public void setDataN(Date dataN) {
        this.dataN = dataN;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Collection<Turno> getTurnoCollection() {
        return turnoCollection;
    }

    public void setTurnoCollection(Collection<Turno> turnoCollection) {
        this.turnoCollection = turnoCollection;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiceFiscale != null ? codiceFiscale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dipendente)) {
            return false;
        }
        Dipendente other = (Dipendente) object;
        return (this.codiceFiscale != null || other.codiceFiscale == null) && (this.codiceFiscale == null || this.codiceFiscale.equals(other.codiceFiscale));
    }

    @Override
    public String toString() {
        return "data.Dipendente[ codiceFiscale=" + codiceFiscale + " ]";
    }
    
}
