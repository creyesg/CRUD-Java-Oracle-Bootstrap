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
@Table(name = "DESPACHO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despacho.findAll", query = "SELECT d FROM Despacho d")
    , @NamedQuery(name = "Despacho.findByCodDespacho", query = "SELECT d FROM Despacho d WHERE d.codDespacho = :codDespacho")
    , @NamedQuery(name = "Despacho.findByCodPaquete", query = "SELECT d FROM Despacho d WHERE d.codPaquete = :codPaquete")
    , @NamedQuery(name = "Despacho.findByFechaDespacho", query = "SELECT d FROM Despacho d WHERE d.fechaDespacho = :fechaDespacho")
    , @NamedQuery(name = "Despacho.findByNumeroHojaDespacho", query = "SELECT d FROM Despacho d WHERE d.numeroHojaDespacho = :numeroHojaDespacho")
    , @NamedQuery(name = "Despacho.findByUsuarioDespacho", query = "SELECT d FROM Despacho d WHERE d.usuarioDespacho = :usuarioDespacho")
    , @NamedQuery(name = "Despacho.findByIdEstatusDespacho", query = "SELECT d FROM Despacho d WHERE d.idEstatusDespacho = :idEstatusDespacho")})
public class Despacho implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DESPACHO")
    private BigDecimal codDespacho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAQUETE")
    private BigInteger codPaquete;
    @Column(name = "FECHA_DESPACHO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDespacho;
    @Column(name = "NUMERO_HOJA_DESPACHO")
    private BigInteger numeroHojaDespacho;
    @Size(max = 100)
    @Column(name = "USUARIO_DESPACHO")
    private String usuarioDespacho;
    @Column(name = "ID_ESTATUS_DESPACHO")
    private BigInteger idEstatusDespacho;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "despachoCodDespacho")
    private Collection<Envios> enviosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "despachoCodDespacho")
    private Collection<EstatusDespacho> estatusDespachoCollection;

    public Despacho() {
    }

    public Despacho(BigDecimal codDespacho) {
        this.codDespacho = codDespacho;
    }

    public Despacho(BigDecimal codDespacho, BigInteger codPaquete) {
        this.codDespacho = codDespacho;
        this.codPaquete = codPaquete;
    }

    public BigDecimal getCodDespacho() {
        return codDespacho;
    }

    public void setCodDespacho(BigDecimal codDespacho) {
        this.codDespacho = codDespacho;
    }

    public BigInteger getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(BigInteger codPaquete) {
        this.codPaquete = codPaquete;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public BigInteger getNumeroHojaDespacho() {
        return numeroHojaDespacho;
    }

    public void setNumeroHojaDespacho(BigInteger numeroHojaDespacho) {
        this.numeroHojaDespacho = numeroHojaDespacho;
    }

    public String getUsuarioDespacho() {
        return usuarioDespacho;
    }

    public void setUsuarioDespacho(String usuarioDespacho) {
        this.usuarioDespacho = usuarioDespacho;
    }

    public BigInteger getIdEstatusDespacho() {
        return idEstatusDespacho;
    }

    public void setIdEstatusDespacho(BigInteger idEstatusDespacho) {
        this.idEstatusDespacho = idEstatusDespacho;
    }

    @XmlTransient
    public Collection<Envios> getEnviosCollection() {
        return enviosCollection;
    }

    public void setEnviosCollection(Collection<Envios> enviosCollection) {
        this.enviosCollection = enviosCollection;
    }

    @XmlTransient
    public Collection<EstatusDespacho> getEstatusDespachoCollection() {
        return estatusDespachoCollection;
    }

    public void setEstatusDespachoCollection(Collection<EstatusDespacho> estatusDespachoCollection) {
        this.estatusDespachoCollection = estatusDespachoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDespacho != null ? codDespacho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despacho)) {
            return false;
        }
        Despacho other = (Despacho) object;
        if ((this.codDespacho == null && other.codDespacho != null) || (this.codDespacho != null && !this.codDespacho.equals(other.codDespacho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Despacho[ codDespacho=" + codDespacho + " ]";
    }
    
}
