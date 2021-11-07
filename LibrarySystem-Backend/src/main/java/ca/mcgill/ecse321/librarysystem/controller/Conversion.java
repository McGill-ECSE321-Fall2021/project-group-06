package ca.mcgill.ecse321.librarysystem.controller;

import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;

public class Conversion {
    public static OpeningHourDto convertToDto(OpeningHour openingHour){
        if(openingHour == null) throw new IllegalArgumentException("Opening Hour not found.");
        return new OpeningHourDto(openingHour.getId(), openingHour.getDayOfWeek(), openingHour.getStartTime(), openingHour.getEndTime(), null);
    }
}
