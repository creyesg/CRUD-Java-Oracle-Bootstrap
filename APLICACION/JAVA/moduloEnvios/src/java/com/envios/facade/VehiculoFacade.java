/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Vehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Vehiculo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.EstatusVehiculo;
import com.envios.db12017.TipoVehiculo;
import com.envios.db12017.Paquete;
import com.envios.db12017.Mensajeros;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }

    public boolean isEstatusVehiculoCollectionEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotEmpty(vehiculo.get(Vehiculo_.estatusVehiculoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstatusVehiculo> findEstatusVehiculoCollection(Vehiculo entity) {
        Vehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<EstatusVehiculo> estatusVehiculoCollection = mergedEntity.getEstatusVehiculoCollection();
        estatusVehiculoCollection.size();
        return estatusVehiculoCollection;
    }

    public boolean isTipoVehiculoCollectionEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotEmpty(vehiculo.get(Vehiculo_.tipoVehiculoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<TipoVehiculo> findTipoVehiculoCollection(Vehiculo entity) {
        Vehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<TipoVehiculo> tipoVehiculoCollection = mergedEntity.getTipoVehiculoCollection();
        tipoVehiculoCollection.size();
        return tipoVehiculoCollection;
    }

    public boolean isPaqueteCodPaqueteEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotNull(vehiculo.get(Vehiculo_.paqueteCodPaquete)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Paquete findPaqueteCodPaquete(Vehiculo entity) {
        return this.getMergedEntity(entity).getPaqueteCodPaquete();
    }

    public boolean isMensajerosCollectionEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotEmpty(vehiculo.get(Vehiculo_.mensajerosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Mensajeros> findMensajerosCollection(Vehiculo entity) {
        Vehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<Mensajeros> mensajerosCollection = mergedEntity.getMensajerosCollection();
        mensajerosCollection.size();
        return mensajerosCollection;
    }
    
}
