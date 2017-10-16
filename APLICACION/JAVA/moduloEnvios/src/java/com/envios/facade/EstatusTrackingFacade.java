/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.EstatusTracking;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.EstatusTracking_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Tracking;

/**
 *
 * @author naore
 */
@Stateless
public class EstatusTrackingFacade extends AbstractFacade<EstatusTracking> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusTrackingFacade() {
        super(EstatusTracking.class);
    }

    public boolean isTrackingCodTrackingEmpty(EstatusTracking entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstatusTracking> estatusTracking = cq.from(EstatusTracking.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estatusTracking, entity), cb.isNotNull(estatusTracking.get(EstatusTracking_.trackingCodTracking)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tracking findTrackingCodTracking(EstatusTracking entity) {
        return this.getMergedEntity(entity).getTrackingCodTracking();
    }
    
}
