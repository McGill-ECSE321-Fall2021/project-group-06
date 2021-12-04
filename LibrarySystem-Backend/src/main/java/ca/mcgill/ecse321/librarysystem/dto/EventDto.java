package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
/**
* @author Howard
*/
public class EventDto {

	private String name;
	private Date eventDate;
	private Time eventStart;
	private Time eventEnd;
    
	public EventDto() {
	}

	public EventDto(String name) {
		this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	}

	public EventDto(String name, Date eventDate, Time eventStart, Time eventEnd) {
		this.name = name;
		this.eventDate = eventDate;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
	}

	public String getName() {
		return name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public Time getEventStart() {
		return eventStart;
	}

	public Time getEndTime() {
		return eventEnd;
	}

}