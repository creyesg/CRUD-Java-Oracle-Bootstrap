/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.db12017;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "EMPRESA_TRACKING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaTracking.findAll", query = "SELECT e FROM EmpresaTracking e")
    , @NamedQuery(name = "EmpresaTracking.findByCodEmpresaTracking", query = "SELECT e FROM EmpresaTracking e WHERE e.codEmpresaTracking = :codEmpresaTracking")
    , @NamedQuery(name = "EmpresaTracking.findByNombreEmpresaTracking", query = "SELECT e FROM EmpresaTracking e WHERE e.nombreEmpresaTracking = :nombreEmpresaTracking")
    , @NamedQuery(name = "EmpresaTracking.findByTrackingCodTracking", query = "SELECT e FROM EmpresaTracking e WHERE e.trackingCodTracking = :trackingCodTracking")})
public class EmpresaTracking implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMPRESA_TRACKING")
    private BigDecimal codEmpresaTracking;
    @Size(max = 200)
    @Column(name = "NOMBRE_EMPRESA_TRACKING")
    private String nombreEmpresaTracking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRACKING_COD_TRACKING")
    private BigInteger trackingCodTracking;

    public EmpresaTracking() {
    }

    public EmpresaTracking(BigDecimal codEmpresaTracking) {
        this.codEmpresaTracking = codEmpresaTracking;
    }

    public EmpresaTracking(BigDecimal codEmpresaTracking, BigInteger trackingCodTracking) {
        this.codEmpresaTracking = codEmpresaTracking;
        this.trackingCodTracking = trackingCodTracking;
    }

    public BigDecimal getCodEmpresaTracking() {
        return codEmpresaTracking;
    }

    public void setCodEmpresaTracking(BigDecimal codEmpresaTracking) {
        this.codEmpresaTracking = codEmpresaTracking;
    }

    public String getNombreEmpresaTracking() {
        return nombreEmpresaTracking;
    }

    public void setNombreEmpresaTracking(String nombreEmpresaTracking) {
        this.nombreEmpresaTracking = nombreEmpresaTracking;
    }

    public BigInteger getTrackingCodTracking() {
        return trackingCodTracking;
    }

    public void setTrackingCodTracking(BigInteger trackingCodTracking) {
        this.trackingCodTracking = trackingCodTracking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEmpresaTracking != null ? codEmpresaTracking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaTracking)) {
            return false;
        }
        EmpresaTracking other = (EmpresaTracking) object;
        if ((this.codEmpresaTracking == null && other.codEmpresaTracking != null) || (this.codEmpresaTracking != null && !this.codEmpresaTracking.equals(other.codEmpresaTracking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EmpresaTracking[ codEmpresaTracking=" + codEmpresaTracking + " ]";
    }
    
}
