package com.mdp.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.mdp.persistence.entity.Compte;
import com.mdp.persistence.entity.Site;

public class CompteManager extends BaseManager {

	
	public List<Compte> selectAll() {
		@SuppressWarnings("unchecked")
		List<Compte> comptes = getEntityManager().createQuery("select c from Compte c").getResultList();
		return comptes;
	}
	
	public void modify(Compte compte){
		EntityManager em = getEntityManager();
		try{
			em.getTransaction().begin();
			Compte compteToUpdate = em.find(Compte.class, compte.getId());
			compteToUpdate.setLogin(compte.getLogin());
			compteToUpdate.setMdp(compte.getMdp());
			Site siteToUpdate = em.find(Site.class, compte.getSite().getId());
			siteToUpdate.setLibelle(compte.getSite().getLibelle());
			siteToUpdate.setUrl(compte.getSite().getUrl());
            em.merge(compteToUpdate);
			em.getTransaction().commit();
		} finally {
		    //em.close();
		}		
	}
	
	public void delete(Compte compte){
		EntityManager em = getEntityManager();
		try{
			em.getTransaction().begin();
			Compte compteToDelete = em.getReference(compte.getClass(), compte.getId());
			em.remove(compteToDelete);
			em.getTransaction().commit();
		} finally {
		    //em.close();
		}		
	}
	
	public void create(Compte compte){
		EntityManager em = getEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(compte);
			em.getTransaction().commit();
		} finally {
		    //em.close();
		}		
	}

}
