package com.mdp.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseManager {

	private static final String JPA_UNIT_NAME = "MdpPersistence";
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(JPA_UNIT_NAME);
			entityManager = entityManagerFactory.createEntityManager(); 
		}
		return entityManager;
	}

}
