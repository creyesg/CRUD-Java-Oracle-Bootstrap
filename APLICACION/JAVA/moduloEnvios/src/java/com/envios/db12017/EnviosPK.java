/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.db12017;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author naore
 */
@Embeddable
public class EnviosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_HOJA_DESPACHO")
    private BigInteger numeroHojaDespacho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ENTREGA")
    private BigInteger codEntrega;

    public EnviosPK() {
    }

    public EnviosPK(BigInteger numeroHojaDespacho, BigInteger codEntrega) {
        this.numeroHojaDespacho = numeroHojaDespacho;
        this.codEntrega = codEntrega;
    }

    public BigInteger getNumeroHojaDespacho() {
        return numeroHojaDespacho;
    }

    public void setNumeroHojaDespacho(BigInteger numeroHojaDespacho) {
        this.numeroHojaDespacho = numeroHojaDespacho;
    }

    public BigInteger getCodEntrega() {
        return codEntrega;
    }

    public void setCodEntrega(BigInteger codEntrega) {
        this.codEntrega = codEntrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroHojaDespacho != null ? numeroHojaDespacho.hashCode() : 0);
        hash += (codEntrega != null ? codEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnviosPK)) {
            return false;
        }
        EnviosPK other = (EnviosPK) object;
        if ((this.numeroHojaDespacho == null && other.numeroHojaDespacho != null) || (this.numeroHojaDespacho != null && !this.numeroHojaDespacho.equals(other.numeroHojaDespacho))) {
            return false;
        }
        if ((this.codEntrega == null && other.codEntrega != null) || (this.codEntrega != null && !this.codEntrega.equals(other.codEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.envios.db12017.EnviosPK[ numeroHojaDespacho=" + numeroHojaDespacho + ", codEntrega=" + codEntrega + " ]";
    }
    
}
