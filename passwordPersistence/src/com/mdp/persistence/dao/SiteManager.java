package com.mdp.persistence.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mdp.persistence.entity.Site;

public class SiteManager extends BaseManager {

	public final String QUERY_SELECT = 
		"select s from Site s "+
		"where (s.libelle = :libelle or :libelle is null)"+
		"and (s.url = :url or :url is null)";
	
	public Site findByAttributes (Site site) {
		
		EntityManager em = getEntityManager();
		Query selectSite = em.createQuery(QUERY_SELECT);
		selectSite.setParameter("libelle", site.getLibelle());
		selectSite.setParameter("url", site.getUrl());
		@SuppressWarnings("unchecked")
		List<Site> resultList = selectSite.getResultList();
		if (!resultList.isEmpty()) {
			site = resultList.get(0);
		} 
		return site;
	}
	
	public Site insertIfNotExist(Site site) {
		
		EntityManager em = getEntityManager();

		Site existingSite = findByAttributes(site);
		if (existingSite == null) {
			em.persist(site);
			return site;
		} else {
			return existingSite;
		}
	}
}
