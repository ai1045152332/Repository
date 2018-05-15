package com.honghe.recordweb.action.frontend.news.entity;

import java.util.ArrayList;
import java.util.List;


public class Rprogram implements java.io.Serializable{
	
	
	private String devices;
	
	private Integer id ;
	
	private String name;
	
	private String prid ;
	
	private String curIdx;
	
	private String state;
	
//	private String ratio;

	private String thumb;

	private String w;
	
	private String h;
	
	private String updateTime;
	
	private String editstate;
	
	private String createname;
	
	private String note;  // 备注
	
	private String theme; // 主题
	
	private String isEdit="1";//是否为编辑页面下
	
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
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
	public String getEditstate() {
		return editstate;
	}
	// add by lihuimin  20140609 begin
 	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	//add by dx
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//end
	public String getCreatename() {
		return createname;
	}
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	// add by lihuimin  20140609 end
	public void setEditstate(String editstate) {
		this.editstate = editstate;
	}

	private List<String> pageList = new ArrayList<String>();
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	
	public List<String> getPageList() {
		return pageList;
	}

	public void setPageList(List<String> pageList) {
		this.pageList = pageList;
	}

	public String getPrid() {
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}

	public String getCurIdx() {
		return curIdx;
	}

	public void setCurIdx(String curIdx) {
		this.curIdx = curIdx;
	}
	
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getDevices() {
		return devices;
	}
	public void setDevices(String devices) {
		this.devices = devices;
	}
	
}
