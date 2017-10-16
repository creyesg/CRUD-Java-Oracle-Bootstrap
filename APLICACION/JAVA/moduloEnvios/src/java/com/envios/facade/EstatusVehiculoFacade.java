/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.envios.facade;

import com.envios.db12017.EstatusVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.envios.db12017.EstatusVehiculo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.envios.db12017.Vehiculo;

/**
 *
 * @author naore
 */
@Stateless
public class EstatusVehiculoFacade extends AbstractFacade<EstatusVehiculo> {

    @PersistenceContext(unitName = "moduloEnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusVehiculoFacade() {
        super(EstatusVehiculo.class);
    }

    public boolean isVehiculoCodVehiculoEmpty(EstatusVehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstatusVehiculo> estatusVehiculo = cq.from(EstatusVehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estatusVehiculo, entity), cb.isNotNull(estatusVehiculo.get(EstatusVehiculo_.vehiculoCodVehiculo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Vehiculo findVehiculoCodVehiculo(EstatusVehiculo entity) {
        return this.getMergedEntity(entity).getVehiculoCodVehiculo();
    }
    
}
