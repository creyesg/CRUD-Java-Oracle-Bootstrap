/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Envios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Envios_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Despacho;
import com.envios.db12017.Entrega;
import com.envios.db12017.Tracking;
import com.envios.db12017.Paquete;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class EnviosFacade extends AbstractFacade<Envios> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnviosFacade() {
        super(Envios.class);
    }

    public boolean isDespachoCodDespachoEmpty(Envios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Envios> envios = cq.from(Envios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(envios, entity), cb.isNotNull(envios.get(Envios_.despachoCodDespacho)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Despacho findDespachoCodDespacho(Envios entity) {
        return this.getMergedEntity(entity).getDespachoCodDespacho();
    }

    public boolean isEntregaCollectionEmpty(Envios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Envios> envios = cq.from(Envios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(envios, entity), cb.isNotEmpty(envios.get(Envios_.entregaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Entrega> findEntregaCollection(Envios entity) {
        Envios mergedEntity = this.getMergedEntity(entity);
        Collection<Entrega> entregaCollection = mergedEntity.getEntregaCollection();
        entregaCollection.size();
        return entregaCollection;
    }

    public boolean isTrackingCollectionEmpty(Envios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Envios> envios = cq.from(Envios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(envios, entity), cb.isNotEmpty(envios.get(Envios_.trackingCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Tracking> findTrackingCollection(Envios entity) {
        Envios mergedEntity = this.getMergedEntity(entity);
        Collection<Tracking> trackingCollection = mergedEntity.getTrackingCollection();
        trackingCollection.size();
        return trackingCollection;
    }

    public boolean isPaqueteCollectionEmpty(Envios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Envios> envios = cq.from(Envios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(envios, entity), cb.isNotEmpty(envios.get(Envios_.paqueteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Paquete> findPaqueteCollection(Envios entity) {
        Envios mergedEntity = this.getMergedEntity(entity);
        Collection<Paquete> paqueteCollection = mergedEntity.getPaqueteCollection();
        paqueteCollection.size();
        return paqueteCollection;
    }
    
}
