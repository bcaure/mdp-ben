package com.mdp.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


/**
 * The persistent class for the site database table.
 * 
 */
@Entity
@Table(name="site")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SITE_ID_GENERATOR", sequenceName="SITE ID SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SITE_ID_GENERATOR")
	private int id;

	private String libelle;

	private String url;

	//bi-directional many-to-one association to Compte
	@OneToMany(mappedBy="site", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Compte> comptes;

    public Site() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Compte> getComptes() {
		return this.comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
}