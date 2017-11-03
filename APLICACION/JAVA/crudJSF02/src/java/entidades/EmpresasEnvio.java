/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "EMPRESAS_ENVIO")
@NamedQueries({
    @NamedQuery(name = "EmpresasEnvio.findAll", query = "SELECT e FROM EmpresasEnvio e")
    , @NamedQuery(name = "EmpresasEnvio.findByIdEmpresa", query = "SELECT e FROM EmpresasEnvio e WHERE e.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "EmpresasEnvio.findByNombreEmpresa", query = "SELECT e FROM EmpresasEnvio e WHERE e.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "EmpresasEnvio.findByTarifaCobro", query = "SELECT e FROM EmpresasEnvio e WHERE e.tarifaCobro = :tarifaCobro")
    , @NamedQuery(name = "EmpresasEnvio.findByTrackAlias", query = "SELECT e FROM EmpresasEnvio e WHERE e.trackAlias = :trackAlias")})
public class EmpresasEnvio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;
    @Size(max = 200)
    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIFA_COBRO")
    private BigDecimal tarifaCobro;
    @Size(max = 150)
    @Column(name = "TRACK_ALIAS")
    private String trackAlias;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Envios> enviosCollection;

    public EmpresasEnvio() {
    }

    public EmpresasEnvio(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public BigDecimal getTarifaCobro() {
        return tarifaCobro;
    }

    public void setTarifaCobro(BigDecimal tarifaCobro) {
        this.tarifaCobro = tarifaCobro;
    }

    public String getTrackAlias() {
        return trackAlias;
    }

    public void setTrackAlias(String trackAlias) {
        this.trackAlias = trackAlias;
    }

    public Collection<Envios> getEnviosCollection() {
        return enviosCollection;
    }

    public void setEnviosCollection(Collection<Envios> enviosCollection) {
        this.enviosCollection = enviosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresasEnvio)) {
            return false;
        }
        EmpresasEnvio other = (EmpresasEnvio) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EmpresasEnvio[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
