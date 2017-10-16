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
@Table(name = "ESTATUS_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusVehiculo.findAll", query = "SELECT e FROM EstatusVehiculo e")
    , @NamedQuery(name = "EstatusVehiculo.findByEstatusVehiculo", query = "SELECT e FROM EstatusVehiculo e WHERE e.estatusVehiculo = :estatusVehiculo")
    , @NamedQuery(name = "EstatusVehiculo.findByDescriEstatusVehiculo", query = "SELECT e FROM EstatusVehiculo e WHERE e.descriEstatusVehiculo = :descriEstatusVehiculo")})
public class EstatusVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_VEHICULO")
    private BigDecimal estatusVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRI_ESTATUS_VEHICULO")
    private String descriEstatusVehiculo;
    @JoinColumn(name = "VEHICULO_COD_VEHICULO", referencedColumnName = "COD_VEHICULO")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoCodVehiculo;

    public EstatusVehiculo() {
    }

    public EstatusVehiculo(BigDecimal estatusVehiculo) {
        this.estatusVehiculo = estatusVehiculo;
    }

    public EstatusVehiculo(BigDecimal estatusVehiculo, String descriEstatusVehiculo) {
        this.estatusVehiculo = estatusVehiculo;
        this.descriEstatusVehiculo = descriEstatusVehiculo;
    }

    public BigDecimal getEstatusVehiculo() {
        return estatusVehiculo;
    }

    public void setEstatusVehiculo(BigDecimal estatusVehiculo) {
        this.estatusVehiculo = estatusVehiculo;
    }

    public String getDescriEstatusVehiculo() {
        return descriEstatusVehiculo;
    }

    public void setDescriEstatusVehiculo(String descriEstatusVehiculo) {
        this.descriEstatusVehiculo = descriEstatusVehiculo;
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
        hash += (estatusVehiculo != null ? estatusVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusVehiculo)) {
            return false;
        }
        EstatusVehiculo other = (EstatusVehiculo) object;
        if ((this.estatusVehiculo == null && other.estatusVehiculo != null) || (this.estatusVehiculo != null && !this.estatusVehiculo.equals(other.estatusVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EstatusVehiculo[ estatusVehiculo=" + estatusVehiculo + " ]";
    }
    
}
