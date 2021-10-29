package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

public class OpeningHourDto {

    private int id;
    private Date date;
    private Time startTime;
    private Time endTime;
    //private Set<Date> holiday;
    //The Holiday class is one that should have been written down, but since it is not specified, it will be written later on.
  
    //OpeningHours Associations
    private HeadLibrarianDto headLibrarian;
    
	public OpeningHourDto() {
	}

	// public OpeningHourDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public OpeningHourDto(int id, Date date, Time startTime, Time endTime, HeadLibrarianDto headLibrarian) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }


    public Time getEndTime() {
        return endTime;
    }

    public HeadLibrarianDto getHeadLibrarian() {
        return headLibrarian;
    } 
}