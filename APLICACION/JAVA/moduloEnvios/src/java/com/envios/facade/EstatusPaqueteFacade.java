/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.EstatusPaquete;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.EstatusPaquete_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Paquete;

/**
 *
 * @author naore
 */
@Stateless
public class EstatusPaqueteFacade extends AbstractFacade<EstatusPaquete> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusPaqueteFacade() {
        super(EstatusPaquete.class);
    }

    public boolean isPaqueteCodPaqueteEmpty(EstatusPaquete entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstatusPaquete> estatusPaquete = cq.from(EstatusPaquete.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estatusPaquete, entity), cb.isNotNull(estatusPaquete.get(EstatusPaquete_.paqueteCodPaquete)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Paquete findPaqueteCodPaquete(EstatusPaquete entity) {
        return this.getMergedEntity(entity).getPaqueteCodPaquete();
    }
    
}
