/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Paquete;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Paquete_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Vehiculo;
import com.envios.db12017.EstatusPaquete;
import com.envios.db12017.Envios;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class PaqueteFacade extends AbstractFacade<Paquete> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaqueteFacade() {
        super(Paquete.class);
    }

    public boolean isVehiculoCollectionEmpty(Paquete entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Paquete> paquete = cq.from(Paquete.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(paquete, entity), cb.isNotEmpty(paquete.get(Paquete_.vehiculoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Vehiculo> findVehiculoCollection(Paquete entity) {
        Paquete mergedEntity = this.getMergedEntity(entity);
        Collection<Vehiculo> vehiculoCollection = mergedEntity.getVehiculoCollection();
        vehiculoCollection.size();
        return vehiculoCollection;
    }

    public boolean isEstatusPaqueteCollectionEmpty(Paquete entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Paquete> paquete = cq.from(Paquete.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(paquete, entity), cb.isNotEmpty(paquete.get(Paquete_.estatusPaqueteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstatusPaquete> findEstatusPaqueteCollection(Paquete entity) {
        Paquete mergedEntity = this.getMergedEntity(entity);
        Collection<EstatusPaquete> estatusPaqueteCollection = mergedEntity.getEstatusPaqueteCollection();
        estatusPaqueteCollection.size();
        return estatusPaqueteCollection;
    }

    public boolean isEnviosEmpty(Paquete entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Paquete> paquete = cq.from(Paquete.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(paquete, entity), cb.isNotNull(paquete.get(Paquete_.envios)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Envios findEnvios(Paquete entity) {
        return this.getMergedEntity(entity).getEnvios();
    }
    
}
