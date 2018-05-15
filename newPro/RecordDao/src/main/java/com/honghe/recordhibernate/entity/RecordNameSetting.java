package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by honghe on 2016/9/13.
 */
@Entity
@Table(name="record_name_setting")
public class RecordNameSetting {

    private int recordNameID;
    private boolean subjectName;
    private boolean teacherName;
    private boolean classRoomName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "record_name_id")
    public int getRecordNameID() {
        return recordNameID;
    }

    public void setRecordNameID(int recordNameID) {
        this.recordNameID = recordNameID;
    }

    @Basic
    @Column(name="subject_name")
    public boolean isSubjectName() {
        return subjectName;
    }

    public void setSubjectName(boolean subjectName) {
        this.subjectName = subjectName;
    }

    @Basic
    @Column(name="teacher_name")
    public boolean isTeacherName() {
        return teacherName;
    }

    public void setTeacherName(boolean teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name="classroom_name")
    public boolean isClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(boolean classRoomName) {
        this.classRoomName = classRoomName;
    }
}
