/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.sessions;

import entidades.StatusTrack;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author naore
 */
@Stateless
public class StatusTrackFacade extends AbstractFacade<StatusTrack> {

    @PersistenceContext(unitName = "crudJSF02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusTrackFacade() {
        super(StatusTrack.class);
    }
    
}