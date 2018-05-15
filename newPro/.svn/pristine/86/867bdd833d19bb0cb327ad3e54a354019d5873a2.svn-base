package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by hthwx on 2015/3/24.
 */
@Entity
@Table(name = "ftpsetting")
public class Ftpsetting {
    private int id;
    private String ftpAddr;
    private Integer ftpPort;
    private String ftpUser;
    private String ftpPass;
    private String ftpRemark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ftp_addr")
    public String getFtpAddr() {
        return ftpAddr;
    }

    public void setFtpAddr(String ftpAddr) {
        this.ftpAddr = ftpAddr;
    }

    @Basic
    @Column(name = "ftp_port")
    public Integer getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }

    @Basic
    @Column(name = "ftp_user")
    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    @Basic
    @Column(name = "ftp_pass")
    public String getFtpPass() {
        return ftpPass;
    }

    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    @Basic
    @Column(name = "ftp_remark")
    public String getFtpRemark() {
        return ftpRemark;
    }

    public void setFtpRemark(String ftpRemark) {
        this.ftpRemark = ftpRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ftpsetting that = (Ftpsetting) o;

        if (id != that.id) return false;
        if (ftpAddr != null ? !ftpAddr.equals(that.ftpAddr) : that.ftpAddr != null) return false;
        if (ftpPass != null ? !ftpPass.equals(that.ftpPass) : that.ftpPass != null) return false;
        if (ftpPort != null ? !ftpPort.equals(that.ftpPort) : that.ftpPort != null) return false;
        if (ftpRemark != null ? !ftpRemark.equals(that.ftpRemark) : that.ftpRemark != null) return false;
        if (ftpUser != null ? !ftpUser.equals(that.ftpUser) : that.ftpUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ftpAddr != null ? ftpAddr.hashCode() : 0);
        result = 31 * result + (ftpPort != null ? ftpPort.hashCode() : 0);
        result = 31 * result + (ftpUser != null ? ftpUser.hashCode() : 0);
        result = 31 * result + (ftpPass != null ? ftpPass.hashCode() : 0);
        result = 31 * result + (ftpRemark != null ? ftpRemark.hashCode() : 0);
        return result;
    }
}
