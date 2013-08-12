package com.mdp.web.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mdp.persistence.dao.UserManager;
import com.mdp.persistence.entity.SiteUser;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="loginManagedBean")
@SessionScoped
public class LoginManagedBean {
	protected String userName;
	protected String password;
    
    @ManagedProperty(value="#{mdpManagedBean}")
    protected MdpManagedBean mdpManagedBean;

	public String authenticate(){
		
		Boolean isAuthenticated = false;
		
		SiteUser user = mdpManagedBean.getUser();
		if (user == null){
			if (userName != null && !userName.equals("")){
				user = (new UserManager()).get(userName);
				if (user!=null && user.getPassword().equals(password)){
					isAuthenticated = true;
					mdpManagedBean.setUser(user);
				} else {
					user = null;
					FacesMessage fm = new FacesMessage("Authentication fails");
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
