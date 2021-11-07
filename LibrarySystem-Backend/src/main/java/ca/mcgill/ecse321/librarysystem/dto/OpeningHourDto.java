package ca.mcgill.ecse321.librarysystem.dto;
import java.sql.Time;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
public class OpeningHourDto {

    private int id;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
    //private Set<Date> holiday;
    //The Holiday class is one that should have been written down, but since it is not specified, it will be written later on.
  
    //OpeningHours Associations
    private HeadLibrarianDto headLibrarian;
    
	public OpeningHourDto() {
	}

    //this class needs to have a headLibrarian, so we will have to include it
    public OpeningHourDto(int id) {
        this(id, DayOfWeek.Monday, Time.valueOf("00:00:00"), Time.valueOf("23:59:59"), new HeadLibrarianDto());
    }

	public OpeningHourDto(int id, DayOfWeek dayOfWeek, Time startTime, Time endTime, HeadLibrarianDto headLibrarian) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
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