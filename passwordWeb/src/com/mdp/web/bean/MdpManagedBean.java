package com.mdp.web.bean;

import com.mdp.persistence.entity.Compte;
import com.mdp.persistence.entity.Site;
import com.mdp.persistence.entity.SiteUser;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@ManagedBean(name = "mdpManagedBean")
@SessionScoped
public class MdpManagedBean {

    protected SiteUser user;
    protected Compte compte;
    @PersistenceContext(unitName = "MdpPersistence")
    private EntityManager em;
    @Resource
    UserTransaction utx;    
    
    protected List<Compte> filteredValues;
    protected Compte[] selectedComptes;
    
    public void modifyListener(Compte modifiedCompte) {

        try {
            utx.begin();
            em.merge(modifiedCompte);
            utx.commit();
            FacesMessage fm = new FacesMessage("Modification OK!", "");
            FacesContext.getCurrentInstance().addMessage("xxx", fm);
        } catch (Exception e) {
            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            e.printStackTrace(out);
            FacesMessage fm = new FacesMessage("Modification fails!", writer.toString());
            FacesContext.getCurrentInstance().addMessage("xxx", fm);
            try {
                utx.rollback();
            } catch (Exception ex) {
                Logger.getLogger(MdpManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

    }

    public void delete(Compte compte) {
        try {
            utx.begin();
            user.getComptes().remove(compte);
            em.merge(user);
            utx.commit();
        } catch (Exception e) {
            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            e.printStackTrace(out);
            FacesMessage fm = new FacesMessage("Delete fails:", writer.toString());
            FacesContext.getCurrentInstance().addMessage("xxx", fm);
            try {
                utx.rollback();
            } catch (Exception ex) {
                Logger.getLogger(MdpManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    public void deleteListener() {
        try {
            utx.begin();
            Collection<Compte> collection = Arrays.asList(selectedComptes);
            user.getComptes().removeAll(collection);
            em.merge(user);
            utx.commit();
        } catch (Exception e) {
            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            e.printStackTrace(out);
            FacesMessage fm = new FacesMessage("Delete fails:", writer.toString());
            FacesContext.getCurrentInstance().addMessage("xxx", fm);
            try {
                utx.rollback();
            } catch (Exception ex) {
                Logger.getLogger(MdpManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    public void createNew() {
        compte = new Compte();
        compte.setSite(new Site());
        compte.getSite().setComptes(new ArrayList<Compte>());
        compte.getSite().getComptes().add(compte);
    }

    public void createListener() {

        try {
            utx.begin();
            user.getComptes().add(compte);
            compte.setUser(user);
            
            em.persist(compte);
            utx.commit();

            FacesMessage fm = new FacesMessage("Creation OK!");
            FacesContext.getCurrentInstance().addMessage("xxx", fm);
        } catch (Exception e) {
            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            e.printStackTrace(out);
            FacesMessage fm = new FacesMessage("Creation fails:", writer.toString());
            FacesContext.getCurrentInstance().addMessage("xxx", fm);
            try {
                utx.rollback();
            } catch (Exception ex) {
                Logger.getLogger(MdpManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        compte = null;
    }

    public List<Compte> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<Compte> filteredValues) {
        this.filteredValues = filteredValues;
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
