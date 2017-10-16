/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.db12017;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "DIRECCIONES_ENTREGAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionesEntregas.findAll", query = "SELECT d FROM DireccionesEntregas d")
    , @NamedQuery(name = "DireccionesEntregas.findByDireccionEntrega", query = "SELECT d FROM DireccionesEntregas d WHERE d.direccionEntrega = :direccionEntrega")
    , @NamedQuery(name = "DireccionesEntregas.findByCalle", query = "SELECT d FROM DireccionesEntregas d WHERE d.calle = :calle")
    , @NamedQuery(name = "DireccionesEntregas.findByAvenida", query = "SELECT d FROM DireccionesEntregas d WHERE d.avenida = :avenida")
    , @NamedQuery(name = "DireccionesEntregas.findByNoVivienda", query = "SELECT d FROM DireccionesEntregas d WHERE d.noVivienda = :noVivienda")
    , @NamedQuery(name = "DireccionesEntregas.findByZona", query = "SELECT d FROM DireccionesEntregas d WHERE d.zona = :zona")
    , @NamedQuery(name = "DireccionesEntregas.findByProvincia", query = "SELECT d FROM DireccionesEntregas d WHERE d.provincia = :provincia")
    , @NamedQuery(name = "DireccionesEntregas.findByMunicipio", query = "SELECT d FROM DireccionesEntregas d WHERE d.municipio = :municipio")
    , @NamedQuery(name = "DireccionesEntregas.findByPais", query = "SELECT d FROM DireccionesEntregas d WHERE d.pais = :pais")
    , @NamedQuery(name = "DireccionesEntregas.findByCodEntrega", query = "SELECT d FROM DireccionesEntregas d WHERE d.codEntrega = :codEntrega")})
public class DireccionesEntregas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIRECCION_ENTREGA")
    private BigDecimal direccionEntrega;
    @Size(max = 100)
    @Column(name = "CALLE")
    private String calle;
    @Size(max = 100)
    @Column(name = "AVENIDA")
    private String avenida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NO_VIVIENDA")
    private String noVivienda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZONA")
    private BigInteger zona;
    @Size(max = 100)
    @Column(name = "PROVINCIA")
    private String provincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PAIS")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ENTREGA")
    private BigInteger codEntrega;
    @JoinColumn(name = "ENTREGA_COD_ENTREGA", referencedColumnName = "COD_ENTREGA")
    @ManyToOne(optional = false)
    private Entrega entregaCodEntrega;

    public DireccionesEntregas() {
    }

    public DireccionesEntregas(BigDecimal direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public DireccionesEntregas(BigDecimal direccionEntrega, String noVivienda, BigInteger zona, String municipio, String pais, BigInteger codEntrega) {
        this.direccionEntrega = direccionEntrega;
        this.noVivienda = noVivienda;
        this.zona = zona;
        this.municipio = municipio;
        this.pais = pais;
        this.codEntrega = codEntrega;
    }

    public BigDecimal getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(BigDecimal direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getNoVivienda() {
        return noVivienda;
    }

    public void setNoVivienda(String noVivienda) {
        this.noVivienda = noVivienda;
    }

    public BigInteger getZona() {
        return zona;
    }

    public void setZona(BigInteger zona) {
        this.zona = zona;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public BigInteger getCodEntrega() {
        return codEntrega;
    }

    public void setCodEntrega(BigInteger codEntrega) {
        this.codEntrega = codEntrega;
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
        hash += (direccionEntrega != null ? direccionEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesEntregas)) {
            return false;
        }
        DireccionesEntregas other = (DireccionesEntregas) object;
        if ((this.direccionEntrega == null && other.direccionEntrega != null) || (this.direccionEntrega != null && !this.direccionEntrega.equals(other.direccionEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.DireccionesEntregas[ direccionEntrega=" + direccionEntrega + " ]";
    }
    
}
