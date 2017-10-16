/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Despacho;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Despacho_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Envios;
import com.envios.db12017.EstatusDespacho;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class DespachoFacade extends AbstractFacade<Despacho> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DespachoFacade() {
        super(Despacho.class);
    }

    public boolean isEnviosCollectionEmpty(Despacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Despacho> despacho = cq.from(Despacho.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(despacho, entity), cb.isNotEmpty(despacho.get(Despacho_.enviosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Envios> findEnviosCollection(Despacho entity) {
        Despacho mergedEntity = this.getMergedEntity(entity);
        Collection<Envios> enviosCollection = mergedEntity.getEnviosCollection();
        enviosCollection.size();
        return enviosCollection;
    }

    public boolean isEstatusDespachoCollectionEmpty(Despacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Despacho> despacho = cq.from(Despacho.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(despacho, entity), cb.isNotEmpty(despacho.get(Despacho_.estatusDespachoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstatusDespacho> findEstatusDespachoCollection(Despacho entity) {
        Despacho mergedEntity = this.getMergedEntity(entity);
        Collection<EstatusDespacho> estatusDespachoCollection = mergedEntity.getEstatusDespachoCollection();
        estatusDespachoCollection.size();
        return estatusDespachoCollection;
    }
    
}
