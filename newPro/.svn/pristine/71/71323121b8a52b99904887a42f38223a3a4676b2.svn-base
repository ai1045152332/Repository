package com.honghe.recordhibernate.entity;

import javax.persistence.*;

/**
 * Created by hthwx on 2015/2/3.
 */
@Entity
@Table(name = "eventfield")
public class Eventfield {
    private int fieldId;
    private int fieldBelong;
    private String fieldName;
    private String fieldContent;
    private String fieldRemark;

    @Id
    @Column(name = "field_id")
    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    @Basic
    @Column(name = "field_belong")
    public int getFieldBelong() {
        return fieldBelong;
    }

    public void setFieldBelong(int fieldBelong) {
        this.fieldBelong = fieldBelong;
    }

    @Basic
    @Column(name = "field_name")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Basic
    @Column(name = "field_content")
    public String getFieldContent() {
        return fieldContent;
    }

    public void setFieldContent(String fieldContent) {
        this.fieldContent = fieldContent;
    }

    @Basic
    @Column(name = "field_remark")
    public String getFieldRemark() {
        return fieldRemark;
    }

    public void setFieldRemark(String fieldRemark) {
        this.fieldRemark = fieldRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eventfield that = (Eventfield) o;

        if (fieldBelong != that.fieldBelong) return false;
        if (fieldId != that.fieldId) return false;
        if (fieldContent != null ? !fieldContent.equals(that.fieldContent) : that.fieldContent != null) return false;
        if (fieldName != null ? !fieldName.equals(that.fieldName) : that.fieldName != null) return false;
        if (fieldRemark != null ? !fieldRemark.equals(that.fieldRemark) : that.fieldRemark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fieldId;
        result = 31 * result + fieldBelong;
        result = 31 * result + (fieldName != null ? fieldName.hashCode() : 0);
        result = 31 * result + (fieldContent != null ? fieldContent.hashCode() : 0);
        result = 31 * result + (fieldRemark != null ? fieldRemark.hashCode() : 0);
        return result;
    }
}
