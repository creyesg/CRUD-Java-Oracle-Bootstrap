/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.PaisEmpresaTracking;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.PaisEmpresaTracking_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Tracking;

/**
 *
 * @author naore
 */
@Stateless
public class PaisEmpresaTrackingFacade extends AbstractFacade<PaisEmpresaTracking> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisEmpresaTrackingFacade() {
        super(PaisEmpresaTracking.class);
    }

    public boolean isTrackingCodTrackingEmpty(PaisEmpresaTracking entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PaisEmpresaTracking> paisEmpresaTracking = cq.from(PaisEmpresaTracking.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(paisEmpresaTracking, entity), cb.isNotNull(paisEmpresaTracking.get(PaisEmpresaTracking_.trackingCodTracking)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tracking findTrackingCodTracking(PaisEmpresaTracking entity) {
        return this.getMergedEntity(entity).getTrackingCodTracking();
    }
    
}
