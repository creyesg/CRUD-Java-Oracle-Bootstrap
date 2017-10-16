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
@Table(name = "VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
    , @NamedQuery(name = "Vehiculo.findByCodVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.codVehiculo = :codVehiculo")
    , @NamedQuery(name = "Vehiculo.findByTipoVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.tipoVehiculo = :tipoVehiculo")
    , @NamedQuery(name = "Vehiculo.findByCodPaquete", query = "SELECT v FROM Vehiculo v WHERE v.codPaquete = :codPaquete")
    , @NamedQuery(name = "Vehiculo.findByCodMensajero", query = "SELECT v FROM Vehiculo v WHERE v.codMensajero = :codMensajero")
    , @NamedQuery(name = "Vehiculo.findByEstatusVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.estatusVehiculo = :estatusVehiculo")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_VEHICULO")
    private BigDecimal codVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_VEHICULO")
    private BigInteger tipoVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAQUETE")
    private BigInteger codPaquete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_MENSAJERO")
    private BigInteger codMensajero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_VEHICULO")
    private BigInteger estatusVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoCodVehiculo")
    private Collection<EstatusVehiculo> estatusVehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoCodVehiculo")
    private Collection<TipoVehiculo> tipoVehiculoCollection;
    @JoinColumn(name = "PAQUETE_COD_PAQUETE", referencedColumnName = "COD_PAQUETE")
    @ManyToOne(optional = false)
    private Paquete paqueteCodPaquete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoCodVehiculo")
    private Collection<Mensajeros> mensajerosCollection;

    public Vehiculo() {
    }

    public Vehiculo(BigDecimal codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public Vehiculo(BigDecimal codVehiculo, BigInteger tipoVehiculo, BigInteger codPaquete, BigInteger codMensajero, BigInteger estatusVehiculo) {
        this.codVehiculo = codVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.codPaquete = codPaquete;
        this.codMensajero = codMensajero;
        this.estatusVehiculo = estatusVehiculo;
    }

    public BigDecimal getCodVehiculo() {
        return codVehiculo;
    }

    public void setCodVehiculo(BigDecimal codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public BigInteger getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(BigInteger tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public BigInteger getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(BigInteger codPaquete) {
        this.codPaquete = codPaquete;
    }

    public BigInteger getCodMensajero() {
        return codMensajero;
    }

    public void setCodMensajero(BigInteger codMensajero) {
        this.codMensajero = codMensajero;
    }

    public BigInteger getEstatusVehiculo() {
        return estatusVehiculo;
    }

    public void setEstatusVehiculo(BigInteger estatusVehiculo) {
        this.estatusVehiculo = estatusVehiculo;
    }

    @XmlTransient
    public Collection<EstatusVehiculo> getEstatusVehiculoCollection() {
        return estatusVehiculoCollection;
    }

    public void setEstatusVehiculoCollection(Collection<EstatusVehiculo> estatusVehiculoCollection) {
        this.estatusVehiculoCollection = estatusVehiculoCollection;
    }

    @XmlTransient
    public Collection<TipoVehiculo> getTipoVehiculoCollection() {
        return tipoVehiculoCollection;
    }

    public void setTipoVehiculoCollection(Collection<TipoVehiculo> tipoVehiculoCollection) {
        this.tipoVehiculoCollection = tipoVehiculoCollection;
    }

    public Paquete getPaqueteCodPaquete() {
        return paqueteCodPaquete;
    }

    public void setPaqueteCodPaquete(Paquete paqueteCodPaquete) {
        this.paqueteCodPaquete = paqueteCodPaquete;
    }

    @XmlTransient
    public Collection<Mensajeros> getMensajerosCollection() {
        return mensajerosCollection;
    }

    public void setMensajerosCollection(Collection<Mensajeros> mensajerosCollection) {
        this.mensajerosCollection = mensajerosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVehiculo != null ? codVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.codVehiculo == null && other.codVehiculo != null) || (this.codVehiculo != null && !this.codVehiculo.equals(other.codVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Vehiculo[ codVehiculo=" + codVehiculo + " ]";
    }
    
}
