package ca.mcgill.ecse321.librarysystem.dto;

import java.util.*;
/**
* @author Howard
*/
public class LibrarianDto {
    private int id;
    private String password;
    private boolean IsHl;
    private Set<ShiftDto> shifts;
    
	public LibrarianDto() {
    }

	public LibrarianDto( int id, boolean isHL,String password, HashSet<ShiftDto> shifts) {
        this.id = id;
        this.password = password;
        this.shifts = shifts;
        this.IsHl = isHL;
	}

    public Set<ShiftDto> getShifts() {
        return shifts;
    }   

    public int getId(){
        return this.id;
    }

    public boolean getHL(){
        return this.IsHl;
    }
    
    public String getPassword() {
        return password;
    }

}