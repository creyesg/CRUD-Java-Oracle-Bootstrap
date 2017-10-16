/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.EstatusDespacho;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.EstatusDespacho_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Despacho;

/**
 *
 * @author naore
 */
@Stateless
public class EstatusDespachoFacade extends AbstractFacade<EstatusDespacho> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusDespachoFacade() {
        super(EstatusDespacho.class);
    }

    public boolean isDespachoCodDespachoEmpty(EstatusDespacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstatusDespacho> estatusDespacho = cq.from(EstatusDespacho.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estatusDespacho, entity), cb.isNotNull(estatusDespacho.get(EstatusDespacho_.despachoCodDespacho)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Despacho findDespachoCodDespacho(EstatusDespacho entity) {
        return this.getMergedEntity(entity).getDespachoCodDespacho();
    }
    
}
