package com.honghe.recordhibernate.entity;

// Generated 2015-01-13 13:05:49 by wzz

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "setting_logo")
public class Logo implements java.io.Serializable {

    private Integer logoId;

    private String logoName;

    private Integer logoUsing;

    private String logoSite;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "logo_id", unique = true, nullable = false)
    public Integer getLogoId() {
        return logoId;
    }

    public void setLogoId(Integer logoId) {
        this.logoId = logoId;
    }


    @Column(name = "logo_name")
    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    @Column(name = "logo_using")
    public Integer getLogoUsing() {
        return logoUsing;
    }

    public void setLogoUsing(Integer logoUsing) {
        this.logoUsing = logoUsing;
    }

    @Column(name = "logo_site")
    public String getLogoSite() {
        return logoSite;
    }

    public void setLogoSite(String logoSite) {
        this.logoSite = logoSite;
    }


}
