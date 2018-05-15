package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2014/9/26.
 * Altered by wzz on 2016-07-16 增加时间策略归属
 */
@Entity
@Table(name="classtime")
public class Classtime implements java.io.Serializable {
    private int classtimeId;
    private Integer weekId;
    private Integer sectionId;
    private String classtimeStart;
    private String classtimeEnd;
//    private Set<Timeplan> timeplan;
    private ClasstimePloy classtimePloy;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "classtime_id")
    public int getClasstimeId() {
        return classtimeId;
    }

    public void setClasstimeId(int classtimeId) {
        this.classtimeId = classtimeId;
    }

    @Basic
    @Column(name = "week_id")
    public Integer getWeekId() {
        return weekId;
    }
    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    @Basic
    @Column(name = "classtime_section")
    public Integer getSectionId() {
        return sectionId;
    }
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Basic
    @Column(name = "classtime_start")
    public String getClasstimeStart() {
        return classtimeStart;
    }

    public void setClasstimeStart(String classtimeStart) {
        this.classtimeStart = classtimeStart;
    }

    @Basic
    @Column(name = "classtime_end")
    public String getClasstimeEnd() {
        return classtimeEnd;
    }

    public void setClasstimeEnd(String classtimeEnd) {
        this.classtimeEnd = classtimeEnd;
    }

    @ManyToOne(fetch = FetchType.LAZY,targetEntity=ClasstimePloy.class)
    @JoinColumn(name = "classtime_ploy")
    public ClasstimePloy getClasstimePloy() {
        return classtimePloy;
    }

    public void setClasstimePloy(ClasstimePloy classtimePloy) {
        this.classtimePloy = classtimePloy;
    }

//    @OneToMany(fetch = FetchType.LAZY,mappedBy="classtime",targetEntity=Timeplan.class)
//    public Set<Timeplan> getTimeplan() {
//        return timeplan;
//    }
//
//    public void setTimeplan(Set<Timeplan> timeplan) {
//        this.timeplan = timeplan;
//    }
}
