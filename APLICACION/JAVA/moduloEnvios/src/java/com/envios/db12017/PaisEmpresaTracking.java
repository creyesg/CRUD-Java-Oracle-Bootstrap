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
@Table(name = "PAIS_EMPRESA_TRACKING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaisEmpresaTracking.findAll", query = "SELECT p FROM PaisEmpresaTracking p")
    , @NamedQuery(name = "PaisEmpresaTracking.findByCodPaisTracking", query = "SELECT p FROM PaisEmpresaTracking p WHERE p.codPaisTracking = :codPaisTracking")
    , @NamedQuery(name = "PaisEmpresaTracking.findByDescripcionPais", query = "SELECT p FROM PaisEmpresaTracking p WHERE p.descripcionPais = :descripcionPais")})
public class PaisEmpresaTracking implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAIS_TRACKING")
    private BigDecimal codPaisTracking;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_PAIS")
    private String descripcionPais;
    @JoinColumn(name = "TRACKING_COD_TRACKING", referencedColumnName = "COD_TRACKING")
    @ManyToOne(optional = false)
    private Tracking trackingCodTracking;

    public PaisEmpresaTracking() {
    }

    public PaisEmpresaTracking(BigDecimal codPaisTracking) {
        this.codPaisTracking = codPaisTracking;
    }

    public BigDecimal getCodPaisTracking() {
        return codPaisTracking;
    }

    public void setCodPaisTracking(BigDecimal codPaisTracking) {
        this.codPaisTracking = codPaisTracking;
    }

    public String getDescripcionPais() {
        return descripcionPais;
    }

    public void setDescripcionPais(String descripcionPais) {
        this.descripcionPais = descripcionPais;
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
        hash += (codPaisTracking != null ? codPaisTracking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaisEmpresaTracking)) {
            return false;
        }
        PaisEmpresaTracking other = (PaisEmpresaTracking) object;
        if ((this.codPaisTracking == null && other.codPaisTracking != null) || (this.codPaisTracking != null && !this.codPaisTracking.equals(other.codPaisTracking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.PaisEmpresaTracking[ codPaisTracking=" + codPaisTracking + " ]";
    }
    
}
