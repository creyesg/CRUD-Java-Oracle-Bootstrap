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
import entidades.StatusOrder;
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
public class StatusOrderJpaController implements Serializable {

    public StatusOrderJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StatusOrder statusOrder) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (statusOrder.getOrdersCollection() == null) {
            statusOrder.setOrdersCollection(new ArrayList<Orders>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Orders> attachedOrdersCollection = new ArrayList<Orders>();
            for (Orders ordersCollectionOrdersToAttach : statusOrder.getOrdersCollection()) {
                ordersCollectionOrdersToAttach = em.getReference(ordersCollectionOrdersToAttach.getClass(), ordersCollectionOrdersToAttach.getOrderId());
                attachedOrdersCollection.add(ordersCollectionOrdersToAttach);
            }
            statusOrder.setOrdersCollection(attachedOrdersCollection);
            em.persist(statusOrder);
            for (Orders ordersCollectionOrders : statusOrder.getOrdersCollection()) {
                StatusOrder oldIdStatusOfOrdersCollectionOrders = ordersCollectionOrders.getIdStatus();
                ordersCollectionOrders.setIdStatus(statusOrder);
                ordersCollectionOrders = em.merge(ordersCollectionOrders);
                if (oldIdStatusOfOrdersCollectionOrders != null) {
                    oldIdStatusOfOrdersCollectionOrders.getOrdersCollection().remove(ordersCollectionOrders);
                    oldIdStatusOfOrdersCollectionOrders = em.merge(oldIdStatusOfOrdersCollectionOrders);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStatusOrder(statusOrder.getIdStatus()) != null) {
                throw new PreexistingEntityException("StatusOrder " + statusOrder + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StatusOrder statusOrder) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StatusOrder persistentStatusOrder = em.find(StatusOrder.class, statusOrder.getIdStatus());
            Collection<Orders> ordersCollectionOld = persistentStatusOrder.getOrdersCollection();
            Collection<Orders> ordersCollectionNew = statusOrder.getOrdersCollection();
            Collection<Orders> attachedOrdersCollectionNew = new ArrayList<Orders>();
            for (Orders ordersCollectionNewOrdersToAttach : ordersCollectionNew) {
                ordersCollectionNewOrdersToAttach = em.getReference(ordersCollectionNewOrdersToAttach.getClass(), ordersCollectionNewOrdersToAttach.getOrderId());
                attachedOrdersCollectionNew.add(ordersCollectionNewOrdersToAttach);
            }
            ordersCollectionNew = attachedOrdersCollectionNew;
            statusOrder.setOrdersCollection(ordersCollectionNew);
            statusOrder = em.merge(statusOrder);
            for (Orders ordersCollectionOldOrders : ordersCollectionOld) {
                if (!ordersCollectionNew.contains(ordersCollectionOldOrders)) {
                    ordersCollectionOldOrders.setIdStatus(null);
                    ordersCollectionOldOrders = em.merge(ordersCollectionOldOrders);
                }
            }
            for (Orders ordersCollectionNewOrders : ordersCollectionNew) {
                if (!ordersCollectionOld.contains(ordersCollectionNewOrders)) {
                    StatusOrder oldIdStatusOfOrdersCollectionNewOrders = ordersCollectionNewOrders.getIdStatus();
                    ordersCollectionNewOrders.setIdStatus(statusOrder);
                    ordersCollectionNewOrders = em.merge(ordersCollectionNewOrders);
                    if (oldIdStatusOfOrdersCollectionNewOrders != null && !oldIdStatusOfOrdersCollectionNewOrders.equals(statusOrder)) {
                        oldIdStatusOfOrdersCollectionNewOrders.getOrdersCollection().remove(ordersCollectionNewOrders);
                        oldIdStatusOfOrdersCollectionNewOrders = em.merge(oldIdStatusOfOrdersCollectionNewOrders);
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
                Long id = statusOrder.getIdStatus();
                if (findStatusOrder(id) == null) {
                    throw new NonexistentEntityException("The statusOrder with id " + id + " no longer exists.");
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
            StatusOrder statusOrder;
            try {
                statusOrder = em.getReference(StatusOrder.class, id);
                statusOrder.getIdStatus();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statusOrder with id " + id + " no longer exists.", enfe);
            }
            Collection<Orders> ordersCollection = statusOrder.getOrdersCollection();
            for (Orders ordersCollectionOrders : ordersCollection) {
                ordersCollectionOrders.setIdStatus(null);
                ordersCollectionOrders = em.merge(ordersCollectionOrders);
            }
            em.remove(statusOrder);
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

    public List<StatusOrder> findStatusOrderEntities() {
        return findStatusOrderEntities(true, -1, -1);
    }

    public List<StatusOrder> findStatusOrderEntities(int maxResults, int firstResult) {
        return findStatusOrderEntities(false, maxResults, firstResult);
    }

    private List<StatusOrder> findStatusOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StatusOrder.class));
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

    public StatusOrder findStatusOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StatusOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatusOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StatusOrder> rt = cq.from(StatusOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
