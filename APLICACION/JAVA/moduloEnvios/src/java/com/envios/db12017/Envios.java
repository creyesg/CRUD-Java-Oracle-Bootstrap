/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.db12017;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "ENVIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envios.findAll", query = "SELECT e FROM Envios e")
    , @NamedQuery(name = "Envios.findByNumeroHojaDespacho", query = "SELECT e FROM Envios e WHERE e.enviosPK.numeroHojaDespacho = :numeroHojaDespacho")
    , @NamedQuery(name = "Envios.findByFechaEnvio", query = "SELECT e FROM Envios e WHERE e.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Envios.findByIdUnidad", query = "SELECT e FROM Envios e WHERE e.idUnidad = :idUnidad")
    , @NamedQuery(name = "Envios.findByCodTracking", query = "SELECT e FROM Envios e WHERE e.codTracking = :codTracking")
    , @NamedQuery(name = "Envios.findByDescripcion", query = "SELECT e FROM Envios e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Envios.findByUsuarioEnvio", query = "SELECT e FROM Envios e WHERE e.usuarioEnvio = :usuarioEnvio")
    , @NamedQuery(name = "Envios.findByCodEntrega", query = "SELECT e FROM Envios e WHERE e.enviosPK.codEntrega = :codEntrega")
    , @NamedQuery(name = "Envios.findByCodPaquete", query = "SELECT e FROM Envios e WHERE e.codPaquete = :codPaquete")
    , @NamedQuery(name = "Envios.findByNombreEnvia", query = "SELECT e FROM Envios e WHERE e.nombreEnvia = :nombreEnvia")})
public class Envios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnviosPK enviosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UNIDAD")
    private BigInteger idUnidad;
    @Column(name = "COD_TRACKING")
    private BigInteger codTracking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUARIO_ENVIO")
    private String usuarioEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAQUETE")
    private BigInteger codPaquete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_ENVIA")
    private String nombreEnvia;
    @JoinColumn(name = "DESPACHO_COD_DESPACHO", referencedColumnName = "COD_DESPACHO")
    @ManyToOne(optional = false)
    private Despacho despachoCodDespacho;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "envios")
    private Collection<Entrega> entregaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "envios")
    private Collection<Tracking> trackingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "envios")
    private Collection<Paquete> paqueteCollection;

    public Envios() {
    }

    public Envios(EnviosPK enviosPK) {
        this.enviosPK = enviosPK;
    }

    public Envios(EnviosPK enviosPK, Date fechaEnvio, BigInteger idUnidad, String descripcion, String usuarioEnvio, BigInteger codPaquete, String nombreEnvia) {
        this.enviosPK = enviosPK;
        this.fechaEnvio = fechaEnvio;
        this.idUnidad = idUnidad;
        this.descripcion = descripcion;
        this.usuarioEnvio = usuarioEnvio;
        this.codPaquete = codPaquete;
        this.nombreEnvia = nombreEnvia;
    }

    public Envios(BigInteger numeroHojaDespacho, BigInteger codEntrega) {
        this.enviosPK = new EnviosPK(numeroHojaDespacho, codEntrega);
    }

    public EnviosPK getEnviosPK() {
        return enviosPK;
    }

    public void setEnviosPK(EnviosPK enviosPK) {
        this.enviosPK = enviosPK;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public BigInteger getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(BigInteger idUnidad) {
        this.idUnidad = idUnidad;
    }

    public BigInteger getCodTracking() {
        return codTracking;
    }

    public void setCodTracking(BigInteger codTracking) {
        this.codTracking = codTracking;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioEnvio() {
        return usuarioEnvio;
    }

    public void setUsuarioEnvio(String usuarioEnvio) {
        this.usuarioEnvio = usuarioEnvio;
    }

    public BigInteger getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(BigInteger codPaquete) {
        this.codPaquete = codPaquete;
    }

    public String getNombreEnvia() {
        return nombreEnvia;
    }

    public void setNombreEnvia(String nombreEnvia) {
        this.nombreEnvia = nombreEnvia;
    }

    public Despacho getDespachoCodDespacho() {
        return despachoCodDespacho;
    }

    public void setDespachoCodDespacho(Despacho despachoCodDespacho) {
        this.despachoCodDespacho = despachoCodDespacho;
    }

    @XmlTransient
    public Collection<Entrega> getEntregaCollection() {
        return entregaCollection;
    }

    public void setEntregaCollection(Collection<Entrega> entregaCollection) {
        this.entregaCollection = entregaCollection;
    }

    @XmlTransient
    public Collection<Tracking> getTrackingCollection() {
        return trackingCollection;
    }

    public void setTrackingCollection(Collection<Tracking> trackingCollection) {
        this.trackingCollection = trackingCollection;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enviosPK != null ? enviosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envios)) {
            return false;
        }
        Envios other = (Envios) object;
        if ((this.enviosPK == null && other.enviosPK != null) || (this.enviosPK != null && !this.enviosPK.equals(other.enviosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Envios[ enviosPK=" + enviosPK + " ]";
    }
    
}
