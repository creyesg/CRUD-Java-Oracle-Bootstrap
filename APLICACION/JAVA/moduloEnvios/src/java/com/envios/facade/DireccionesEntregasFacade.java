/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.DireccionesEntregas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.DireccionesEntregas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Entrega;

/**
 *
 * @author naore
 */
@Stateless
public class DireccionesEntregasFacade extends AbstractFacade<DireccionesEntregas> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionesEntregasFacade() {
        super(DireccionesEntregas.class);
    }

    public boolean isEntregaCodEntregaEmpty(DireccionesEntregas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DireccionesEntregas> direccionesEntregas = cq.from(DireccionesEntregas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(direccionesEntregas, entity), cb.isNotNull(direccionesEntregas.get(DireccionesEntregas_.entregaCodEntrega)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Entrega findEntregaCodEntrega(DireccionesEntregas entity) {
        return this.getMergedEntity(entity).getEntregaCodEntrega();
    }
    
}
