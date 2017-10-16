/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.EstatusMensajero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.EstatusMensajero_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Mensajeros;

/**
 *
 * @author naore
 */
@Stateless
public class EstatusMensajeroFacade extends AbstractFacade<EstatusMensajero> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusMensajeroFacade() {
        super(EstatusMensajero.class);
    }

    public boolean isMensajerosCodMensajeroEmpty(EstatusMensajero entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstatusMensajero> estatusMensajero = cq.from(EstatusMensajero.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estatusMensajero, entity), cb.isNotNull(estatusMensajero.get(EstatusMensajero_.mensajerosCodMensajero)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Mensajeros findMensajerosCodMensajero(EstatusMensajero entity) {
        return this.getMergedEntity(entity).getMensajerosCodMensajero();
    }
    
}
