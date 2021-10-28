package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
public class ShiftDto {

    private int shiftID;
    private HeadLibrarianDto headLibrarian;
    private Set<LibrarianDto> librarians;
    private Date date;
    private Time startTime;
    private Time endTime;
    //private Set<Date> holiday;
    //The Holiday class is one that should have been written down, but since it is not specified, it will be written later on.
  
    //OpeningHours Associations
    
	public ShiftDto() {
	}

	// public OpeningHourDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public ShiftDto(int shiftID, HeadLibrarianDto headLibrarian, HashSet<LibrarianDto> librarians, Date date, Time startTime, Time endTime) {
        this.shiftID = shiftID;
        this.headLibrarian = headLibrarian;
        this.librarians =librarians;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getShiftID() {
        return shiftID;
    }

    public HeadLibrarianDto getHeadLibrarian() {
        return headLibrarian;
    }

    public Set<LibrarianDto> getLibrarians() {
        return librarians;
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
    
}