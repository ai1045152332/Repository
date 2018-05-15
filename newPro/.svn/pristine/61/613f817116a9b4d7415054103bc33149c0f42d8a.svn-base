package com.honghe.recordhibernate.entity;

import javax.persistence.*;

/**
 * Created by hthwx on 2015/2/12.
 */
@Entity
@Table(name = "command_code")
public class CommandCode {
    private int codeId;
    private String codeName;
    private String codeType;
    private String codeResult;
    private String codeRemark;
    private String codeInstruction;
    private String codeFlag;
    private String codeGroup;
    private Dspecification dspecification;
    private String codeCode;

    @Id
    @Column(name = "code_id")
    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Basic
    @Column(name = "code_name")
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Basic
    @Column(name = "code_type")
    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    @Basic
    @Column(name = "code_result")
    public String getCodeResult() {
        return codeResult;
    }

    public void setCodeResult(String codeResult) {
        this.codeResult = codeResult;
    }

    @Basic
    @Column(name = "code_remark")
    public String getCodeRemark() {
        return codeRemark;
    }

    public void setCodeRemark(String codeRemark) {
        this.codeRemark = codeRemark;
    }

    @Basic
    @Column(name = "code_instruction")
    public String getCodeInstruction() {
        return codeInstruction;
    }

    public void setCodeInstruction(String codeInstruction) {
        this.codeInstruction = codeInstruction;
    }

    @Basic
    @Column(name = "code_code")
    public String getCodeCode() {
        return codeCode;
    }

    public void setCodeCode(String codeCode) {
        this.codeCode = codeCode;
    }

    @Basic
    @Column(name = "code_flag")
    public String getCodeFlag() {
        return codeFlag;
    }

    public void setCodeFlag(String codeFlag) {
        this.codeFlag = codeFlag;
    }
    @Basic
    @Column(name = "code_group")
    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    @ManyToOne(targetEntity = Dspecification.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dspec_id")
    public Dspecification getDspecification() {
        return dspecification;
    }

    public void setDspecification(Dspecification dspecification) {
        this.dspecification = dspecification;
    }
}
