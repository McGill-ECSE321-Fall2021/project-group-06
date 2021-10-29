package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class HeadLibrarianDto extends LibrarianDto {

    private Set<OpeningHourDto> openingHours;
    private Set<ShiftDto> shifts;
    
	public HeadLibrarianDto() {
	}
    //we don't need another constructor I don't think so)
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