package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;

@CrossOrigin(origins = "*")
@RestController
public class OpeningHourController {

    @Autowired
    private OpeningHourService service;

    @PostMapping(value = { "/openingHours", "/openingHours/"})
    public OpeningHourDto createOpeningHour(@RequestParam int id, @RequestParam DayOfWeek DayOfWeek, @RequestParam Time startTime, @RequestParam Time endTime) throws Exception{
        // OpeningHour openingHour = null;
        try{
            OpeningHour openingHour= service.createOpeningHour(id, DayOfWeek, startTime, endTime);
            return Conversion.convertToDto(openingHour);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }        
    }
}
