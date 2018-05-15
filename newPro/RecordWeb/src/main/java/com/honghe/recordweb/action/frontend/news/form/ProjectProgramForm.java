package com.honghe.recordweb.action.frontend.news.form;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ProjectProgramForm {
	
	private String prid;
	
	private String dstPrid;

	private String ratio;
	
	private String curIdx;
	
	private String w;
	
	private String h;

	private String state;
	
	private String editstate;
	
	private String name; // 节目名称

	private String note;  // 备注
	
	private String theme; // 主题
	
	private String navproject;
	
	private String keyWords;
	
	private String sorter;
	
	private String checkedPrj;
	
	private String auditFlag;
	
	private String delScopeFlag;
	
	private String quipub;
	
	private String groupId;
	
	private String deviceId;

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getQuipub() {
		return quipub;
	}
	public void setQuipub(String quipub) {
		this.quipub = quipub;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDelScopeFlag() {
		return delScopeFlag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public void setDelScopeFlag(String delScopeFlag) {
		this.delScopeFlag = delScopeFlag;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getCheckedPrj() {
		return checkedPrj;
	}

	public void setCheckedPrj(String checkedPrj) {
		this.checkedPrj = checkedPrj;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getNavproject() {
		return navproject;
	}

	public void setNavproject(String navproject) {
		this.navproject = navproject;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getCurIdx() {
		return curIdx;
	}

	public void setCurIdx(String curIdx) {
		this.curIdx = curIdx;
	}

	public String getPrid() {
//		try {
//			prid = new String(prid.getBytes("ISO-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getDstPrid() {
		return dstPrid;
	}

	public void setDstPrid(String dstPrid) {
		this.dstPrid = dstPrid;
	}
	public String getEditstate() {
		return editstate;
	}
	public void setEditstate(String editstate) {
		this.editstate = editstate;
	}
	

}
