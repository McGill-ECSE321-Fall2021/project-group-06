package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class LibrarianDto extends AccountDto {

    private Set<ShiftDto> shifts;
    
	public LibrarianDto() {
	}

	// public HeadLibrarianDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

	public LibrarianDto( HashSet<ShiftDto> shifts) {
        this.shifts = shifts;
	}

    public Set<ShiftDto> getShifts() {
        return shifts;
    }   

}