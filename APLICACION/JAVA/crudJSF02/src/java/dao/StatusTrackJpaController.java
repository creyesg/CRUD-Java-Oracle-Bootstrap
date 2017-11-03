/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entidades.StatusTrack;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.TrackingT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author naore
 */
public class StatusTrackJpaController implements Serializable {

    public StatusTrackJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StatusTrack statusTrack) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (statusTrack.getTrackingTCollection() == null) {
            statusTrack.setTrackingTCollection(new ArrayList<TrackingT>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<TrackingT> attachedTrackingTCollection = new ArrayList<TrackingT>();
            for (TrackingT trackingTCollectionTrackingTToAttach : statusTrack.getTrackingTCollection()) {
                trackingTCollectionTrackingTToAttach = em.getReference(trackingTCollectionTrackingTToAttach.getClass(), trackingTCollectionTrackingTToAttach.getTrackId());
                attachedTrackingTCollection.add(trackingTCollectionTrackingTToAttach);
            }
            statusTrack.setTrackingTCollection(attachedTrackingTCollection);
            em.persist(statusTrack);
            for (TrackingT trackingTCollectionTrackingT : statusTrack.getTrackingTCollection()) {
                StatusTrack oldIdStatusOfTrackingTCollectionTrackingT = trackingTCollectionTrackingT.getIdStatus();
                trackingTCollectionTrackingT.setIdStatus(statusTrack);
                trackingTCollectionTrackingT = em.merge(trackingTCollectionTrackingT);
                if (oldIdStatusOfTrackingTCollectionTrackingT != null) {
                    oldIdStatusOfTrackingTCollectionTrackingT.getTrackingTCollection().remove(trackingTCollectionTrackingT);
                    oldIdStatusOfTrackingTCollectionTrackingT = em.merge(oldIdStatusOfTrackingTCollectionTrackingT);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStatusTrack(statusTrack.getIdStatus()) != null) {
                throw new PreexistingEntityException("StatusTrack " + statusTrack + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StatusTrack statusTrack) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StatusTrack persistentStatusTrack = em.find(StatusTrack.class, statusTrack.getIdStatus());
            Collection<TrackingT> trackingTCollectionOld = persistentStatusTrack.getTrackingTCollection();
            Collection<TrackingT> trackingTCollectionNew = statusTrack.getTrackingTCollection();
            Collection<TrackingT> attachedTrackingTCollectionNew = new ArrayList<TrackingT>();
            for (TrackingT trackingTCollectionNewTrackingTToAttach : trackingTCollectionNew) {
                trackingTCollectionNewTrackingTToAttach = em.getReference(trackingTCollectionNewTrackingTToAttach.getClass(), trackingTCollectionNewTrackingTToAttach.getTrackId());
                attachedTrackingTCollectionNew.add(trackingTCollectionNewTrackingTToAttach);
            }
            trackingTCollectionNew = attachedTrackingTCollectionNew;
            statusTrack.setTrackingTCollection(trackingTCollectionNew);
            statusTrack = em.merge(statusTrack);
            for (TrackingT trackingTCollectionOldTrackingT : trackingTCollectionOld) {
                if (!trackingTCollectionNew.contains(trackingTCollectionOldTrackingT)) {
                    trackingTCollectionOldTrackingT.setIdStatus(null);
                    trackingTCollectionOldTrackingT = em.merge(trackingTCollectionOldTrackingT);
                }
            }
            for (TrackingT trackingTCollectionNewTrackingT : trackingTCollectionNew) {
                if (!trackingTCollectionOld.contains(trackingTCollectionNewTrackingT)) {
                    StatusTrack oldIdStatusOfTrackingTCollectionNewTrackingT = trackingTCollectionNewTrackingT.getIdStatus();
                    trackingTCollectionNewTrackingT.setIdStatus(statusTrack);
                    trackingTCollectionNewTrackingT = em.merge(trackingTCollectionNewTrackingT);
                    if (oldIdStatusOfTrackingTCollectionNewTrackingT != null && !oldIdStatusOfTrackingTCollectionNewTrackingT.equals(statusTrack)) {
                        oldIdStatusOfTrackingTCollectionNewTrackingT.getTrackingTCollection().remove(trackingTCollectionNewTrackingT);
                        oldIdStatusOfTrackingTCollectionNewTrackingT = em.merge(oldIdStatusOfTrackingTCollectionNewTrackingT);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = statusTrack.getIdStatus();
                if (findStatusTrack(id) == null) {
                    throw new NonexistentEntityException("The statusTrack with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StatusTrack statusTrack;
            try {
                statusTrack = em.getReference(StatusTrack.class, id);
                statusTrack.getIdStatus();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statusTrack with id " + id + " no longer exists.", enfe);
            }
            Collection<TrackingT> trackingTCollection = statusTrack.getTrackingTCollection();
            for (TrackingT trackingTCollectionTrackingT : trackingTCollection) {
                trackingTCollectionTrackingT.setIdStatus(null);
                trackingTCollectionTrackingT = em.merge(trackingTCollectionTrackingT);
            }
            em.remove(statusTrack);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StatusTrack> findStatusTrackEntities() {
        return findStatusTrackEntities(true, -1, -1);
    }

    public List<StatusTrack> findStatusTrackEntities(int maxResults, int firstResult) {
        return findStatusTrackEntities(false, maxResults, firstResult);
    }

    private List<StatusTrack> findStatusTrackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StatusTrack.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public StatusTrack findStatusTrack(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StatusTrack.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatusTrackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StatusTrack> rt = cq.from(StatusTrack.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
