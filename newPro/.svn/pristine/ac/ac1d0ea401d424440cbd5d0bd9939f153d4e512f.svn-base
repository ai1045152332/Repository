package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by honghe on 2016/9/5.
 */
@Entity
@Table(name="temporaryplan")
public class Temporaryplan {
    private int temporaryplanId;
    private String name;
    private String timeStart;
    private String timeEnd;
    private Set<Host> hosts;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "temporaryplan_id")
    public int getTemporaryplanId() {
        return temporaryplanId;
    }

    public void setTemporaryplanId(int temporaryplanId) {
        this.temporaryplanId = temporaryplanId;
    }

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name="time_start")
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @Basic
    @Column(name="time_end")
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

//    @ManyToMany(targetEntity=Host.class,mappedBy="temporaryplanSet",fetch = FetchType.LAZY)
    @Transient
    public Set<Host> getHosts() {
        return hosts;
    }

    public void setHosts(Set<Host> hosts) {
        this.hosts = hosts;
    }
}
