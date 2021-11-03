package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class LibrarianDto extends AccountDto {

    private Set<ShiftDto> shifts;
    
	public LibrarianDto() {
	}
	//we don't need another constructor I don't think so

	public LibrarianDto( HashSet<ShiftDto> shifts) {
        this.shifts = shifts;
	}

    public Set<ShiftDto> getShifts() {
        return shifts;
    }   

}