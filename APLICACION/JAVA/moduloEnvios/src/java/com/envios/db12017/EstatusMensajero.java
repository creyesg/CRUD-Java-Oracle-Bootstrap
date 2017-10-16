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
@Table(name = "ESTATUS_MENSAJERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusMensajero.findAll", query = "SELECT e FROM EstatusMensajero e")
    , @NamedQuery(name = "EstatusMensajero.findByEstatusMensajero", query = "SELECT e FROM EstatusMensajero e WHERE e.estatusMensajero = :estatusMensajero")
    , @NamedQuery(name = "EstatusMensajero.findByDescriEstatusMensajero", query = "SELECT e FROM EstatusMensajero e WHERE e.descriEstatusMensajero = :descriEstatusMensajero")})
public class EstatusMensajero implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_MENSAJERO")
    private BigDecimal estatusMensajero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESCRI_ESTATUS_MENSAJERO")
    private String descriEstatusMensajero;
    @JoinColumn(name = "MENSAJEROS_COD_MENSAJERO", referencedColumnName = "COD_MENSAJERO")
    @ManyToOne(optional = false)
    private Mensajeros mensajerosCodMensajero;

    public EstatusMensajero() {
    }

    public EstatusMensajero(BigDecimal estatusMensajero) {
        this.estatusMensajero = estatusMensajero;
    }

    public EstatusMensajero(BigDecimal estatusMensajero, String descriEstatusMensajero) {
        this.estatusMensajero = estatusMensajero;
        this.descriEstatusMensajero = descriEstatusMensajero;
    }

    public BigDecimal getEstatusMensajero() {
        return estatusMensajero;
    }

    public void setEstatusMensajero(BigDecimal estatusMensajero) {
        this.estatusMensajero = estatusMensajero;
    }

    public String getDescriEstatusMensajero() {
        return descriEstatusMensajero;
    }

    public void setDescriEstatusMensajero(String descriEstatusMensajero) {
        this.descriEstatusMensajero = descriEstatusMensajero;
    }

    public Mensajeros getMensajerosCodMensajero() {
        return mensajerosCodMensajero;
    }

    public void setMensajerosCodMensajero(Mensajeros mensajerosCodMensajero) {
        this.mensajerosCodMensajero = mensajerosCodMensajero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estatusMensajero != null ? estatusMensajero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusMensajero)) {
            return false;
        }
        EstatusMensajero other = (EstatusMensajero) object;
        if ((this.estatusMensajero == null && other.estatusMensajero != null) || (this.estatusMensajero != null && !this.estatusMensajero.equals(other.estatusMensajero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EstatusMensajero[ estatusMensajero=" + estatusMensajero + " ]";
    }
    
}
