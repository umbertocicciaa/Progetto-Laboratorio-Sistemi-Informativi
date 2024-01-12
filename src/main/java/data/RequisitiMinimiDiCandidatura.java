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
@Table(name = "RequisitiMinimiDiCandidatura")
@NamedQueries({
    @NamedQuery(name = "RequisitiMinimiDiCandidatura.findAll", query = "SELECT r FROM RequisitiMinimiDiCandidatura r"),
    @NamedQuery(name = "RequisitiMinimiDiCandidatura.findByTitoloDiStudio", query = "SELECT r FROM RequisitiMinimiDiCandidatura r WHERE r.titoloDiStudio = :titoloDiStudio")})
public class RequisitiMinimiDiCandidatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TitoloDiStudio")
    private String titoloDiStudio;

    public RequisitiMinimiDiCandidatura() {
    }

    public RequisitiMinimiDiCandidatura(String titoloDiStudio) {
        this.titoloDiStudio = titoloDiStudio;
    }

    public String getTitoloDiStudio() {
        return titoloDiStudio;
    }

    public void setTitoloDiStudio(String titoloDiStudio) {
        this.titoloDiStudio = titoloDiStudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titoloDiStudio != null ? titoloDiStudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequisitiMinimiDiCandidatura)) {
            return false;
        }
        RequisitiMinimiDiCandidatura other = (RequisitiMinimiDiCandidatura) object;
        return (this.titoloDiStudio != null || other.titoloDiStudio == null) && (this.titoloDiStudio == null || this.titoloDiStudio.equals(other.titoloDiStudio));
    }

    @Override
    public String toString() {
        return "data.RequisitiMinimiDiCandidatura[ titoloDiStudio=" + titoloDiStudio + " ]";
    }
    
}
