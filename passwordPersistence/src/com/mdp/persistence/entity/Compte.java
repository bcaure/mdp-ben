package com.mdp.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the compte database table.
 * 
 */
@Entity
@Table(name="compte")
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMPTE_ID_GENERATOR", sequenceName="COMPTE ID SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPTE_ID_GENERATOR")
	private int id;

	private String login;

	private String mdp;

	//bi-directional many-to-one association to Site
    @ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id_site")
	private Site site;
    
    @ManyToOne
	@JoinColumn(name="user")
	private SiteUser user;

    public Compte() {
    }
    
	public Compte(int id) {
		super();
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( !(obj instanceof Compte) ) return false;
		Compte compte = (Compte) obj;
		if (compte.id==143 || compte.id == 144)
			System.out.println("id:"+id);
		return compte.id == id;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

    public SiteUser getUser() {
        return user;
    }

    public void setUser(SiteUser user) {
        this.user = user;
    }
	
}