package com.mdp.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name = "user")
public class SiteUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    @Temporal( TemporalType.DATE)
    @Column(name = "date_quota")
    private Date dateQuota;
    private String password;
    @Column(name = "used_quota")
    private int usedQuota;
    @OneToMany(mappedBy = "user")
    private List<Compte> comptes;

    public SiteUser() {
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDateQuota() {
        return this.dateQuota;
    }

    public void setDateQuota(Date dateQuota) {
        this.dateQuota = dateQuota;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsedQuota() {
        return this.usedQuota;
    }

    public void setUsedQuota(int usedQuota) {
        this.usedQuota = usedQuota;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}