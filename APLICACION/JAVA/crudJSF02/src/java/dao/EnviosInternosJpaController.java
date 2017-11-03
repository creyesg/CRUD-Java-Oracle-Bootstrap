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
import entidades.Envios;
import entidades.EnviosInternos;
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
public class EnviosInternosJpaController implements Serializable {

    public EnviosInternosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnviosInternos enviosInternos) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (enviosInternos.getEnviosCollection() == null) {
            enviosInternos.setEnviosCollection(new ArrayList<Envios>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Envios> attachedEnviosCollection = new ArrayList<Envios>();
            for (Envios enviosCollectionEnviosToAttach : enviosInternos.getEnviosCollection()) {
                enviosCollectionEnviosToAttach = em.getReference(enviosCollectionEnviosToAttach.getClass(), enviosCollectionEnviosToAttach.getEnvioId());
                attachedEnviosCollection.add(enviosCollectionEnviosToAttach);
            }
            enviosInternos.setEnviosCollection(attachedEnviosCollection);
            em.persist(enviosInternos);
            for (Envios enviosCollectionEnvios : enviosInternos.getEnviosCollection()) {
                EnviosInternos oldCodEmpInternoOfEnviosCollectionEnvios = enviosCollectionEnvios.getCodEmpInterno();
                enviosCollectionEnvios.setCodEmpInterno(enviosInternos);
                enviosCollectionEnvios = em.merge(enviosCollectionEnvios);
                if (oldCodEmpInternoOfEnviosCollectionEnvios != null) {
                    oldCodEmpInternoOfEnviosCollectionEnvios.getEnviosCollection().remove(enviosCollectionEnvios);
                    oldCodEmpInternoOfEnviosCollectionEnvios = em.merge(oldCodEmpInternoOfEnviosCollectionEnvios);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEnviosInternos(enviosInternos.getCodEmpleado()) != null) {
                throw new PreexistingEntityException("EnviosInternos " + enviosInternos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnviosInternos enviosInternos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EnviosInternos persistentEnviosInternos = em.find(EnviosInternos.class, enviosInternos.getCodEmpleado());
            Collection<Envios> enviosCollectionOld = persistentEnviosInternos.getEnviosCollection();
            Collection<Envios> enviosCollectionNew = enviosInternos.getEnviosCollection();
            Collection<Envios> attachedEnviosCollectionNew = new ArrayList<Envios>();
            for (Envios enviosCollectionNewEnviosToAttach : enviosCollectionNew) {
                enviosCollectionNewEnviosToAttach = em.getReference(enviosCollectionNewEnviosToAttach.getClass(), enviosCollectionNewEnviosToAttach.getEnvioId());
                attachedEnviosCollectionNew.add(enviosCollectionNewEnviosToAttach);
            }
            enviosCollectionNew = attachedEnviosCollectionNew;
            enviosInternos.setEnviosCollection(enviosCollectionNew);
            enviosInternos = em.merge(enviosInternos);
            for (Envios enviosCollectionOldEnvios : enviosCollectionOld) {
                if (!enviosCollectionNew.contains(enviosCollectionOldEnvios)) {
                    enviosCollectionOldEnvios.setCodEmpInterno(null);
                    enviosCollectionOldEnvios = em.merge(enviosCollectionOldEnvios);
                }
            }
            for (Envios enviosCollectionNewEnvios : enviosCollectionNew) {
                if (!enviosCollectionOld.contains(enviosCollectionNewEnvios)) {
                    EnviosInternos oldCodEmpInternoOfEnviosCollectionNewEnvios = enviosCollectionNewEnvios.getCodEmpInterno();
                    enviosCollectionNewEnvios.setCodEmpInterno(enviosInternos);
                    enviosCollectionNewEnvios = em.merge(enviosCollectionNewEnvios);
                    if (oldCodEmpInternoOfEnviosCollectionNewEnvios != null && !oldCodEmpInternoOfEnviosCollectionNewEnvios.equals(enviosInternos)) {
                        oldCodEmpInternoOfEnviosCollectionNewEnvios.getEnviosCollection().remove(enviosCollectionNewEnvios);
                        oldCodEmpInternoOfEnviosCollectionNewEnvios = em.merge(oldCodEmpInternoOfEnviosCollectionNewEnvios);
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
                Long id = enviosInternos.getCodEmpleado();
                if (findEnviosInternos(id) == null) {
                    throw new NonexistentEntityException("The enviosInternos with id " + id + " no longer exists.");
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
            EnviosInternos enviosInternos;
            try {
                enviosInternos = em.getReference(EnviosInternos.class, id);
                enviosInternos.getCodEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enviosInternos with id " + id + " no longer exists.", enfe);
            }
            Collection<Envios> enviosCollection = enviosInternos.getEnviosCollection();
            for (Envios enviosCollectionEnvios : enviosCollection) {
                enviosCollectionEnvios.setCodEmpInterno(null);
                enviosCollectionEnvios = em.merge(enviosCollectionEnvios);
            }
            em.remove(enviosInternos);
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

    public List<EnviosInternos> findEnviosInternosEntities() {
        return findEnviosInternosEntities(true, -1, -1);
    }

    public List<EnviosInternos> findEnviosInternosEntities(int maxResults, int firstResult) {
        return findEnviosInternosEntities(false, maxResults, firstResult);
    }

    private List<EnviosInternos> findEnviosInternosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnviosInternos.class));
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

    public EnviosInternos findEnviosInternos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnviosInternos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnviosInternosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnviosInternos> rt = cq.from(EnviosInternos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
