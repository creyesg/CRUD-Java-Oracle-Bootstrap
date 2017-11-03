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
@Table(name = "ENVIOS_INTERNOS")
@NamedQueries({
    @NamedQuery(name = "EnviosInternos.findAll", query = "SELECT e FROM EnviosInternos e")
    , @NamedQuery(name = "EnviosInternos.findByCodEmpleado", query = "SELECT e FROM EnviosInternos e WHERE e.codEmpleado = :codEmpleado")
    , @NamedQuery(name = "EnviosInternos.findByTrackAlias", query = "SELECT e FROM EnviosInternos e WHERE e.trackAlias = :trackAlias")})
public class EnviosInternos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMPLEADO")
    private Long codEmpleado;
    @Size(max = 150)
    @Column(name = "TRACK_ALIAS")
    private String trackAlias;
    @OneToMany(mappedBy = "codEmpInterno")
    private Collection<Envios> enviosCollection;

    public EnviosInternos() {
    }

    public EnviosInternos(Long codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public Long getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Long codEmpleado) {
        this.codEmpleado = codEmpleado;
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
        hash += (codEmpleado != null ? codEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnviosInternos)) {
            return false;
        }
        EnviosInternos other = (EnviosInternos) object;
        if ((this.codEmpleado == null && other.codEmpleado != null) || (this.codEmpleado != null && !this.codEmpleado.equals(other.codEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnviosInternos[ codEmpleado=" + codEmpleado + " ]";
    }
    
}
