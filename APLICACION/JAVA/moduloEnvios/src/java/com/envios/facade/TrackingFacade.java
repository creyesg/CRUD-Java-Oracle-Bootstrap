/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Tracking;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Tracking_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.PaisEmpresaTracking;
import com.envios.db12017.EstatusTracking;
import com.envios.db12017.Envios;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class TrackingFacade extends AbstractFacade<Tracking> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrackingFacade() {
        super(Tracking.class);
    }

    public boolean isPaisEmpresaTrackingCollectionEmpty(Tracking entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tracking> tracking = cq.from(Tracking.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tracking, entity), cb.isNotEmpty(tracking.get(Tracking_.paisEmpresaTrackingCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<PaisEmpresaTracking> findPaisEmpresaTrackingCollection(Tracking entity) {
        Tracking mergedEntity = this.getMergedEntity(entity);
        Collection<PaisEmpresaTracking> paisEmpresaTrackingCollection = mergedEntity.getPaisEmpresaTrackingCollection();
        paisEmpresaTrackingCollection.size();
        return paisEmpresaTrackingCollection;
    }

    public boolean isEstatusTrackingCollectionEmpty(Tracking entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tracking> tracking = cq.from(Tracking.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tracking, entity), cb.isNotEmpty(tracking.get(Tracking_.estatusTrackingCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstatusTracking> findEstatusTrackingCollection(Tracking entity) {
        Tracking mergedEntity = this.getMergedEntity(entity);
        Collection<EstatusTracking> estatusTrackingCollection = mergedEntity.getEstatusTrackingCollection();
        estatusTrackingCollection.size();
        return estatusTrackingCollection;
    }

    public boolean isEnviosEmpty(Tracking entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tracking> tracking = cq.from(Tracking.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tracking, entity), cb.isNotNull(tracking.get(Tracking_.envios)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Envios findEnvios(Tracking entity) {
        return this.getMergedEntity(entity).getEnvios();
    }
    
}
