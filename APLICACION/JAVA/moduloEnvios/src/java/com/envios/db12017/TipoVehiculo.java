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
@Table(name = "TIPO_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVehiculo.findAll", query = "SELECT t FROM TipoVehiculo t")
    , @NamedQuery(name = "TipoVehiculo.findByTipoVehiculo", query = "SELECT t FROM TipoVehiculo t WHERE t.tipoVehiculo = :tipoVehiculo")
    , @NamedQuery(name = "TipoVehiculo.findByDescriTipoVehiculo", query = "SELECT t FROM TipoVehiculo t WHERE t.descriTipoVehiculo = :descriTipoVehiculo")})
public class TipoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_VEHICULO")
    private BigDecimal tipoVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRI_TIPO_VEHICULO")
    private String descriTipoVehiculo;
    @JoinColumn(name = "VEHICULO_COD_VEHICULO", referencedColumnName = "COD_VEHICULO")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoCodVehiculo;

    public TipoVehiculo() {
    }

    public TipoVehiculo(BigDecimal tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public TipoVehiculo(BigDecimal tipoVehiculo, String descriTipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        this.descriTipoVehiculo = descriTipoVehiculo;
    }

    public BigDecimal getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(BigDecimal tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getDescriTipoVehiculo() {
        return descriTipoVehiculo;
    }

    public void setDescriTipoVehiculo(String descriTipoVehiculo) {
        this.descriTipoVehiculo = descriTipoVehiculo;
    }

    public Vehiculo getVehiculoCodVehiculo() {
        return vehiculoCodVehiculo;
    }

    public void setVehiculoCodVehiculo(Vehiculo vehiculoCodVehiculo) {
        this.vehiculoCodVehiculo = vehiculoCodVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoVehiculo != null ? tipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVehiculo)) {
            return false;
        }
        TipoVehiculo other = (TipoVehiculo) object;
        if ((this.tipoVehiculo == null && other.tipoVehiculo != null) || (this.tipoVehiculo != null && !this.tipoVehiculo.equals(other.tipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.TipoVehiculo[ tipoVehiculo=" + tipoVehiculo + " ]";
    }
    
}
