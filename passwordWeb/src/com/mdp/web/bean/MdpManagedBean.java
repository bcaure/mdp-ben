package com.mdp.web.bean;

import com.mdp.persistence.dao.CompteManager;
import com.mdp.persistence.dao.UserManager;
import com.mdp.persistence.entity.Compte;
import com.mdp.persistence.entity.Site;
import com.mdp.persistence.entity.SiteUser;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name="mdpManagedBean")
@SessionScoped
public class MdpManagedBean {

	protected SiteUser user;
	protected Compte[] selectedComptes;
	protected Integer maxRows = 15;
    protected Compte compte;

	
	public void modifyListener(RowEditEvent event) {
		
		Compte modifiedCompte = (Compte) event.getObject();        
        
		CompteManager compteManager = new CompteManager();
		
		try{
			compteManager.modify(modifiedCompte);
			FacesMessage fm = new FacesMessage("Modification OK!");
			FacesContext.getCurrentInstance().addMessage("xxx", fm);

		} catch (Exception e){
			FacesMessage fm = new FacesMessage("Modification fails:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage("xxx", fm);
		}

	}

	
	public void delete(Compte compte){
 		try{
            user.getComptes().remove(compte);
            UserManager userManager = new UserManager();
            userManager.merge(user);
		} catch (Exception e){
			FacesMessage fm = new FacesMessage("Delete fails:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage("xxx", fm);
		}
	}
	
	public void deleteListener(){
 		try{
            Collection<Compte> collection = Arrays.asList(selectedComptes);
            user.getComptes().removeAll(collection);
            UserManager userManager = new UserManager();
            userManager.merge(user);
		} catch (Exception e){
			FacesMessage fm = new FacesMessage("Delete fails:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage("xxx", fm);
		}
	}
    
    public void createNew(){
        compte = new Compte();
        compte.setSite(new Site());
        compte.getSite().setComptes(new ArrayList<Compte>());
        compte.getSite().getComptes().add(compte);
    }
    
	public void createListener(){
		
		try {
            user.getComptes().add(compte);
            compte.setUser(user);
            CompteManager compteManager = new CompteManager();
            compteManager.create(compte);

            FacesMessage fm = new FacesMessage("Creation OK!");
			FacesContext.getCurrentInstance().addMessage("xxx", fm);
		} catch (Exception e1) {
			FacesMessage fm = new FacesMessage("Creation fails:"+e1);
			FacesContext.getCurrentInstance().addMessage("xxx", fm);
		}
		
		compte = null;
	}

	public List<Compte> autocomplete(Object suggest) {

		String pref = (String) suggest;
		ArrayList<Compte> result = new ArrayList<Compte>();
		
		Iterator<Compte> iterator = user.getComptes().iterator();
		while (iterator.hasNext()) {
			
		    Compte elem = iterator.next();
		
		    if ((elem.getSite().getUrl() != null 
		    		&& elem.getSite().getUrl().toLowerCase().indexOf(pref.toLowerCase()) >= 0) 
		    		|| "".equals(pref)) {
	    		System.out.println("Suggestion found:"+elem.getSite().getUrl());
				FacesMessage fm = new FacesMessage("Suggestion found");
				FacesContext.getCurrentInstance().addMessage("xxx", fm);
		        result.add(elem);
		    }

        }

		if (result.isEmpty()){
    		System.out.println("No suggestion found!");
			FacesMessage fm = new FacesMessage("No suggestion found");
			FacesContext.getCurrentInstance().addMessage("xxx", fm);
		}
        return result;

     }

	public Integer getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}

	public Compte[] getSelectedComptes() {
		return selectedComptes;
	}

	public void setSelectedComptes(Compte[] selectedComptes) {
		this.selectedComptes = selectedComptes;
	}

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public SiteUser getUser() {
        return user;
    }

    public void setUser(SiteUser user) {
        this.user = user;
    }
    
}
