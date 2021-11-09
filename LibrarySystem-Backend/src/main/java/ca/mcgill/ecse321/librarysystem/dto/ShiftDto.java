package ca.mcgill.ecse321.librarysystem.dto;
import java.sql.Time;

import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
public class ShiftDto {

    private int shiftID;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
    //private Set<Date> holiday;
    //The Holiday class is one that should have been written down, but since it is not specified, it will be written later on.
  
    //OpeningHours Associations
    
	public ShiftDto() {
	}
    public ShiftDto(int shiftID) {
        this(shiftID, DayOfWeek.Monday, Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
    }

	public ShiftDto(int shiftID, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
        this.shiftID = shiftID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getShiftID() {
        return shiftID;
    }

    // public HeadLibrarianDto getHeadLibrarian() {
    //     return headLibrarian;
    // }

    // public LibrarianDto getLibrarian() {
    //     return librarian;
    // }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
    
}