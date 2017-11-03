/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author naore
 */
@Entity
@Table(name = "ORDERS")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findByOrderId", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId")
    , @NamedQuery(name = "Orders.findByNombreFactura", query = "SELECT o FROM Orders o WHERE o.nombreFactura = :nombreFactura")
    , @NamedQuery(name = "Orders.findByNit", query = "SELECT o FROM Orders o WHERE o.nit = :nit")
    , @NamedQuery(name = "Orders.findByDireccion", query = "SELECT o FROM Orders o WHERE o.direccion = :direccion")
    , @NamedQuery(name = "Orders.findByClientId", query = "SELECT o FROM Orders o WHERE o.clientId = :clientId")
    , @NamedQuery(name = "Orders.findByFecOrder", query = "SELECT o FROM Orders o WHERE o.fecOrder = :fecOrder")
    , @NamedQuery(name = "Orders.findByPesoTotal", query = "SELECT o FROM Orders o WHERE o.pesoTotal = :pesoTotal")
    , @NamedQuery(name = "Orders.findByTamanioTotal", query = "SELECT o FROM Orders o WHERE o.tamanioTotal = :tamanioTotal")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_ID")
    private Long orderId;
    @Size(max = 300)
    @Column(name = "NOMBRE_FACTURA")
    private String nombreFactura;
    @Size(max = 10)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 250)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Column(name = "FEC_ORDER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecOrder;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO_TOTAL")
    private BigDecimal pesoTotal;
    @Column(name = "TAMANIO_TOTAL")
    private BigDecimal tamanioTotal;
    @OneToMany(mappedBy = "orderId")
    private Collection<Envios> enviosCollection;
    @JoinColumn(name = "ID_STATUS", referencedColumnName = "ID_STATUS")
    @ManyToOne
    private StatusOrder idStatus;
    @OneToMany(mappedBy = "orderId")
    private Collection<OrdersDet> ordersDetCollection;
    @OneToMany(mappedBy = "orderId")
    private Collection<TrackingT> trackingTCollection;

    public Orders() {
    }

    public Orders(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getNombreFactura() {
        return nombreFactura;
    }

    public void setNombreFactura(String nombreFactura) {
        this.nombreFactura = nombreFactura;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getFecOrder() {
        return fecOrder;
    }

    public void setFecOrder(Date fecOrder) {
        this.fecOrder = fecOrder;
    }

    public BigDecimal getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public BigDecimal getTamanioTotal() {
        return tamanioTotal;
    }

    public void setTamanioTotal(BigDecimal tamanioTotal) {
        this.tamanioTotal = tamanioTotal;
    }

    public Collection<Envios> getEnviosCollection() {
        return enviosCollection;
    }

    public void setEnviosCollection(Collection<Envios> enviosCollection) {
        this.enviosCollection = enviosCollection;
    }

    public StatusOrder getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(StatusOrder idStatus) {
        this.idStatus = idStatus;
    }

    public Collection<OrdersDet> getOrdersDetCollection() {
        return ordersDetCollection;
    }

    public void setOrdersDetCollection(Collection<OrdersDet> ordersDetCollection) {
        this.ordersDetCollection = ordersDetCollection;
    }

    public Collection<TrackingT> getTrackingTCollection() {
        return trackingTCollection;
    }

    public void setTrackingTCollection(Collection<TrackingT> trackingTCollection) {
        this.trackingTCollection = trackingTCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Orders[ orderId=" + orderId + " ]";
    }
    
}
