/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author naore
 */
@Entity
@Table(name = "STATUS_ORDER")
@NamedQueries({
    @NamedQuery(name = "StatusOrder.findAll", query = "SELECT s FROM StatusOrder s")
    , @NamedQuery(name = "StatusOrder.findByIdStatus", query = "SELECT s FROM StatusOrder s WHERE s.idStatus = :idStatus")
    , @NamedQuery(name = "StatusOrder.findByDescriptionStatus", query = "SELECT s FROM StatusOrder s WHERE s.descriptionStatus = :descriptionStatus")})
public class StatusOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_STATUS")
    private Long idStatus;
    @Size(max = 150)
    @Column(name = "DESCRIPTION_STATUS")
    private String descriptionStatus;
    @OneToMany(mappedBy = "idStatus")
    private Collection<Orders> ordersCollection;

    public StatusOrder() {
    }

    public StatusOrder(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescriptionStatus() {
        return descriptionStatus;
    }

    public void setDescriptionStatus(String descriptionStatus) {
        this.descriptionStatus = descriptionStatus;
    }

    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusOrder)) {
            return false;
        }
        StatusOrder other = (StatusOrder) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.StatusOrder[ idStatus=" + idStatus + " ]";
    }
    
}
