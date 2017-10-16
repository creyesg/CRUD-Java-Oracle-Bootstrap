/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.db12017;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author naore
 */
@Entity
@Table(name = "TRACKING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tracking.findAll", query = "SELECT t FROM Tracking t")
    , @NamedQuery(name = "Tracking.findByCodTracking", query = "SELECT t FROM Tracking t WHERE t.codTracking = :codTracking")
    , @NamedQuery(name = "Tracking.findByCodEmpresaTracking", query = "SELECT t FROM Tracking t WHERE t.codEmpresaTracking = :codEmpresaTracking")
    , @NamedQuery(name = "Tracking.findByCodPaisTracking", query = "SELECT t FROM Tracking t WHERE t.codPaisTracking = :codPaisTracking")
    , @NamedQuery(name = "Tracking.findByCodEstatusTracking", query = "SELECT t FROM Tracking t WHERE t.codEstatusTracking = :codEstatusTracking")
    , @NamedQuery(name = "Tracking.findByNumeroHojaDespacho", query = "SELECT t FROM Tracking t WHERE t.numeroHojaDespacho = :numeroHojaDespacho")})
public class Tracking implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_TRACKING")
    private BigDecimal codTracking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMPRESA_TRACKING")
    private BigInteger codEmpresaTracking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAIS_TRACKING")
    private BigInteger codPaisTracking;
    @Column(name = "COD_ESTATUS_TRACKING")
    private BigInteger codEstatusTracking;
    @Column(name = "NUMERO_HOJA_DESPACHO")
    private BigInteger numeroHojaDespacho;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackingCodTracking")
    private Collection<PaisEmpresaTracking> paisEmpresaTrackingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackingCodTracking")
    private Collection<EstatusTracking> estatusTrackingCollection;
    @JoinColumns({
        @JoinColumn(name = "ENVIOS_NUMERO_HOJA_DESPACHO", referencedColumnName = "NUMERO_HOJA_DESPACHO")
        , @JoinColumn(name = "ENVIOS_COD_ENTREGA", referencedColumnName = "COD_ENTREGA")})
    @ManyToOne(optional = false)
    private Envios envios;

    public Tracking() {
    }

    public Tracking(BigDecimal codTracking) {
        this.codTracking = codTracking;
    }

    public Tracking(BigDecimal codTracking, BigInteger codEmpresaTracking, BigInteger codPaisTracking) {
        this.codTracking = codTracking;
        this.codEmpresaTracking = codEmpresaTracking;
        this.codPaisTracking = codPaisTracking;
    }

    public BigDecimal getCodTracking() {
        return codTracking;
    }

    public void setCodTracking(BigDecimal codTracking) {
        this.codTracking = codTracking;
    }

    public BigInteger getCodEmpresaTracking() {
        return codEmpresaTracking;
    }

    public void setCodEmpresaTracking(BigInteger codEmpresaTracking) {
        this.codEmpresaTracking = codEmpresaTracking;
    }

    public BigInteger getCodPaisTracking() {
        return codPaisTracking;
    }

    public void setCodPaisTracking(BigInteger codPaisTracking) {
        this.codPaisTracking = codPaisTracking;
    }

    public BigInteger getCodEstatusTracking() {
        return codEstatusTracking;
    }

    public void setCodEstatusTracking(BigInteger codEstatusTracking) {
        this.codEstatusTracking = codEstatusTracking;
    }

    public BigInteger getNumeroHojaDespacho() {
        return numeroHojaDespacho;
    }

    public void setNumeroHojaDespacho(BigInteger numeroHojaDespacho) {
        this.numeroHojaDespacho = numeroHojaDespacho;
    }

    @XmlTransient
    public Collection<PaisEmpresaTracking> getPaisEmpresaTrackingCollection() {
        return paisEmpresaTrackingCollection;
    }

    public void setPaisEmpresaTrackingCollection(Collection<PaisEmpresaTracking> paisEmpresaTrackingCollection) {
        this.paisEmpresaTrackingCollection = paisEmpresaTrackingCollection;
    }

    @XmlTransient
    public Collection<EstatusTracking> getEstatusTrackingCollection() {
        return estatusTrackingCollection;
    }

    public void setEstatusTrackingCollection(Collection<EstatusTracking> estatusTrackingCollection) {
        this.estatusTrackingCollection = estatusTrackingCollection;
    }

    public Envios getEnvios() {
        return envios;
    }

    public void setEnvios(Envios envios) {
        this.envios = envios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTracking != null ? codTracking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tracking)) {
            return false;
        }
        Tracking other = (Tracking) object;
        if ((this.codTracking == null && other.codTracking != null) || (this.codTracking != null && !this.codTracking.equals(other.codTracking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Tracking[ codTracking=" + codTracking + " ]";
    }
    
}
