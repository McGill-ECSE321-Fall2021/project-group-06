package ca.mcgill.ecse321.librarysystem.dto;

import java.util.*;
/**
* @author Howard
*/
public class LibrarianDto {
    private int id;
    private String password;
    private Set<ShiftDto> shifts;
    
	public LibrarianDto() {
    }

	public LibrarianDto( int id, String password, HashSet<ShiftDto> shifts) {
        this.id = id;
        this.password = password;
        this.shifts = shifts;
	}

    public Set<ShiftDto> getShifts() {
        return shifts;
    }   

    public int getId(){
        return this.id;
    }
    
    public String getPassword() {
        return password;
    }

}