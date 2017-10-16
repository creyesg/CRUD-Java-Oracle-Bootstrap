/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.db12017;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author naore
 */
@Entity
@Table(name = "ESTATUS_PAQUETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusPaquete.findAll", query = "SELECT e FROM EstatusPaquete e")
    , @NamedQuery(name = "EstatusPaquete.findByEstatusPaquete", query = "SELECT e FROM EstatusPaquete e WHERE e.estatusPaquete = :estatusPaquete")
    , @NamedQuery(name = "EstatusPaquete.findByDescriEstatusPaquete", query = "SELECT e FROM EstatusPaquete e WHERE e.descriEstatusPaquete = :descriEstatusPaquete")})
public class EstatusPaquete implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_PAQUETE")
    private BigDecimal estatusPaquete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESCRI_ESTATUS_PAQUETE")
    private String descriEstatusPaquete;
    @JoinColumn(name = "PAQUETE_COD_PAQUETE", referencedColumnName = "COD_PAQUETE")
    @ManyToOne(optional = false)
    private Paquete paqueteCodPaquete;

    public EstatusPaquete() {
    }

    public EstatusPaquete(BigDecimal estatusPaquete) {
        this.estatusPaquete = estatusPaquete;
    }

    public EstatusPaquete(BigDecimal estatusPaquete, String descriEstatusPaquete) {
        this.estatusPaquete = estatusPaquete;
        this.descriEstatusPaquete = descriEstatusPaquete;
    }

    public BigDecimal getEstatusPaquete() {
        return estatusPaquete;
    }

    public void setEstatusPaquete(BigDecimal estatusPaquete) {
        this.estatusPaquete = estatusPaquete;
    }

    public String getDescriEstatusPaquete() {
        return descriEstatusPaquete;
    }

    public void setDescriEstatusPaquete(String descriEstatusPaquete) {
        this.descriEstatusPaquete = descriEstatusPaquete;
    }

    public Paquete getPaqueteCodPaquete() {
        return paqueteCodPaquete;
    }

    public void setPaqueteCodPaquete(Paquete paqueteCodPaquete) {
        this.paqueteCodPaquete = paqueteCodPaquete;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estatusPaquete != null ? estatusPaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusPaquete)) {
            return false;
        }
        EstatusPaquete other = (EstatusPaquete) object;
        if ((this.estatusPaquete == null && other.estatusPaquete != null) || (this.estatusPaquete != null && !this.estatusPaquete.equals(other.estatusPaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EstatusPaquete[ estatusPaquete=" + estatusPaquete + " ]";
    }
    
}
