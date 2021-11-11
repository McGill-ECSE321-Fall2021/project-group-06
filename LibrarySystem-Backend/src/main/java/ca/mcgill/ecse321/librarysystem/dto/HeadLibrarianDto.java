package ca.mcgill.ecse321.librarysystem.dto;

import java.util.*;
/**
* @author Howard
*/
public class HeadLibrarianDto extends LibrarianDto {

    private Set<OpeningHourDto> openingHours;
	public HeadLibrarianDto() {
	}
	public HeadLibrarianDto(int id, HashSet<OpeningHourDto> openingHours, HashSet<ShiftDto> shifts) {
        super(id, shifts);
        this.openingHours = openingHours;
	}

    public Set<OpeningHourDto> getOpeningHours() {
        return openingHours;
    }

}