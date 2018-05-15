package com.honghe.recordhibernate.entity;



import javax.persistence.*;

/**
 * Created by hthwx on 2015/2/3.
 */
@Entity
@Table(name="hostevent")
public class Hostevent {
    private int eventId;
    private String eventName;
    private String eventContent;
    private String eventType;
    private String eventTopic;
    private String eventDesc;
    private String eventRemark;

    @Id
    @Column(name = "event_id")
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "event_name")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "event_content")
    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Basic
    @Column(name = "event_type")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "event_topic")
    public String getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(String eventTopic) {
        this.eventTopic = eventTopic;
    }

    @Basic
    @Column(name = "event_desc")
    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    @Basic
    @Column(name = "event_remark")
    public String getEventRemark() {
        return eventRemark;
    }

    public void setEventRemark(String eventRemark) {
        this.eventRemark = eventRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hostevent hostevent = (Hostevent) o;

        if (eventId != hostevent.eventId) return false;
        if (eventContent != null ? !eventContent.equals(hostevent.eventContent) : hostevent.eventContent != null)
            return false;
        if (eventDesc != null ? !eventDesc.equals(hostevent.eventDesc) : hostevent.eventDesc != null) return false;
        if (eventName != null ? !eventName.equals(hostevent.eventName) : hostevent.eventName != null) return false;
        if (eventRemark != null ? !eventRemark.equals(hostevent.eventRemark) : hostevent.eventRemark != null)
            return false;
        if (eventTopic != null ? !eventTopic.equals(hostevent.eventTopic) : hostevent.eventTopic != null) return false;
        if (eventType != null ? !eventType.equals(hostevent.eventType) : hostevent.eventType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventContent != null ? eventContent.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (eventTopic != null ? eventTopic.hashCode() : 0);
        result = 31 * result + (eventDesc != null ? eventDesc.hashCode() : 0);
        result = 31 * result + (eventRemark != null ? eventRemark.hashCode() : 0);
        return result;
    }
}
