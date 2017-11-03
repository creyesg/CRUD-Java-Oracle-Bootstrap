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

/**
 *
 * @author naore
 */
@Entity
@Table(name = "ENVIOS")
@NamedQueries({
    @NamedQuery(name = "Envios.findAll", query = "SELECT e FROM Envios e")
    , @NamedQuery(name = "Envios.findByEnvioId", query = "SELECT e FROM Envios e WHERE e.envioId = :envioId")
    , @NamedQuery(name = "Envios.findBySnInterno", query = "SELECT e FROM Envios e WHERE e.snInterno = :snInterno")
    , @NamedQuery(name = "Envios.findByFecEnvio", query = "SELECT e FROM Envios e WHERE e.fecEnvio = :fecEnvio")})
public class Envios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENVIO_ID")
    private Long envioId;
    @Column(name = "SN_INTERNO")
    private Integer snInterno;
    @Column(name = "FEC_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecEnvio;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private EmpresasEnvio idEmpresa;
    @JoinColumn(name = "COD_EMP_INTERNO", referencedColumnName = "COD_EMPLEADO")
    @ManyToOne
    private EnviosInternos codEmpInterno;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    @ManyToOne
    private Orders orderId;

    public Envios() {
    }

    public Envios(Long envioId) {
        this.envioId = envioId;
    }

    public Long getEnvioId() {
        return envioId;
    }

    public void setEnvioId(Long envioId) {
        this.envioId = envioId;
    }

    public Integer getSnInterno() {
        return snInterno;
    }

    public void setSnInterno(Integer snInterno) {
        this.snInterno = snInterno;
    }

    public Date getFecEnvio() {
        return fecEnvio;
    }

    public void setFecEnvio(Date fecEnvio) {
        this.fecEnvio = fecEnvio;
    }

    public EmpresasEnvio getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(EmpresasEnvio idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public EnviosInternos getCodEmpInterno() {
        return codEmpInterno;
    }

    public void setCodEmpInterno(EnviosInternos codEmpInterno) {
        this.codEmpInterno = codEmpInterno;
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
        hash += (envioId != null ? envioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envios)) {
            return false;
        }
        Envios other = (Envios) object;
        if ((this.envioId == null && other.envioId != null) || (this.envioId != null && !this.envioId.equals(other.envioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Envios[ envioId=" + envioId + " ]";
    }
    
}
