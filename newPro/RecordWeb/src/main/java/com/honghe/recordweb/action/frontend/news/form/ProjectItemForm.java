package com.honghe.recordweb.action.frontend.news.form;

public class ProjectItemForm {
	private String prid;
	private String pid;
	private String bid;
	private String quipub;  // 快速发布的标志
	private String repa;  // resource下PPT的MD5文件 用于拷贝至edit目录下
	private String soupa;  // PPT的原名称 用于修改mapped.txt中的对应关系
	public String getSoupa() {
		return soupa;
	}
	public void setSoupa(String soupa) {
		this.soupa = soupa;
	}
	public String getRepa() {
		return repa;
	}
	public void setRepa(String repa) {
		this.repa = repa;
	}
	private String params;
	public String getQuipub() {
		return quipub;
	}
	public void setQuipub(String quipub) {
		this.quipub = quipub;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
}
