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
@Table(name = "ENTREGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e")
    , @NamedQuery(name = "Entrega.findByCodEntrega", query = "SELECT e FROM Entrega e WHERE e.codEntrega = :codEntrega")
    , @NamedQuery(name = "Entrega.findByNumeroHojaDespacho", query = "SELECT e FROM Entrega e WHERE e.numeroHojaDespacho = :numeroHojaDespacho")
    , @NamedQuery(name = "Entrega.findByFechaEntrega", query = "SELECT e FROM Entrega e WHERE e.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "Entrega.findByDireccionEntrega", query = "SELECT e FROM Entrega e WHERE e.direccionEntrega = :direccionEntrega")
    , @NamedQuery(name = "Entrega.findByNombreEntrega", query = "SELECT e FROM Entrega e WHERE e.nombreEntrega = :nombreEntrega")
    , @NamedQuery(name = "Entrega.findByEstatusEntrega", query = "SELECT e FROM Entrega e WHERE e.estatusEntrega = :estatusEntrega")
    , @NamedQuery(name = "Entrega.findByUsuarioEntrega", query = "SELECT e FROM Entrega e WHERE e.usuarioEntrega = :usuarioEntrega")})
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ENTREGA")
    private BigDecimal codEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_HOJA_DESPACHO")
    private BigInteger numeroHojaDespacho;
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DIRECCION_ENTREGA")
    private String direccionEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_ENTREGA")
    private String nombreEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_ENTREGA")
    private BigInteger estatusEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USUARIO_ENTREGA")
    private String usuarioEntrega;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entregaCodEntrega")
    private Collection<EstatusEntrega> estatusEntregaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entregaCodEntrega")
    private Collection<DireccionesEntregas> direccionesEntregasCollection;
    @JoinColumns({
        @JoinColumn(name = "ENVIOS_NUMERO_HOJA_DESPACHO", referencedColumnName = "NUMERO_HOJA_DESPACHO")
        , @JoinColumn(name = "ENVIOS_COD_ENTREGA", referencedColumnName = "COD_ENTREGA")})
    @ManyToOne(optional = false)
    private Envios envios;

    public Entrega() {
    }

    public Entrega(BigDecimal codEntrega) {
        this.codEntrega = codEntrega;
    }

    public Entrega(BigDecimal codEntrega, BigInteger numeroHojaDespacho, String direccionEntrega, String nombreEntrega, BigInteger estatusEntrega, String usuarioEntrega) {
        this.codEntrega = codEntrega;
        this.numeroHojaDespacho = numeroHojaDespacho;
        this.direccionEntrega = direccionEntrega;
        this.nombreEntrega = nombreEntrega;
        this.estatusEntrega = estatusEntrega;
        this.usuarioEntrega = usuarioEntrega;
    }

    public BigDecimal getCodEntrega() {
        return codEntrega;
    }

    public void setCodEntrega(BigDecimal codEntrega) {
        this.codEntrega = codEntrega;
    }

    public BigInteger getNumeroHojaDespacho() {
        return numeroHojaDespacho;
    }

    public void setNumeroHojaDespacho(BigInteger numeroHojaDespacho) {
        this.numeroHojaDespacho = numeroHojaDespacho;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getNombreEntrega() {
        return nombreEntrega;
    }

    public void setNombreEntrega(String nombreEntrega) {
        this.nombreEntrega = nombreEntrega;
    }

    public BigInteger getEstatusEntrega() {
        return estatusEntrega;
    }

    public void setEstatusEntrega(BigInteger estatusEntrega) {
        this.estatusEntrega = estatusEntrega;
    }

    public String getUsuarioEntrega() {
        return usuarioEntrega;
    }

    public void setUsuarioEntrega(String usuarioEntrega) {
        this.usuarioEntrega = usuarioEntrega;
    }

    @XmlTransient
    public Collection<EstatusEntrega> getEstatusEntregaCollection() {
        return estatusEntregaCollection;
    }

    public void setEstatusEntregaCollection(Collection<EstatusEntrega> estatusEntregaCollection) {
        this.estatusEntregaCollection = estatusEntregaCollection;
    }

    @XmlTransient
    public Collection<DireccionesEntregas> getDireccionesEntregasCollection() {
        return direccionesEntregasCollection;
    }

    public void setDireccionesEntregasCollection(Collection<DireccionesEntregas> direccionesEntregasCollection) {
        this.direccionesEntregasCollection = direccionesEntregasCollection;
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
        hash += (codEntrega != null ? codEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        if ((this.codEntrega == null && other.codEntrega != null) || (this.codEntrega != null && !this.codEntrega.equals(other.codEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Entrega[ codEntrega=" + codEntrega + " ]";
    }
    
}
