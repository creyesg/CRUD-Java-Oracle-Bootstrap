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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author naore
 */
@Entity
@Table(name = "PAQUETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p")
    , @NamedQuery(name = "Paquete.findByCodPaquete", query = "SELECT p FROM Paquete p WHERE p.codPaquete = :codPaquete")
    , @NamedQuery(name = "Paquete.findByFechaEnvio", query = "SELECT p FROM Paquete p WHERE p.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Paquete.findByFechaDespacho", query = "SELECT p FROM Paquete p WHERE p.fechaDespacho = :fechaDespacho")
    , @NamedQuery(name = "Paquete.findByCodVehiculo", query = "SELECT p FROM Paquete p WHERE p.codVehiculo = :codVehiculo")
    , @NamedQuery(name = "Paquete.findByNumeroHojaDespacho", query = "SELECT p FROM Paquete p WHERE p.numeroHojaDespacho = :numeroHojaDespacho")
    , @NamedQuery(name = "Paquete.findByUsuarioEnvioPaquete", query = "SELECT p FROM Paquete p WHERE p.usuarioEnvioPaquete = :usuarioEnvioPaquete")
    , @NamedQuery(name = "Paquete.findByEstatusPaquete", query = "SELECT p FROM Paquete p WHERE p.estatusPaquete = :estatusPaquete")})
public class Paquete implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAQUETE")
    private BigDecimal codPaquete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Column(name = "FECHA_DESPACHO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDespacho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_VEHICULO")
    private BigInteger codVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_HOJA_DESPACHO")
    private BigInteger numeroHojaDespacho;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO_ENVIO_PAQUETE")
    private String usuarioEnvioPaquete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_PAQUETE")
    private BigInteger estatusPaquete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paqueteCodPaquete")
    private Collection<Vehiculo> vehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paqueteCodPaquete")
    private Collection<EstatusPaquete> estatusPaqueteCollection;
    @JoinColumns({
        @JoinColumn(name = "ENVIOS_NUMERO_HOJA_DESPACHO", referencedColumnName = "NUMERO_HOJA_DESPACHO")
        , @JoinColumn(name = "ENVIOS_COD_ENTREGA", referencedColumnName = "COD_ENTREGA")})
    @ManyToOne(optional = false)
    private Envios envios;

    public Paquete() {
    }

    public Paquete(BigDecimal codPaquete) {
        this.codPaquete = codPaquete;
    }

    public Paquete(BigDecimal codPaquete, Date fechaEnvio, BigInteger codVehiculo, BigInteger numeroHojaDespacho, String usuarioEnvioPaquete, BigInteger estatusPaquete) {
        this.codPaquete = codPaquete;
        this.fechaEnvio = fechaEnvio;
        this.codVehiculo = codVehiculo;
        this.numeroHojaDespacho = numeroHojaDespacho;
        this.usuarioEnvioPaquete = usuarioEnvioPaquete;
        this.estatusPaquete = estatusPaquete;
    }

    public BigDecimal getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(BigDecimal codPaquete) {
        this.codPaquete = codPaquete;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public BigInteger getCodVehiculo() {
        return codVehiculo;
    }

    public void setCodVehiculo(BigInteger codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public BigInteger getNumeroHojaDespacho() {
        return numeroHojaDespacho;
    }

    public void setNumeroHojaDespacho(BigInteger numeroHojaDespacho) {
        this.numeroHojaDespacho = numeroHojaDespacho;
    }

    public String getUsuarioEnvioPaquete() {
        return usuarioEnvioPaquete;
    }

    public void setUsuarioEnvioPaquete(String usuarioEnvioPaquete) {
        this.usuarioEnvioPaquete = usuarioEnvioPaquete;
    }

    public BigInteger getEstatusPaquete() {
        return estatusPaquete;
    }

    public void setEstatusPaquete(BigInteger estatusPaquete) {
        this.estatusPaquete = estatusPaquete;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @XmlTransient
    public Collection<EstatusPaquete> getEstatusPaqueteCollection() {
        return estatusPaqueteCollection;
    }

    public void setEstatusPaqueteCollection(Collection<EstatusPaquete> estatusPaqueteCollection) {
        this.estatusPaqueteCollection = estatusPaqueteCollection;
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
        hash += (codPaquete != null ? codPaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.codPaquete == null && other.codPaquete != null) || (this.codPaquete != null && !this.codPaquete.equals(other.codPaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Paquete[ codPaquete=" + codPaquete + " ]";
    }
    
}
