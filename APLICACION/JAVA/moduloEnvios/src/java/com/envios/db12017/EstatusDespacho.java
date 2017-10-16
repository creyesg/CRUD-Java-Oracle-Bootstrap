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
@Table(name = "ESTATUS_DESPACHO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusDespacho.findAll", query = "SELECT e FROM EstatusDespacho e")
    , @NamedQuery(name = "EstatusDespacho.findByIdEstatusDespacho", query = "SELECT e FROM EstatusDespacho e WHERE e.idEstatusDespacho = :idEstatusDespacho")
    , @NamedQuery(name = "EstatusDespacho.findByDescriEstatusDespacho", query = "SELECT e FROM EstatusDespacho e WHERE e.descriEstatusDespacho = :descriEstatusDespacho")})
public class EstatusDespacho implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATUS_DESPACHO")
    private BigDecimal idEstatusDespacho;
    @Size(max = 10)
    @Column(name = "DESCRI_ESTATUS_DESPACHO")
    private String descriEstatusDespacho;
    @JoinColumn(name = "DESPACHO_COD_DESPACHO", referencedColumnName = "COD_DESPACHO")
    @ManyToOne(optional = false)
    private Despacho despachoCodDespacho;

    public EstatusDespacho() {
    }

    public EstatusDespacho(BigDecimal idEstatusDespacho) {
        this.idEstatusDespacho = idEstatusDespacho;
    }

    public BigDecimal getIdEstatusDespacho() {
        return idEstatusDespacho;
    }

    public void setIdEstatusDespacho(BigDecimal idEstatusDespacho) {
        this.idEstatusDespacho = idEstatusDespacho;
    }

    public String getDescriEstatusDespacho() {
        return descriEstatusDespacho;
    }

    public void setDescriEstatusDespacho(String descriEstatusDespacho) {
        this.descriEstatusDespacho = descriEstatusDespacho;
    }

    public Despacho getDespachoCodDespacho() {
        return despachoCodDespacho;
    }

    public void setDespachoCodDespacho(Despacho despachoCodDespacho) {
        this.despachoCodDespacho = despachoCodDespacho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatusDespacho != null ? idEstatusDespacho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusDespacho)) {
            return false;
        }
        EstatusDespacho other = (EstatusDespacho) object;
        if ((this.idEstatusDespacho == null && other.idEstatusDespacho != null) || (this.idEstatusDespacho != null && !this.idEstatusDespacho.equals(other.idEstatusDespacho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EstatusDespacho[ idEstatusDespacho=" + idEstatusDespacho + " ]";
    }
    
}
