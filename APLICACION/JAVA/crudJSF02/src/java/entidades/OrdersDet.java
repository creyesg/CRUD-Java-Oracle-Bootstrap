/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

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

/**
 *
 * @author naore
 */
@Entity
@Table(name = "ORDERS_DET")
@NamedQueries({
    @NamedQuery(name = "OrdersDet.findAll", query = "SELECT o FROM OrdersDet o")
    , @NamedQuery(name = "OrdersDet.findByDetId", query = "SELECT o FROM OrdersDet o WHERE o.detId = :detId")
    , @NamedQuery(name = "OrdersDet.findByCodProducto", query = "SELECT o FROM OrdersDet o WHERE o.codProducto = :codProducto")
    , @NamedQuery(name = "OrdersDet.findByPeso", query = "SELECT o FROM OrdersDet o WHERE o.peso = :peso")
    , @NamedQuery(name = "OrdersDet.findByTama\u00f1o", query = "SELECT o FROM OrdersDet o WHERE o.tama\u00f1o = :tama\u00f1o")})
public class OrdersDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_ID")
    private Long detId;
    @Size(max = 150)
    @Column(name = "COD_PRODUCTO")
    private String codProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "TAMA\u00d1O")
    private BigDecimal tamaño;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    @ManyToOne
    private Orders orderId;

    public OrdersDet() {
    }

    public OrdersDet(Long detId) {
        this.detId = detId;
    }

    public Long getDetId() {
        return detId;
    }

    public void setDetId(Long detId) {
        this.detId = detId;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getTamaño() {
        return tamaño;
    }

    public void setTamaño(BigDecimal tamaño) {
        this.tamaño = tamaño;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detId != null ? detId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdersDet)) {
            return false;
        }
        OrdersDet other = (OrdersDet) object;
        if ((this.detId == null && other.detId != null) || (this.detId != null && !this.detId.equals(other.detId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.OrdersDet[ detId=" + detId + " ]";
    }
    
}
