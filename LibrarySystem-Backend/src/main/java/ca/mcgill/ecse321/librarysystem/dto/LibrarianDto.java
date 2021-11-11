package ca.mcgill.ecse321.librarysystem.dto;

import java.util.*;

public class LibrarianDto {
    private int id;
    private Set<ShiftDto> shifts;
    
	public LibrarianDto() {
	}
	//we don't need another constructor I don't think so

	public LibrarianDto( int id, HashSet<ShiftDto> shifts) {
        this.id = id;
        this.shifts = shifts;
	}

    public Set<ShiftDto> getShifts() {
        return shifts;
    }   

    public int getId(){
        return this.id;
    }

}