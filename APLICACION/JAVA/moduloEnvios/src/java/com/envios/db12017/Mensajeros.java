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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author naore
 */
@Entity
@Table(name = "MENSAJEROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajeros.findAll", query = "SELECT m FROM Mensajeros m")
    , @NamedQuery(name = "Mensajeros.findByCodMensajero", query = "SELECT m FROM Mensajeros m WHERE m.codMensajero = :codMensajero")
    , @NamedQuery(name = "Mensajeros.findByPrimerNombreMensajero", query = "SELECT m FROM Mensajeros m WHERE m.primerNombreMensajero = :primerNombreMensajero")
    , @NamedQuery(name = "Mensajeros.findBySegundoNombreMensajero", query = "SELECT m FROM Mensajeros m WHERE m.segundoNombreMensajero = :segundoNombreMensajero")
    , @NamedQuery(name = "Mensajeros.findByPrimerApellidoMensajero", query = "SELECT m FROM Mensajeros m WHERE m.primerApellidoMensajero = :primerApellidoMensajero")
    , @NamedQuery(name = "Mensajeros.findBySegundoApellidoMensajero", query = "SELECT m FROM Mensajeros m WHERE m.segundoApellidoMensajero = :segundoApellidoMensajero")
    , @NamedQuery(name = "Mensajeros.findByEstatusMensajero", query = "SELECT m FROM Mensajeros m WHERE m.estatusMensajero = :estatusMensajero")})
public class Mensajeros implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_MENSAJERO")
    private BigDecimal codMensajero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRIMER_NOMBRE_MENSAJERO")
    private String primerNombreMensajero;
    @Size(max = 50)
    @Column(name = "SEGUNDO_NOMBRE_MENSAJERO")
    private String segundoNombreMensajero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRIMER_APELLIDO_MENSAJERO")
    private String primerApellidoMensajero;
    @Size(max = 50)
    @Column(name = "SEGUNDO_APELLIDO_MENSAJERO")
    private String segundoApellidoMensajero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS_MENSAJERO")
    private BigInteger estatusMensajero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensajerosCodMensajero")
    private Collection<EstatusMensajero> estatusMensajeroCollection;
    @JoinColumn(name = "VEHICULO_COD_VEHICULO", referencedColumnName = "COD_VEHICULO")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoCodVehiculo;

    public Mensajeros() {
    }

    public Mensajeros(BigDecimal codMensajero) {
        this.codMensajero = codMensajero;
    }

    public Mensajeros(BigDecimal codMensajero, String primerNombreMensajero, String primerApellidoMensajero, BigInteger estatusMensajero) {
        this.codMensajero = codMensajero;
        this.primerNombreMensajero = primerNombreMensajero;
        this.primerApellidoMensajero = primerApellidoMensajero;
        this.estatusMensajero = estatusMensajero;
    }

    public BigDecimal getCodMensajero() {
        return codMensajero;
    }

    public void setCodMensajero(BigDecimal codMensajero) {
        this.codMensajero = codMensajero;
    }

    public String getPrimerNombreMensajero() {
        return primerNombreMensajero;
    }

    public void setPrimerNombreMensajero(String primerNombreMensajero) {
        this.primerNombreMensajero = primerNombreMensajero;
    }

    public String getSegundoNombreMensajero() {
        return segundoNombreMensajero;
    }

    public void setSegundoNombreMensajero(String segundoNombreMensajero) {
        this.segundoNombreMensajero = segundoNombreMensajero;
    }

    public String getPrimerApellidoMensajero() {
        return primerApellidoMensajero;
    }

    public void setPrimerApellidoMensajero(String primerApellidoMensajero) {
        this.primerApellidoMensajero = primerApellidoMensajero;
    }

    public String getSegundoApellidoMensajero() {
        return segundoApellidoMensajero;
    }

    public void setSegundoApellidoMensajero(String segundoApellidoMensajero) {
        this.segundoApellidoMensajero = segundoApellidoMensajero;
    }

    public BigInteger getEstatusMensajero() {
        return estatusMensajero;
    }

    public void setEstatusMensajero(BigInteger estatusMensajero) {
        this.estatusMensajero = estatusMensajero;
    }

    @XmlTransient
    public Collection<EstatusMensajero> getEstatusMensajeroCollection() {
        return estatusMensajeroCollection;
    }

    public void setEstatusMensajeroCollection(Collection<EstatusMensajero> estatusMensajeroCollection) {
        this.estatusMensajeroCollection = estatusMensajeroCollection;
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
        hash += (codMensajero != null ? codMensajero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensajeros)) {
            return false;
        }
        Mensajeros other = (Mensajeros) object;
        if ((this.codMensajero == null && other.codMensajero != null) || (this.codMensajero != null && !this.codMensajero.equals(other.codMensajero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.Mensajeros[ codMensajero=" + codMensajero + " ]";
    }
    
}
