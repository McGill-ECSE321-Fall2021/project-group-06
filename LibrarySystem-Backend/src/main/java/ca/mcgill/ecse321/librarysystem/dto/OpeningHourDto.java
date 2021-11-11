package ca.mcgill.ecse321.librarysystem.dto;
import java.sql.Time;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
public class OpeningHourDto {

    private int id;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;
	public OpeningHourDto() {
	}
    public OpeningHourDto(int id) {
        this(id, DayOfWeek.Monday, Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
    }

	public OpeningHourDto(int id, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
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
}