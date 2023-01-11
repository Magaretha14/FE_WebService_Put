/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ws.a.database.exceptions.NonexistentEntityException;
import ws.a.database.exceptions.PreexistingEntityException;

/**
 *
 * @author ASUS
 */
public class A2020wsJpaController implements Serializable {

    public A2020wsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ws.a_database_jar_0.0.1-SNAPSHOTPU");
    
    public A2020wsJpaController(){
        
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(A2020ws a2020ws) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(a2020ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findA2020ws(a2020ws.getId()) != null) {
                throw new PreexistingEntityException("A2020ws " + a2020ws + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(A2020ws a2020ws) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            a2020ws = em.merge(a2020ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = a2020ws.getId();
                if (findA2020ws(id) == null) {
                    throw new NonexistentEntityException("The a2020ws with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            A2020ws a2020ws;
            try {
                a2020ws = em.getReference(A2020ws.class, id);
                a2020ws.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The a2020ws with id " + id + " no longer exists.", enfe);
            }
            em.remove(a2020ws);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<A2020ws> findA2020wsEntities() {
        return findA2020wsEntities(true, -1, -1);
    }

    public List<A2020ws> findA2020wsEntities(int maxResults, int firstResult) {
        return findA2020wsEntities(false, maxResults, firstResult);
    }

    private List<A2020ws> findA2020wsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(A2020ws.class));
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

    public A2020ws findA2020ws(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(A2020ws.class, id);
        } finally {
            em.close();
        }
    }

    public int getA2020wsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<A2020ws> rt = cq.from(A2020ws.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
