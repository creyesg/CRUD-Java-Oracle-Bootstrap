/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "TRACKING_T")
@NamedQueries({
    @NamedQuery(name = "TrackingT.findAll", query = "SELECT t FROM TrackingT t")
    , @NamedQuery(name = "TrackingT.findByTrackId", query = "SELECT t FROM TrackingT t WHERE t.trackId = :trackId")
    , @NamedQuery(name = "TrackingT.findByFecTrack", query = "SELECT t FROM TrackingT t WHERE t.fecTrack = :fecTrack")
    , @NamedQuery(name = "TrackingT.findByEmisorAlias", query = "SELECT t FROM TrackingT t WHERE t.emisorAlias = :emisorAlias")
    , @NamedQuery(name = "TrackingT.findByReseptorAlias", query = "SELECT t FROM TrackingT t WHERE t.reseptorAlias = :reseptorAlias")
    , @NamedQuery(name = "TrackingT.findByTxtObservaciones", query = "SELECT t FROM TrackingT t WHERE t.txtObservaciones = :txtObservaciones")
    , @NamedQuery(name = "TrackingT.findByCoordenadas", query = "SELECT t FROM TrackingT t WHERE t.coordenadas = :coordenadas")})
public class TrackingT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRACK_ID")
    private Long trackId;
    @Column(name = "FEC_TRACK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecTrack;
    @Size(max = 150)
    @Column(name = "EMISOR_ALIAS")
    private String emisorAlias;
    @Size(max = 150)
    @Column(name = "RESEPTOR_ALIAS")
    private String reseptorAlias;
    @Size(max = 500)
    @Column(name = "TXT_OBSERVACIONES")
    private String txtObservaciones;
    @Size(max = 200)
    @Column(name = "COORDENADAS")
    private String coordenadas;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    @ManyToOne
    private Orders orderId;
    @JoinColumn(name = "ID_STATUS", referencedColumnName = "ID_STATUS")
    @ManyToOne
    private StatusTrack idStatus;

    public TrackingT() {
    }

    public TrackingT(Long trackId) {
        this.trackId = trackId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Date getFecTrack() {
        return fecTrack;
    }

    public void setFecTrack(Date fecTrack) {
        this.fecTrack = fecTrack;
    }

    public String getEmisorAlias() {
        return emisorAlias;
    }

    public void setEmisorAlias(String emisorAlias) {
        this.emisorAlias = emisorAlias;
    }

    public String getReseptorAlias() {
        return reseptorAlias;
    }

    public void setReseptorAlias(String reseptorAlias) {
        this.reseptorAlias = reseptorAlias;
    }

    public String getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setTxtObservaciones(String txtObservaciones) {
        this.txtObservaciones = txtObservaciones;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public StatusTrack getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(StatusTrack idStatus) {
        this.idStatus = idStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trackId != null ? trackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrackingT)) {
            return false;
        }
        TrackingT other = (TrackingT) object;
        if ((this.trackId == null && other.trackId != null) || (this.trackId != null && !this.trackId.equals(other.trackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TrackingT[ trackId=" + trackId + " ]";
    }
    
}
