/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.TipoVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.TipoVehiculo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Vehiculo;

/**
 *
 * @author naore
 */
@Stateless
public class TipoVehiculoFacade extends AbstractFacade<TipoVehiculo> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoVehiculoFacade() {
        super(TipoVehiculo.class);
    }

    public boolean isVehiculoCodVehiculoEmpty(TipoVehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoVehiculo> tipoVehiculo = cq.from(TipoVehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoVehiculo, entity), cb.isNotNull(tipoVehiculo.get(TipoVehiculo_.vehiculoCodVehiculo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Vehiculo findVehiculoCodVehiculo(TipoVehiculo entity) {
        return this.getMergedEntity(entity).getVehiculoCodVehiculo();
    }
    
}
