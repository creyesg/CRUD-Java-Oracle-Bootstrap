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
import entidades.EmpresasEnvio;
import entidades.Envios;
import entidades.EnviosInternos;
import entidades.Orders;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author naore
 */
public class EnviosJpaController implements Serializable {

    public EnviosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Envios envios) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmpresasEnvio idEmpresa = envios.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                envios.setIdEmpresa(idEmpresa);
            }
            EnviosInternos codEmpInterno = envios.getCodEmpInterno();
            if (codEmpInterno != null) {
                codEmpInterno = em.getReference(codEmpInterno.getClass(), codEmpInterno.getCodEmpleado());
                envios.setCodEmpInterno(codEmpInterno);
            }
            Orders orderId = envios.getOrderId();
            if (orderId != null) {
                orderId = em.getReference(orderId.getClass(), orderId.getOrderId());
                envios.setOrderId(orderId);
            }
            em.persist(envios);
            if (idEmpresa != null) {
                idEmpresa.getEnviosCollection().add(envios);
                idEmpresa = em.merge(idEmpresa);
            }
            if (codEmpInterno != null) {
                codEmpInterno.getEnviosCollection().add(envios);
                codEmpInterno = em.merge(codEmpInterno);
            }
            if (orderId != null) {
                orderId.getEnviosCollection().add(envios);
                orderId = em.merge(orderId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEnvios(envios.getEnvioId()) != null) {
                throw new PreexistingEntityException("Envios " + envios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Envios envios) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Envios persistentEnvios = em.find(Envios.class, envios.getEnvioId());
            EmpresasEnvio idEmpresaOld = persistentEnvios.getIdEmpresa();
            EmpresasEnvio idEmpresaNew = envios.getIdEmpresa();
            EnviosInternos codEmpInternoOld = persistentEnvios.getCodEmpInterno();
            EnviosInternos codEmpInternoNew = envios.getCodEmpInterno();
            Orders orderIdOld = persistentEnvios.getOrderId();
            Orders orderIdNew = envios.getOrderId();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                envios.setIdEmpresa(idEmpresaNew);
            }
            if (codEmpInternoNew != null) {
                codEmpInternoNew = em.getReference(codEmpInternoNew.getClass(), codEmpInternoNew.getCodEmpleado());
                envios.setCodEmpInterno(codEmpInternoNew);
            }
            if (orderIdNew != null) {
                orderIdNew = em.getReference(orderIdNew.getClass(), orderIdNew.getOrderId());
                envios.setOrderId(orderIdNew);
            }
            envios = em.merge(envios);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getEnviosCollection().remove(envios);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getEnviosCollection().add(envios);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (codEmpInternoOld != null && !codEmpInternoOld.equals(codEmpInternoNew)) {
                codEmpInternoOld.getEnviosCollection().remove(envios);
                codEmpInternoOld = em.merge(codEmpInternoOld);
            }
            if (codEmpInternoNew != null && !codEmpInternoNew.equals(codEmpInternoOld)) {
                codEmpInternoNew.getEnviosCollection().add(envios);
                codEmpInternoNew = em.merge(codEmpInternoNew);
            }
            if (orderIdOld != null && !orderIdOld.equals(orderIdNew)) {
                orderIdOld.getEnviosCollection().remove(envios);
                orderIdOld = em.merge(orderIdOld);
            }
            if (orderIdNew != null && !orderIdNew.equals(orderIdOld)) {
                orderIdNew.getEnviosCollection().add(envios);
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
                Long id = envios.getEnvioId();
                if (findEnvios(id) == null) {
                    throw new NonexistentEntityException("The envios with id " + id + " no longer exists.");
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
            Envios envios;
            try {
                envios = em.getReference(Envios.class, id);
                envios.getEnvioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The envios with id " + id + " no longer exists.", enfe);
            }
            EmpresasEnvio idEmpresa = envios.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getEnviosCollection().remove(envios);
                idEmpresa = em.merge(idEmpresa);
            }
            EnviosInternos codEmpInterno = envios.getCodEmpInterno();
            if (codEmpInterno != null) {
                codEmpInterno.getEnviosCollection().remove(envios);
                codEmpInterno = em.merge(codEmpInterno);
            }
            Orders orderId = envios.getOrderId();
            if (orderId != null) {
                orderId.getEnviosCollection().remove(envios);
                orderId = em.merge(orderId);
            }
            em.remove(envios);
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

    public List<Envios> findEnviosEntities() {
        return findEnviosEntities(true, -1, -1);
    }

    public List<Envios> findEnviosEntities(int maxResults, int firstResult) {
        return findEnviosEntities(false, maxResults, firstResult);
    }

    private List<Envios> findEnviosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Envios.class));
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

    public Envios findEnvios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Envios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnviosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Envios> rt = cq.from(Envios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
