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
@Table(name = "ESTATUS_ENTREGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusEntrega.findAll", query = "SELECT e FROM EstatusEntrega e")
    , @NamedQuery(name = "EstatusEntrega.findByEstatusEntrega", query = "SELECT e FROM EstatusEntrega e WHERE e.estatusEntrega = :estatusEntrega")
    , @NamedQuery(name = "EstatusEntrega.findByDescriEstatusEntrega", query = "SELECT e FROM EstatusEntrega e WHERE e.descriEstatusEntrega = :descriEstatusEntrega")})
public class EstatusEntrega implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_ENTREGA")
    private BigDecimal estatusEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DESCRI_ESTATUS_ENTREGA")
    private String descriEstatusEntrega;
    @JoinColumn(name = "ENTREGA_COD_ENTREGA", referencedColumnName = "COD_ENTREGA")
    @ManyToOne(optional = false)
    private Entrega entregaCodEntrega;

    public EstatusEntrega() {
    }

    public EstatusEntrega(BigDecimal estatusEntrega) {
        this.estatusEntrega = estatusEntrega;
    }

    public EstatusEntrega(BigDecimal estatusEntrega, String descriEstatusEntrega) {
        this.estatusEntrega = estatusEntrega;
        this.descriEstatusEntrega = descriEstatusEntrega;
    }

    public BigDecimal getEstatusEntrega() {
        return estatusEntrega;
    }

    public void setEstatusEntrega(BigDecimal estatusEntrega) {
        this.estatusEntrega = estatusEntrega;
    }

    public String getDescriEstatusEntrega() {
        return descriEstatusEntrega;
    }

    public void setDescriEstatusEntrega(String descriEstatusEntrega) {
        this.descriEstatusEntrega = descriEstatusEntrega;
    }

    public Entrega getEntregaCodEntrega() {
        return entregaCodEntrega;
    }

    public void setEntregaCodEntrega(Entrega entregaCodEntrega) {
        this.entregaCodEntrega = entregaCodEntrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estatusEntrega != null ? estatusEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusEntrega)) {
            return false;
        }
        EstatusEntrega other = (EstatusEntrega) object;
        if ((this.estatusEntrega == null && other.estatusEntrega != null) || (this.estatusEntrega != null && !this.estatusEntrega.equals(other.estatusEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EstatusEntrega[ estatusEntrega=" + estatusEntrega + " ]";
    }
    
}
