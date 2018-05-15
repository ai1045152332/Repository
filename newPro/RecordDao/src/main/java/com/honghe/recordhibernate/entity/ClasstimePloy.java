package com.honghe.recordhibernate.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wzz on 2016-07-16.
 */
@Entity
@Table(name = "classtime_ploy")
public class ClasstimePloy {
    private Integer ployId;
    private String ployName;
//    private Set<Hostgroup> hostgroups = new HashSet<Hostgroup>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ploy_id")
    public Integer getPloyId() {
        return ployId;
    }

    public void setPloyId(Integer ployId) {
        this.ployId = ployId;
    }

    @Basic
    @Column(name = "ploy_name")
    public String getPloyName() {
        return ployName;
    }

    public void setPloyName(String ployName) {
        this.ployName = ployName;
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "classtimeploy2group", joinColumns =
//            { @JoinColumn(name = "ploy_id") }, inverseJoinColumns =
//            { @JoinColumn(name = "group_id") })
//    public Set<Hostgroup> getHostgroups() {
//        return hostgroups;
//    }
//
//    public void setHostgroups(Set<Hostgroup> hostgroups) {
//        this.hostgroups = hostgroups;
//    }
}
