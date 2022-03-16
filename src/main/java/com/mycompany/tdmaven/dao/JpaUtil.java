/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tdmaven.dao;

import static java.rmi.server.LogStream.log;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author bbbbb
 */
public class JpaUtil {

    public static final String PERSISTENCE_UNIT_NAME = "DASI-TD1";
    private static EntityManagerFactory entityManagerFactory = null;

    public static synchronized void init() {
        log("Initialisation de la factory de contexte de persistance");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory("DASI-TD1");
    }

    public static synchronized void destroy() {
        log("Libération de la factory de contexte de persistance");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
    
    private static final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<EntityManager>() {
        @Override
        protected EntityManager initialValue() {
            return null;
        }
    };

    public static void creerContextePersistance() {
        log("Création du contexte de persistance");
        threadLocalEntityManager.set(entityManagerFactory.createEntityManager());
    }

    /**
     * Ferme l'instance courante de Entity Manager (liée à ce Thread).
     * <br><strong>À utiliser uniquement au niveau Service.</strong>
     */
    public static void fermerContextePersistance() {
        log("Fermeture du contexte de persistance");
        EntityManager em = threadLocalEntityManager.get();
        em.close();
        threadLocalEntityManager.set(null);
    }

    /**
     * Démarre une transaction sur l'instance courante de Entity Manager.<br><strong>À utiliser uniquement au niveau Service.</strong>
     * @throws java.lang.Exception
     */
    public static void ouvrirTransaction() throws Exception {
        log("Ouverture de la transaction (begin)");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().begin();
        } catch (Exception ex) {
            log("Erreur lors de l'ouverture de la transaction");
            throw ex;
        }
    }

    /**
     * Valide la transaction courante sur l'instance courante de Entity Manager.<br><strong>À utiliser uniquement au niveau Service.</strong>
     *
     * @exception RollbackException lorsque le <em>commit</em> n'a pas réussi.
     * @throws java.lang.Exception
     */
    public static void validerTransaction() throws RollbackException, Exception {
        log("Validation de la transaction (commit)");
        try {
            EntityManager em = threadLocalEntityManager.get();
            em.getTransaction().commit();
        } catch (Exception ex) {
            log("Erreur lors de la validation (commit) de la transaction");
            throw ex;
        }
    }

    /**
     * Annule la transaction courante sur l'instance courante de Entity Manager.
     * Si la transaction courante n'est pas démarrée, cette méthode n'effectue
     * aucune opération.
     * <br><strong>À utiliser uniquement au niveau Service.</strong>
     */
    public static void annulerTransaction() {
        try {
            log("Annulation de la transaction (rollback)");

            EntityManager em = threadLocalEntityManager.get();
            if (em.getTransaction().isActive()) {
                log("Annulation effective de la transaction (rollback d'une transaction active)");
                em.getTransaction().rollback();
            }

        } catch (Exception ex) {
            log("Erreur lors de l'annulation (rollback) de la transaction");
        }
    }

    /**
     * Retourne l'instance courante de Entity Manager.
     * <br><strong>À utiliser uniquement au niveau DAO.</strong>
     *
     * @return instance de Entity Manager
     */
    protected static EntityManager obtenirContextePersistance() {
        log("Obtention du contexte de persistance");
        return threadLocalEntityManager.get();
    }
}
