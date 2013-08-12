package com.mdp.persistence.dao;

import com.mdp.persistence.entity.SiteUser;

public class UserManager extends BaseManager {

	public SiteUser get(String login){
		return getEntityManager().find(SiteUser.class, login);
	}
    
    public void merge(SiteUser user) {
        getEntityManager().merge(user);
    }
}
