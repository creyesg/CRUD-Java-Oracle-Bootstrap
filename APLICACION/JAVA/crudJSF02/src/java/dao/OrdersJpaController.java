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
import entidades.StatusOrder;
import entidades.Envios;
import entidades.Orders;
import java.util.ArrayList;
import java.util.Collection;
import entidades.OrdersDet;
import entidades.TrackingT;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author naore
 */
public class OrdersJpaController implements Serializable {

    public OrdersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orders orders) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (orders.getEnviosCollection() == null) {
            orders.setEnviosCollection(new ArrayList<Envios>());
        }
        if (orders.getOrdersDetCollection() == null) {
            orders.setOrdersDetCollection(new ArrayList<OrdersDet>());
        }
        if (orders.getTrackingTCollection() == null) {
            orders.setTrackingTCollection(new ArrayList<TrackingT>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StatusOrder idStatus = orders.getIdStatus();
            if (idStatus != null) {
                idStatus = em.getReference(idStatus.getClass(), idStatus.getIdStatus());
                orders.setIdStatus(idStatus);
            }
            Collection<Envios> attachedEnviosCollection = new ArrayList<Envios>();
            for (Envios enviosCollectionEnviosToAttach : orders.getEnviosCollection()) {
                enviosCollectionEnviosToAttach = em.getReference(enviosCollectionEnviosToAttach.getClass(), enviosCollectionEnviosToAttach.getEnvioId());
                attachedEnviosCollection.add(enviosCollectionEnviosToAttach);
            }
            orders.setEnviosCollection(attachedEnviosCollection);
            Collection<OrdersDet> attachedOrdersDetCollection = new ArrayList<OrdersDet>();
            for (OrdersDet ordersDetCollectionOrdersDetToAttach : orders.getOrdersDetCollection()) {
                ordersDetCollectionOrdersDetToAttach = em.getReference(ordersDetCollectionOrdersDetToAttach.getClass(), ordersDetCollectionOrdersDetToAttach.getDetId());
                attachedOrdersDetCollection.add(ordersDetCollectionOrdersDetToAttach);
            }
            orders.setOrdersDetCollection(attachedOrdersDetCollection);
            Collection<TrackingT> attachedTrackingTCollection = new ArrayList<TrackingT>();
            for (TrackingT trackingTCollectionTrackingTToAttach : orders.getTrackingTCollection()) {
                trackingTCollectionTrackingTToAttach = em.getReference(trackingTCollectionTrackingTToAttach.getClass(), trackingTCollectionTrackingTToAttach.getTrackId());
                attachedTrackingTCollection.add(trackingTCollectionTrackingTToAttach);
            }
            orders.setTrackingTCollection(attachedTrackingTCollection);
            em.persist(orders);
            if (idStatus != null) {
                idStatus.getOrdersCollection().add(orders);
                idStatus = em.merge(idStatus);
            }
            for (Envios enviosCollectionEnvios : orders.getEnviosCollection()) {
                Orders oldOrderIdOfEnviosCollectionEnvios = enviosCollectionEnvios.getOrderId();
                enviosCollectionEnvios.setOrderId(orders);
                enviosCollectionEnvios = em.merge(enviosCollectionEnvios);
                if (oldOrderIdOfEnviosCollectionEnvios != null) {
                    oldOrderIdOfEnviosCollectionEnvios.getEnviosCollection().remove(enviosCollectionEnvios);
                    oldOrderIdOfEnviosCollectionEnvios = em.merge(oldOrderIdOfEnviosCollectionEnvios);
                }
            }
            for (OrdersDet ordersDetCollectionOrdersDet : orders.getOrdersDetCollection()) {
                Orders oldOrderIdOfOrdersDetCollectionOrdersDet = ordersDetCollectionOrdersDet.getOrderId();
                ordersDetCollectionOrdersDet.setOrderId(orders);
                ordersDetCollectionOrdersDet = em.merge(ordersDetCollectionOrdersDet);
                if (oldOrderIdOfOrdersDetCollectionOrdersDet != null) {
                    oldOrderIdOfOrdersDetCollectionOrdersDet.getOrdersDetCollection().remove(ordersDetCollectionOrdersDet);
                    oldOrderIdOfOrdersDetCollectionOrdersDet = em.merge(oldOrderIdOfOrdersDetCollectionOrdersDet);
                }
            }
            for (TrackingT trackingTCollectionTrackingT : orders.getTrackingTCollection()) {
                Orders oldOrderIdOfTrackingTCollectionTrackingT = trackingTCollectionTrackingT.getOrderId();
                trackingTCollectionTrackingT.setOrderId(orders);
                trackingTCollectionTrackingT = em.merge(trackingTCollectionTrackingT);
                if (oldOrderIdOfTrackingTCollectionTrackingT != null) {
                    oldOrderIdOfTrackingTCollectionTrackingT.getTrackingTCollection().remove(trackingTCollectionTrackingT);
                    oldOrderIdOfTrackingTCollectionTrackingT = em.merge(oldOrderIdOfTrackingTCollectionTrackingT);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrders(orders.getOrderId()) != null) {
                throw new PreexistingEntityException("Orders " + orders + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orders orders) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders persistentOrders = em.find(Orders.class, orders.getOrderId());
            StatusOrder idStatusOld = persistentOrders.getIdStatus();
            StatusOrder idStatusNew = orders.getIdStatus();
            Collection<Envios> enviosCollectionOld = persistentOrders.getEnviosCollection();
            Collection<Envios> enviosCollectionNew = orders.getEnviosCollection();
            Collection<OrdersDet> ordersDetCollectionOld = persistentOrders.getOrdersDetCollection();
            Collection<OrdersDet> ordersDetCollectionNew = orders.getOrdersDetCollection();
            Collection<TrackingT> trackingTCollectionOld = persistentOrders.getTrackingTCollection();
            Collection<TrackingT> trackingTCollectionNew = orders.getTrackingTCollection();
            if (idStatusNew != null) {
                idStatusNew = em.getReference(idStatusNew.getClass(), idStatusNew.getIdStatus());
                orders.setIdStatus(idStatusNew);
            }
            Collection<Envios> attachedEnviosCollectionNew = new ArrayList<Envios>();
            for (Envios enviosCollectionNewEnviosToAttach : enviosCollectionNew) {
                enviosCollectionNewEnviosToAttach = em.getReference(enviosCollectionNewEnviosToAttach.getClass(), enviosCollectionNewEnviosToAttach.getEnvioId());
                attachedEnviosCollectionNew.add(enviosCollectionNewEnviosToAttach);
            }
            enviosCollectionNew = attachedEnviosCollectionNew;
            orders.setEnviosCollection(enviosCollectionNew);
            Collection<OrdersDet> attachedOrdersDetCollectionNew = new ArrayList<OrdersDet>();
            for (OrdersDet ordersDetCollectionNewOrdersDetToAttach : ordersDetCollectionNew) {
                ordersDetCollectionNewOrdersDetToAttach = em.getReference(ordersDetCollectionNewOrdersDetToAttach.getClass(), ordersDetCollectionNewOrdersDetToAttach.getDetId());
                attachedOrdersDetCollectionNew.add(ordersDetCollectionNewOrdersDetToAttach);
            }
            ordersDetCollectionNew = attachedOrdersDetCollectionNew;
            orders.setOrdersDetCollection(ordersDetCollectionNew);
            Collection<TrackingT> attachedTrackingTCollectionNew = new ArrayList<TrackingT>();
            for (TrackingT trackingTCollectionNewTrackingTToAttach : trackingTCollectionNew) {
                trackingTCollectionNewTrackingTToAttach = em.getReference(trackingTCollectionNewTrackingTToAttach.getClass(), trackingTCollectionNewTrackingTToAttach.getTrackId());
                attachedTrackingTCollectionNew.add(trackingTCollectionNewTrackingTToAttach);
            }
            trackingTCollectionNew = attachedTrackingTCollectionNew;
            orders.setTrackingTCollection(trackingTCollectionNew);
            orders = em.merge(orders);
            if (idStatusOld != null && !idStatusOld.equals(idStatusNew)) {
                idStatusOld.getOrdersCollection().remove(orders);
                idStatusOld = em.merge(idStatusOld);
            }
            if (idStatusNew != null && !idStatusNew.equals(idStatusOld)) {
                idStatusNew.getOrdersCollection().add(orders);
                idStatusNew = em.merge(idStatusNew);
            }
            for (Envios enviosCollectionOldEnvios : enviosCollectionOld) {
                if (!enviosCollectionNew.contains(enviosCollectionOldEnvios)) {
                    enviosCollectionOldEnvios.setOrderId(null);
                    enviosCollectionOldEnvios = em.merge(enviosCollectionOldEnvios);
                }
            }
            for (Envios enviosCollectionNewEnvios : enviosCollectionNew) {
                if (!enviosCollectionOld.contains(enviosCollectionNewEnvios)) {
                    Orders oldOrderIdOfEnviosCollectionNewEnvios = enviosCollectionNewEnvios.getOrderId();
                    enviosCollectionNewEnvios.setOrderId(orders);
                    enviosCollectionNewEnvios = em.merge(enviosCollectionNewEnvios);
                    if (oldOrderIdOfEnviosCollectionNewEnvios != null && !oldOrderIdOfEnviosCollectionNewEnvios.equals(orders)) {
                        oldOrderIdOfEnviosCollectionNewEnvios.getEnviosCollection().remove(enviosCollectionNewEnvios);
                        oldOrderIdOfEnviosCollectionNewEnvios = em.merge(oldOrderIdOfEnviosCollectionNewEnvios);
                    }
                }
            }
            for (OrdersDet ordersDetCollectionOldOrdersDet : ordersDetCollectionOld) {
                if (!ordersDetCollectionNew.contains(ordersDetCollectionOldOrdersDet)) {
                    ordersDetCollectionOldOrdersDet.setOrderId(null);
                    ordersDetCollectionOldOrdersDet = em.merge(ordersDetCollectionOldOrdersDet);
                }
            }
            for (OrdersDet ordersDetCollectionNewOrdersDet : ordersDetCollectionNew) {
                if (!ordersDetCollectionOld.contains(ordersDetCollectionNewOrdersDet)) {
                    Orders oldOrderIdOfOrdersDetCollectionNewOrdersDet = ordersDetCollectionNewOrdersDet.getOrderId();
                    ordersDetCollectionNewOrdersDet.setOrderId(orders);
                    ordersDetCollectionNewOrdersDet = em.merge(ordersDetCollectionNewOrdersDet);
                    if (oldOrderIdOfOrdersDetCollectionNewOrdersDet != null && !oldOrderIdOfOrdersDetCollectionNewOrdersDet.equals(orders)) {
                        oldOrderIdOfOrdersDetCollectionNewOrdersDet.getOrdersDetCollection().remove(ordersDetCollectionNewOrdersDet);
                        oldOrderIdOfOrdersDetCollectionNewOrdersDet = em.merge(oldOrderIdOfOrdersDetCollectionNewOrdersDet);
                    }
                }
            }
            for (TrackingT trackingTCollectionOldTrackingT : trackingTCollectionOld) {
                if (!trackingTCollectionNew.contains(trackingTCollectionOldTrackingT)) {
                    trackingTCollectionOldTrackingT.setOrderId(null);
                    trackingTCollectionOldTrackingT = em.merge(trackingTCollectionOldTrackingT);
                }
            }
            for (TrackingT trackingTCollectionNewTrackingT : trackingTCollectionNew) {
                if (!trackingTCollectionOld.contains(trackingTCollectionNewTrackingT)) {
                    Orders oldOrderIdOfTrackingTCollectionNewTrackingT = trackingTCollectionNewTrackingT.getOrderId();
                    trackingTCollectionNewTrackingT.setOrderId(orders);
                    trackingTCollectionNewTrackingT = em.merge(trackingTCollectionNewTrackingT);
                    if (oldOrderIdOfTrackingTCollectionNewTrackingT != null && !oldOrderIdOfTrackingTCollectionNewTrackingT.equals(orders)) {
                        oldOrderIdOfTrackingTCollectionNewTrackingT.getTrackingTCollection().remove(trackingTCollectionNewTrackingT);
                        oldOrderIdOfTrackingTCollectionNewTrackingT = em.merge(oldOrderIdOfTrackingTCollectionNewTrackingT);
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
                Long id = orders.getOrderId();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
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
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getOrderId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            StatusOrder idStatus = orders.getIdStatus();
            if (idStatus != null) {
                idStatus.getOrdersCollection().remove(orders);
                idStatus = em.merge(idStatus);
            }
            Collection<Envios> enviosCollection = orders.getEnviosCollection();
            for (Envios enviosCollectionEnvios : enviosCollection) {
                enviosCollectionEnvios.setOrderId(null);
                enviosCollectionEnvios = em.merge(enviosCollectionEnvios);
            }
            Collection<OrdersDet> ordersDetCollection = orders.getOrdersDetCollection();
            for (OrdersDet ordersDetCollectionOrdersDet : ordersDetCollection) {
                ordersDetCollectionOrdersDet.setOrderId(null);
                ordersDetCollectionOrdersDet = em.merge(ordersDetCollectionOrdersDet);
            }
            Collection<TrackingT> trackingTCollection = orders.getTrackingTCollection();
            for (TrackingT trackingTCollectionTrackingT : trackingTCollection) {
                trackingTCollectionTrackingT.setOrderId(null);
                trackingTCollectionTrackingT = em.merge(trackingTCollectionTrackingT);
            }
            em.remove(orders);
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

    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
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

    public Orders findOrders(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
