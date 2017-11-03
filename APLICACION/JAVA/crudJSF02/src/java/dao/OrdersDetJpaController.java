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
import entidades.OrdersDet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author naore
 */
public class OrdersDetJpaController implements Serializable {

    public OrdersDetJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdersDet ordersDet) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders orderId = ordersDet.getOrderId();
            if (orderId != null) {
                orderId = em.getReference(orderId.getClass(), orderId.getOrderId());
                ordersDet.setOrderId(orderId);
            }
            em.persist(ordersDet);
            if (orderId != null) {
                orderId.getOrdersDetCollection().add(ordersDet);
                orderId = em.merge(orderId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrdersDet(ordersDet.getDetId()) != null) {
                throw new PreexistingEntityException("OrdersDet " + ordersDet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdersDet ordersDet) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdersDet persistentOrdersDet = em.find(OrdersDet.class, ordersDet.getDetId());
            Orders orderIdOld = persistentOrdersDet.getOrderId();
            Orders orderIdNew = ordersDet.getOrderId();
            if (orderIdNew != null) {
                orderIdNew = em.getReference(orderIdNew.getClass(), orderIdNew.getOrderId());
                ordersDet.setOrderId(orderIdNew);
            }
            ordersDet = em.merge(ordersDet);
            if (orderIdOld != null && !orderIdOld.equals(orderIdNew)) {
                orderIdOld.getOrdersDetCollection().remove(ordersDet);
                orderIdOld = em.merge(orderIdOld);
            }
            if (orderIdNew != null && !orderIdNew.equals(orderIdOld)) {
                orderIdNew.getOrdersDetCollection().add(ordersDet);
                orderIdNew = em.merge(orderIdNew);
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
                Long id = ordersDet.getDetId();
                if (findOrdersDet(id) == null) {
                    throw new NonexistentEntityException("The ordersDet with id " + id + " no longer exists.");
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
            OrdersDet ordersDet;
            try {
                ordersDet = em.getReference(OrdersDet.class, id);
                ordersDet.getDetId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordersDet with id " + id + " no longer exists.", enfe);
            }
            Orders orderId = ordersDet.getOrderId();
            if (orderId != null) {
                orderId.getOrdersDetCollection().remove(ordersDet);
                orderId = em.merge(orderId);
            }
            em.remove(ordersDet);
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

    public List<OrdersDet> findOrdersDetEntities() {
        return findOrdersDetEntities(true, -1, -1);
    }

    public List<OrdersDet> findOrdersDetEntities(int maxResults, int firstResult) {
        return findOrdersDetEntities(false, maxResults, firstResult);
    }

    private List<OrdersDet> findOrdersDetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdersDet.class));
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

    public OrdersDet findOrdersDet(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdersDet.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersDetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdersDet> rt = cq.from(OrdersDet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
