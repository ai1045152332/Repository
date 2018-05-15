package com.honghe.recordweb.action.frontend.report;

/**
 * Created by mz on 2018/3/31.
 */
public class ReportDTO {

    private String groupName;
    private Integer count;
    private String rate;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReportDTO reportDTO = (ReportDTO) o;

		if (groupName != null ? !groupName.equals(reportDTO.groupName) : reportDTO.groupName != null) return false;
		if (count != null ? !count.equals(reportDTO.count) : reportDTO.count != null) return false;
		return rate != null ? rate.equals(reportDTO.rate) : reportDTO.rate == null;

	}

	@Override
	public int hashCode() {
		int result = groupName != null ? groupName.hashCode() : 0;
		result = 31 * result + (count != null ? count.hashCode() : 0);
		result = 31 * result + (rate != null ? rate.hashCode() : 0);
		return result;
	}
}
