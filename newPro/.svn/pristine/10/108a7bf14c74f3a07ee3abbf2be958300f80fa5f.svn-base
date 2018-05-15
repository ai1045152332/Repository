package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2014/11/10.
 */
@Entity
@Table(name = "d_mode", schema = "", catalog = "nvrmanager")
public class DMode {
    private int mId;
    private String mName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "m_id")
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    @Basic
    @Column(name = "m_name")
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }


    @Override
    public int hashCode() {
        return super.hashCode()*this.getmName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DMode){
             DMode dMode = (DMode)obj;
             return this.getmName().equals(dMode.getmName());
        }
        return false;
    }

}
