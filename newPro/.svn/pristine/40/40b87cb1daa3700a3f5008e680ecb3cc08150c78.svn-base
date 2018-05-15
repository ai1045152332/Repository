package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wj on 2016-07-16.
 */
@Entity
@Table(name="liveplan")
public class Liveplan implements java.io.Serializable {
    private int liveplanId;
    private Integer liveplanHost;
    private Date liveplanDate;
    private Byte liveplanWeek;
    private Integer liveplanSection;
    private String liveplanChannel;
    private String liveplanSessionid;
    private String liveplanRoomid;
    private Byte liveplanAutoclose;
    private String liveplanExt;
    private Integer liveplanFlag;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "liveplan_id")
    public int getLiveplanId() {
        return liveplanId;
    }

    public void setLiveplanId(int liveplanId) {
        this.liveplanId = liveplanId;
    }

    @Basic
    @Column(name = "liveplan_host")
    public Integer getLiveplanHost() {
        return liveplanHost;
    }

    public void setLiveplanHost(Integer liveplanHost) {
        this.liveplanHost = liveplanHost;
    }

    @Basic
    @Column(name = "liveplan_date")
    public Date getLiveplanDate() {
        return liveplanDate;
    }

    public void setLiveplanDate(Date liveplanDate) {
        this.liveplanDate = liveplanDate;
    }

    @Basic
    @Column(name = "liveplan_week")
    public Byte getLiveplanWeek() {
        return liveplanWeek;
    }

    public void setLiveplanWeek(Byte liveplanWeek) {
        this.liveplanWeek = liveplanWeek;
    }

    @Basic
    @Column(name = "liveplan_section")
    public Integer getLiveplanSection() {
        return liveplanSection;
    }

    public void setLiveplanSection(Integer liveplanSection) {
        this.liveplanSection = liveplanSection;
    }

    @Basic
    @Column(name = "liveplan_channel")
    public String getLiveplanChannel() {
        return liveplanChannel;
    }

    public void setLiveplanChannel(String liveplanChannel) {
        this.liveplanChannel = liveplanChannel;
    }

    @Basic
    @Column(name = "liveplan_sessionid")
    public String getLiveplanSessionid() {
        return liveplanSessionid;
    }

    public void setLiveplanSessionid(String liveplanSessionid) {
        this.liveplanSessionid = liveplanSessionid;
    }

    @Basic
    @Column(name = "liveplan_roomid")
    public String getLiveplanRoomid() {
        return liveplanRoomid;
    }

    public void setLiveplanRoomid(String liveplanRoomid) {
        this.liveplanRoomid = liveplanRoomid;
    }

    @Basic
    @Column(name = "liveplan_autoclose")
    public Byte getLiveplanAutoclose() {
        return liveplanAutoclose;
    }

    public void setLiveplanAutoclose(Byte liveplanAutoclose) {
        this.liveplanAutoclose = liveplanAutoclose;
    }

    @Basic
    @Column(name = "liveplan_ext")
    public String getLiveplanExt() {
        return liveplanExt;
    }

    public void setLiveplanExt(String liveplanExt) {
        this.liveplanExt = liveplanExt;
    }

    @Basic
    @Column(name = "liveplan_flag")
    public Integer getLiveplanFlag() {
        return liveplanFlag;
    }

    public void setLiveplanFlag(Integer liveplanFlag) {
        this.liveplanFlag = liveplanFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Liveplan liveplan = (Liveplan) o;

        if (liveplanId != liveplan.liveplanId) return false;
        if (liveplanHost != null ? !liveplanHost.equals(liveplan.liveplanHost) : liveplan.liveplanHost != null)
            return false;
        if (liveplanDate != null ? !liveplanDate.equals(liveplan.liveplanDate) : liveplan.liveplanDate != null)
            return false;
        if (liveplanWeek != null ? !liveplanWeek.equals(liveplan.liveplanWeek) : liveplan.liveplanWeek != null)
            return false;
        if (liveplanChannel != null ? !liveplanChannel.equals(liveplan.liveplanChannel) : liveplan.liveplanChannel != null)
            return false;
        if (liveplanSessionid != null ? !liveplanSessionid.equals(liveplan.liveplanSessionid) : liveplan.liveplanSessionid != null)
            return false;
        if (liveplanRoomid != null ? !liveplanRoomid.equals(liveplan.liveplanRoomid) : liveplan.liveplanRoomid != null)
            return false;
        if (liveplanAutoclose != null ? !liveplanAutoclose.equals(liveplan.liveplanAutoclose) : liveplan.liveplanAutoclose != null)
            return false;
        if (liveplanExt != null ? !liveplanExt.equals(liveplan.liveplanExt) : liveplan.liveplanExt != null)
            return false;
        if (liveplanFlag != liveplan.liveplanFlag) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = liveplanId;
        result = 31 * result + (liveplanHost != null ? liveplanHost.hashCode() : 0);
        result = 31 * result + (liveplanDate != null ? liveplanDate.hashCode() : 0);
        result = 31 * result + (liveplanWeek != null ? liveplanWeek.hashCode() : 0);
        result = 31 * result + (liveplanChannel != null ? liveplanChannel.hashCode() : 0);
        result = 31 * result + (liveplanSessionid != null ? liveplanSessionid.hashCode() : 0);
        result = 31 * result + (liveplanRoomid != null ? liveplanRoomid.hashCode() : 0);
        result = 31 * result + (liveplanAutoclose != null ? liveplanAutoclose.hashCode() : 0);
        result = 31 * result + (liveplanExt != null ? liveplanExt.hashCode() : 0);
        result = 31 * result + (liveplanFlag != null ? liveplanFlag.hashCode() : 0);
        return result;
    }
}
