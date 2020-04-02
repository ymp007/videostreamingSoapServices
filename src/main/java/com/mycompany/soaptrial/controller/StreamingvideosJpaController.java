/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soaptrial.controller;

import com.mycompany.soaptrial.controller.exceptions.NonexistentEntityException;
import com.mycompany.soaptrial.controller.exceptions.PreexistingEntityException;
import com.mycompany.soaptrial.model.Streamingvideos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author YMP
 */
public class StreamingvideosJpaController implements Serializable {

    public StreamingvideosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Streamingvideos streamingvideos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(streamingvideos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStreamingvideos(streamingvideos.getId()) != null) {
                throw new PreexistingEntityException("Streamingvideos " + streamingvideos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Streamingvideos streamingvideos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            streamingvideos = em.merge(streamingvideos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = streamingvideos.getId();
                if (findStreamingvideos(id) == null) {
                    throw new NonexistentEntityException("The streamingvideos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Streamingvideos streamingvideos;
            try {
                streamingvideos = em.getReference(Streamingvideos.class, id);
                streamingvideos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The streamingvideos with id " + id + " no longer exists.", enfe);
            }
            em.remove(streamingvideos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Streamingvideos> findStreamingvideosEntities() {
        return findStreamingvideosEntities(true, -1, -1);
    }

    public List<Streamingvideos> findStreamingvideosEntities(int maxResults, int firstResult) {
        return findStreamingvideosEntities(false, maxResults, firstResult);
    }

    private List<Streamingvideos> findStreamingvideosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Streamingvideos.class));
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

    public Streamingvideos findStreamingvideos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Streamingvideos.class, id);
        } finally {
            em.close();
        }
    }

    public int getStreamingvideosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Streamingvideos> rt = cq.from(Streamingvideos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
