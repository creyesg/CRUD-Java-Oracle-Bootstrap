/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entidades.EmpresasEnvio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Envios;
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
public class EmpresasEnvioJpaController implements Serializable {

    public EmpresasEnvioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmpresasEnvio empresasEnvio) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (empresasEnvio.getEnviosCollection() == null) {
            empresasEnvio.setEnviosCollection(new ArrayList<Envios>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Envios> attachedEnviosCollection = new ArrayList<Envios>();
            for (Envios enviosCollectionEnviosToAttach : empresasEnvio.getEnviosCollection()) {
                enviosCollectionEnviosToAttach = em.getReference(enviosCollectionEnviosToAttach.getClass(), enviosCollectionEnviosToAttach.getEnvioId());
                attachedEnviosCollection.add(enviosCollectionEnviosToAttach);
            }
            empresasEnvio.setEnviosCollection(attachedEnviosCollection);
            em.persist(empresasEnvio);
            for (Envios enviosCollectionEnvios : empresasEnvio.getEnviosCollection()) {
                EmpresasEnvio oldIdEmpresaOfEnviosCollectionEnvios = enviosCollectionEnvios.getIdEmpresa();
                enviosCollectionEnvios.setIdEmpresa(empresasEnvio);
                enviosCollectionEnvios = em.merge(enviosCollectionEnvios);
                if (oldIdEmpresaOfEnviosCollectionEnvios != null) {
                    oldIdEmpresaOfEnviosCollectionEnvios.getEnviosCollection().remove(enviosCollectionEnvios);
                    oldIdEmpresaOfEnviosCollectionEnvios = em.merge(oldIdEmpresaOfEnviosCollectionEnvios);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmpresasEnvio(empresasEnvio.getIdEmpresa()) != null) {
                throw new PreexistingEntityException("EmpresasEnvio " + empresasEnvio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmpresasEnvio empresasEnvio) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmpresasEnvio persistentEmpresasEnvio = em.find(EmpresasEnvio.class, empresasEnvio.getIdEmpresa());
            Collection<Envios> enviosCollectionOld = persistentEmpresasEnvio.getEnviosCollection();
            Collection<Envios> enviosCollectionNew = empresasEnvio.getEnviosCollection();
            Collection<Envios> attachedEnviosCollectionNew = new ArrayList<Envios>();
            for (Envios enviosCollectionNewEnviosToAttach : enviosCollectionNew) {
                enviosCollectionNewEnviosToAttach = em.getReference(enviosCollectionNewEnviosToAttach.getClass(), enviosCollectionNewEnviosToAttach.getEnvioId());
                attachedEnviosCollectionNew.add(enviosCollectionNewEnviosToAttach);
            }
            enviosCollectionNew = attachedEnviosCollectionNew;
            empresasEnvio.setEnviosCollection(enviosCollectionNew);
            empresasEnvio = em.merge(empresasEnvio);
            for (Envios enviosCollectionOldEnvios : enviosCollectionOld) {
                if (!enviosCollectionNew.contains(enviosCollectionOldEnvios)) {
                    enviosCollectionOldEnvios.setIdEmpresa(null);
                    enviosCollectionOldEnvios = em.merge(enviosCollectionOldEnvios);
                }
            }
            for (Envios enviosCollectionNewEnvios : enviosCollectionNew) {
                if (!enviosCollectionOld.contains(enviosCollectionNewEnvios)) {
                    EmpresasEnvio oldIdEmpresaOfEnviosCollectionNewEnvios = enviosCollectionNewEnvios.getIdEmpresa();
                    enviosCollectionNewEnvios.setIdEmpresa(empresasEnvio);
                    enviosCollectionNewEnvios = em.merge(enviosCollectionNewEnvios);
                    if (oldIdEmpresaOfEnviosCollectionNewEnvios != null && !oldIdEmpresaOfEnviosCollectionNewEnvios.equals(empresasEnvio)) {
                        oldIdEmpresaOfEnviosCollectionNewEnvios.getEnviosCollection().remove(enviosCollectionNewEnvios);
                        oldIdEmpresaOfEnviosCollectionNewEnvios = em.merge(oldIdEmpresaOfEnviosCollectionNewEnvios);
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
                Long id = empresasEnvio.getIdEmpresa();
                if (findEmpresasEnvio(id) == null) {
                    throw new NonexistentEntityException("The empresasEnvio with id " + id + " no longer exists.");
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
            EmpresasEnvio empresasEnvio;
            try {
                empresasEnvio = em.getReference(EmpresasEnvio.class, id);
                empresasEnvio.getIdEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresasEnvio with id " + id + " no longer exists.", enfe);
            }
            Collection<Envios> enviosCollection = empresasEnvio.getEnviosCollection();
            for (Envios enviosCollectionEnvios : enviosCollection) {
                enviosCollectionEnvios.setIdEmpresa(null);
                enviosCollectionEnvios = em.merge(enviosCollectionEnvios);
            }
            em.remove(empresasEnvio);
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

    public List<EmpresasEnvio> findEmpresasEnvioEntities() {
        return findEmpresasEnvioEntities(true, -1, -1);
    }

    public List<EmpresasEnvio> findEmpresasEnvioEntities(int maxResults, int firstResult) {
        return findEmpresasEnvioEntities(false, maxResults, firstResult);
    }

    private List<EmpresasEnvio> findEmpresasEnvioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmpresasEnvio.class));
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

    public EmpresasEnvio findEmpresasEnvio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmpresasEnvio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresasEnvioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmpresasEnvio> rt = cq.from(EmpresasEnvio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
