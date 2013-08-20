package com.mdp.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mdp.persistence.entity.SiteUser;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ManagedBean(name="loginManagedBean")
@SessionScoped
public class LoginManagedBean {
	protected String userName;
	protected String password;
    
    @ManagedProperty(value="#{mdpManagedBean}")
    protected MdpManagedBean mdpManagedBean;
    
    @PersistenceUnit(unitName="MdpPersistence")
    private EntityManagerFactory em;

    public String authenticate(){

            Boolean isAuthenticated = false;

            SiteUser user = mdpManagedBean.getUser();
            if (user == null){
                if (userName != null && !userName.equals("")){
                    try {
                        user = em.createEntityManager().find(SiteUser.class, userName);
                        if (user!=null && user.getPassword().equals(password)){
                                isAuthenticated = true;
                                mdpManagedBean.setUser(user);
                        } 
                    } catch (Exception e) {
                        StringWriter writer = new StringWriter();
                        PrintWriter out = new PrintWriter(writer);
                        e.printStackTrace(out);
                        FacesMessage fm = new FacesMessage("Authentication fails", writer.toString());
                        FacesContext.getCurrentInstance().addMessage("xxx", fm);
                    }
                }
            } else {
                isAuthenticated = true;
            }

            return isAuthenticated?"primeFacesGrid":"index";
    }

    public String getUserName() {
            return userName;
    }

    public void setUserName(String userName) {
            this.userName = userName;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public MdpManagedBean getMdpManagedBean() {
        return mdpManagedBean;
    }

    public void setMdpManagedBean(MdpManagedBean mdpManagedBean) {
        this.mdpManagedBean = mdpManagedBean;
    }

	
	
}
