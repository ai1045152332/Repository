package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wuzhenzhen on 2016/06/09.
 */
@Entity
@Table(name="curriculum")
public class Curriculum implements java.io.Serializable {
    private int curId;
    private Date curDate;
    private int curWeek;
    private int curSection;
    private int curHost;
    private String curClass;
    private String curTeacher;
    private String curSubject;
    private String curUnit;

    // "2" 表示周课表
    public static final int CUR_WEEK_TYPE = 2;
    // "1" 表示总课表
    public static final int CUT_DATE_TYPE = 1;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cur_id")
    public int getCurId() {
        return curId;
    }
    public void setCurId(int curId) {
        this.curId = curId;
    }

    @Basic
    @Column(name = "cur_unit")
    public String getCurUnit() {
        return curUnit;
    }

    public void setCurUnit(String curUnit) {
        this.curUnit = curUnit;
    }

    @Basic
    @Column(name = "cur_date")
    public Date getCurDate() {
        return curDate;
    }
    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    @Basic
    @Column(name = "cur_week")
    public int getCurWeek() {
        return curWeek;
    }
    public void setCurWeek(int curWeek) {
        this.curWeek = curWeek;
    }

    @Basic
    @Column(name = "cur_section")
    public int getCurSection() {
        return curSection;
    }
    public void setCurSection(int curSection) {
        this.curSection = curSection;
    }

    @Basic
    @Column(name = "cur_host")
    public int getCurHost() {
        return curHost;
    }
    public void setCurHost(int curHost) {
        this.curHost = curHost;
    }

    @Basic
    @Column(name = "cur_class")
    public String getCurClass() {
        return curClass;
    }
    public void setCurClass(String curClass) {
        this.curClass = curClass;
    }

    @Basic
    @Column(name = "cur_teacher")
    public String getCurTeacher() {
        return curTeacher;
    }
    public void setCurTeacher(String curTeacher) {
        this.curTeacher = curTeacher;
    }

    @Basic
    @Column(name = "cur_subject")
    public String getCurSubject() {
        return curSubject;
    }
    public void setCurSubject(String curSubject) {
        this.curSubject = curSubject;
    }
}
