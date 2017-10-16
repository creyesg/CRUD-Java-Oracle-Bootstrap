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
@Table(name = "ESTATUS_TRACKING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusTracking.findAll", query = "SELECT e FROM EstatusTracking e")
    , @NamedQuery(name = "EstatusTracking.findByCodEstatusTracking", query = "SELECT e FROM EstatusTracking e WHERE e.codEstatusTracking = :codEstatusTracking")
    , @NamedQuery(name = "EstatusTracking.findByDescriEstatusTracking", query = "SELECT e FROM EstatusTracking e WHERE e.descriEstatusTracking = :descriEstatusTracking")})
public class EstatusTracking implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ESTATUS_TRACKING")
    private BigDecimal codEstatusTracking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DESCRI_ESTATUS_TRACKING")
    private String descriEstatusTracking;
    @JoinColumn(name = "TRACKING_COD_TRACKING", referencedColumnName = "COD_TRACKING")
    @ManyToOne(optional = false)
    private Tracking trackingCodTracking;

    public EstatusTracking() {
    }

    public EstatusTracking(BigDecimal codEstatusTracking) {
        this.codEstatusTracking = codEstatusTracking;
    }

    public EstatusTracking(BigDecimal codEstatusTracking, String descriEstatusTracking) {
        this.codEstatusTracking = codEstatusTracking;
        this.descriEstatusTracking = descriEstatusTracking;
    }

    public BigDecimal getCodEstatusTracking() {
        return codEstatusTracking;
    }

    public void setCodEstatusTracking(BigDecimal codEstatusTracking) {
        this.codEstatusTracking = codEstatusTracking;
    }

    public String getDescriEstatusTracking() {
        return descriEstatusTracking;
    }

    public void setDescriEstatusTracking(String descriEstatusTracking) {
        this.descriEstatusTracking = descriEstatusTracking;
    }

    public Tracking getTrackingCodTracking() {
        return trackingCodTracking;
    }

    public void setTrackingCodTracking(Tracking trackingCodTracking) {
        this.trackingCodTracking = trackingCodTracking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstatusTracking != null ? codEstatusTracking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusTracking)) {
            return false;
        }
        EstatusTracking other = (EstatusTracking) object;
        if ((this.codEstatusTracking == null && other.codEstatusTracking != null) || (this.codEstatusTracking != null && !this.codEstatusTracking.equals(other.codEstatusTracking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EstatusTracking[ codEstatusTracking=" + codEstatusTracking + " ]";
    }
    
}
