package com.niit.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Event {
	
	@Id
	private String eventid;
	private String event_name;
	private Date date_of_event;
	private String venue;
	private String description;
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public Date getDate_of_event() {
		return date_of_event;
	}
	public void setDate_of_event(Date date_of_event) {
		this.date_of_event = date_of_event;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", event_name=" + event_name + ", date_of_event=" + date_of_event
				+ ", venue=" + venue + ", description=" + description + "]";
	}
	
	

}
