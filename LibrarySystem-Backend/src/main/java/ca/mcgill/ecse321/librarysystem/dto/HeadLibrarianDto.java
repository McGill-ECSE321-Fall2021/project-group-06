package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class HeadLibrarianDto extends LibrarianDto {

    private Set<OpeningHourDto> openingHours;
    private Set<ShiftDto> shifts;
    
	public HeadLibrarianDto() {
	}

	// public HeadLibrarianDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public HeadLibrarianDto(HashSet<OpeningHourDto> openingHours, HashSet<ShiftDto> shifts) {
        this.openingHours = openingHours;
        this.shifts = shifts;
	}

    public Set<OpeningHourDto> getOpeningHours() {
        return openingHours;
    }

    public Set<ShiftDto> getShifts() {
        return shifts;
    }   

}