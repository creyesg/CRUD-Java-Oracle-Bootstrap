/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Mensajeros;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Mensajeros_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.EstatusMensajero;
import com.envios.db12017.Vehiculo;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class MensajerosFacade extends AbstractFacade<Mensajeros> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajerosFacade() {
        super(Mensajeros.class);
    }

    public boolean isEstatusMensajeroCollectionEmpty(Mensajeros entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mensajeros> mensajeros = cq.from(Mensajeros.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(mensajeros, entity), cb.isNotEmpty(mensajeros.get(Mensajeros_.estatusMensajeroCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstatusMensajero> findEstatusMensajeroCollection(Mensajeros entity) {
        Mensajeros mergedEntity = this.getMergedEntity(entity);
        Collection<EstatusMensajero> estatusMensajeroCollection = mergedEntity.getEstatusMensajeroCollection();
        estatusMensajeroCollection.size();
        return estatusMensajeroCollection;
    }

    public boolean isVehiculoCodVehiculoEmpty(Mensajeros entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mensajeros> mensajeros = cq.from(Mensajeros.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(mensajeros, entity), cb.isNotNull(mensajeros.get(Mensajeros_.vehiculoCodVehiculo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Vehiculo findVehiculoCodVehiculo(Mensajeros entity) {
        return this.getMergedEntity(entity).getVehiculoCodVehiculo();
    }
    
}
