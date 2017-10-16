/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.EstatusEntrega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.EstatusEntrega_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Entrega;

/**
 *
 * @author naore
 */
@Stateless
public class EstatusEntregaFacade extends AbstractFacade<EstatusEntrega> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusEntregaFacade() {
        super(EstatusEntrega.class);
    }

    public boolean isEntregaCodEntregaEmpty(EstatusEntrega entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstatusEntrega> estatusEntrega = cq.from(EstatusEntrega.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estatusEntrega, entity), cb.isNotNull(estatusEntrega.get(EstatusEntrega_.entregaCodEntrega)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Entrega findEntregaCodEntrega(EstatusEntrega entity) {
        return this.getMergedEntity(entity).getEntregaCodEntrega();
    }
    
}
