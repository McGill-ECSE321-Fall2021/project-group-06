package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    /**
     * @author Niels Mainville
     * Create OpeningHours Dto with given parameters
     * @param id
     * @param DayOfWeek
     * @param startTime
     * @param endTime
     * @throws Exception
     * @return openingHour Dto
     */
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
    /**
     * Find Opening Hour of given parsameter
     * @param id
     * @return openingHour Dto
     */
    @GetMapping(value= {"/openingHours/{id}", "/openingHours/{id}/"})
	public OpeningHourDto getOpeningHourById(@PathVariable("id") int id) {
		return Conversion.convertToDto(service.getOpeningHour(id));
	}
     /**
	 * Update Opening Hour
	 * @param id
	 * @param newDay
	 * @param newStart
	 * @param newEnd
	 * @return updated Opening Hour Dto */
	 
	@PutMapping(value= {"/updateOpeningHour/{id}/{newDay}/{newStart}/{newEnd}/"})
	public OpeningHourDto updateOpeningHour(@PathVariable("id") int aId, @PathVariable("newDay") DayOfWeek newDay, @PathVariable("newStart") Time newStart,
			@PathVariable("newEnd") Time newEnd) {
		OpeningHour oH=service.updateOpeningHours(aId, newDay, newStart, newEnd);
		return Conversion.convertToDto(oH);
	}
    /**
	 * Delete Opening Hour of corresponding parameter
	 * @param id
	 * @return deleted Opening Hour Dto */
	 
	// @PutMapping(value= {"/deleteOpeningHour/{id}", "/deleteOpeningHour/{id}/"})
	// public OpeningHourDto deleteOpeningHour(int id) {
	// 	// return Conversion.convertToDto(service.deleteOpeningHour(id)
	// }
}
