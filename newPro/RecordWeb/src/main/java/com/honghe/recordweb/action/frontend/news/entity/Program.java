package com.honghe.recordweb.action.frontend.news.entity;

import java.util.ArrayList;
import java.util.List;


public class Program implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id ;
	
	private String status;
	
	private String name;
	
	private String title;
	
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
	
	private Integer level;
	
	private boolean isDel;
	
	private Integer playPlanId;
	
	private Integer duration;
	
	private String starttime;
	private String endtime;
	private String startdate;
	private String enddate;
	private String weekSelected;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWeekSelected() {
		return weekSelected;
	}
	public void setWeekSelected(String weekSelected) {
		this.weekSelected = weekSelected;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(boolean isDel) {
		this.isDel = isDel;
	}
	public Integer getPlayPlanId() {
		return playPlanId;
	}
	public void setPlayPlanId(Integer playPlanId) {
		this.playPlanId = playPlanId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
