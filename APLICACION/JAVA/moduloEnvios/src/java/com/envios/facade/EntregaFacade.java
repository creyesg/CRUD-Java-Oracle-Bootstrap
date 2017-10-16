/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.Entrega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.Entrega_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.EstatusEntrega;
import com.envios.db12017.DireccionesEntregas;
import com.envios.db12017.Envios;
import java.util.Collection;

/**
 *
 * @author naore
 */
@Stateless
public class EntregaFacade extends AbstractFacade<Entrega> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntregaFacade() {
        super(Entrega.class);
    }

    public boolean isEstatusEntregaCollectionEmpty(Entrega entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Entrega> entrega = cq.from(Entrega.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(entrega, entity), cb.isNotEmpty(entrega.get(Entrega_.estatusEntregaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstatusEntrega> findEstatusEntregaCollection(Entrega entity) {
        Entrega mergedEntity = this.getMergedEntity(entity);
        Collection<EstatusEntrega> estatusEntregaCollection = mergedEntity.getEstatusEntregaCollection();
        estatusEntregaCollection.size();
        return estatusEntregaCollection;
    }

    public boolean isDireccionesEntregasCollectionEmpty(Entrega entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Entrega> entrega = cq.from(Entrega.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(entrega, entity), cb.isNotEmpty(entrega.get(Entrega_.direccionesEntregasCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<DireccionesEntregas> findDireccionesEntregasCollection(Entrega entity) {
        Entrega mergedEntity = this.getMergedEntity(entity);
        Collection<DireccionesEntregas> direccionesEntregasCollection = mergedEntity.getDireccionesEntregasCollection();
        direccionesEntregasCollection.size();
        return direccionesEntregasCollection;
    }

    public boolean isEnviosEmpty(Entrega entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Entrega> entrega = cq.from(Entrega.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(entrega, entity), cb.isNotNull(entrega.get(Entrega_.envios)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Envios findEnvios(Entrega entity) {
        return this.getMergedEntity(entity).getEnvios();
    }
    
}
