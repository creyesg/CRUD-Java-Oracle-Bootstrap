/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Orders;
import entidades.StatusTrack;
import entidades.TrackingT;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author naore
 */
public class TrackingTJpaController implements Serializable {

    public TrackingTJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TrackingT trackingT) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders orderId = trackingT.getOrderId();
            if (orderId != null) {
                orderId = em.getReference(orderId.getClass(), orderId.getOrderId());
                trackingT.setOrderId(orderId);
            }
            StatusTrack idStatus = trackingT.getIdStatus();
            if (idStatus != null) {
                idStatus = em.getReference(idStatus.getClass(), idStatus.getIdStatus());
                trackingT.setIdStatus(idStatus);
            }
            em.persist(trackingT);
            if (orderId != null) {
                orderId.getTrackingTCollection().add(trackingT);
                orderId = em.merge(orderId);
            }
            if (idStatus != null) {
                idStatus.getTrackingTCollection().add(trackingT);
                idStatus = em.merge(idStatus);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTrackingT(trackingT.getTrackId()) != null) {
                throw new PreexistingEntityException("TrackingT " + trackingT + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TrackingT trackingT) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TrackingT persistentTrackingT = em.find(TrackingT.class, trackingT.getTrackId());
            Orders orderIdOld = persistentTrackingT.getOrderId();
            Orders orderIdNew = trackingT.getOrderId();
            StatusTrack idStatusOld = persistentTrackingT.getIdStatus();
            StatusTrack idStatusNew = trackingT.getIdStatus();
            if (orderIdNew != null) {
                orderIdNew = em.getReference(orderIdNew.getClass(), orderIdNew.getOrderId());
                trackingT.setOrderId(orderIdNew);
            }
            if (idStatusNew != null) {
                idStatusNew = em.getReference(idStatusNew.getClass(), idStatusNew.getIdStatus());
                trackingT.setIdStatus(idStatusNew);
            }
            trackingT = em.merge(trackingT);
            if (orderIdOld != null && !orderIdOld.equals(orderIdNew)) {
                orderIdOld.getTrackingTCollection().remove(trackingT);
                orderIdOld = em.merge(orderIdOld);
            }
            if (orderIdNew != null && !orderIdNew.equals(orderIdOld)) {
                orderIdNew.getTrackingTCollection().add(trackingT);
                orderIdNew = em.merge(orderIdNew);
            }
            if (idStatusOld != null && !idStatusOld.equals(idStatusNew)) {
                idStatusOld.getTrackingTCollection().remove(trackingT);
                idStatusOld = em.merge(idStatusOld);
            }
            if (idStatusNew != null && !idStatusNew.equals(idStatusOld)) {
                idStatusNew.getTrackingTCollection().add(trackingT);
                idStatusNew = em.merge(idStatusNew);
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
                Long id = trackingT.getTrackId();
                if (findTrackingT(id) == null) {
                    throw new NonexistentEntityException("The trackingT with id " + id + " no longer exists.");
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
            TrackingT trackingT;
            try {
                trackingT = em.getReference(TrackingT.class, id);
                trackingT.getTrackId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trackingT with id " + id + " no longer exists.", enfe);
            }
            Orders orderId = trackingT.getOrderId();
            if (orderId != null) {
                orderId.getTrackingTCollection().remove(trackingT);
                orderId = em.merge(orderId);
            }
            StatusTrack idStatus = trackingT.getIdStatus();
            if (idStatus != null) {
                idStatus.getTrackingTCollection().remove(trackingT);
                idStatus = em.merge(idStatus);
            }
            em.remove(trackingT);
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

    public List<TrackingT> findTrackingTEntities() {
        return findTrackingTEntities(true, -1, -1);
    }

    public List<TrackingT> findTrackingTEntities(int maxResults, int firstResult) {
        return findTrackingTEntities(false, maxResults, firstResult);
    }

    private List<TrackingT> findTrackingTEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TrackingT.class));
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

    public TrackingT findTrackingT(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TrackingT.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrackingTCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TrackingT> rt = cq.from(TrackingT.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
