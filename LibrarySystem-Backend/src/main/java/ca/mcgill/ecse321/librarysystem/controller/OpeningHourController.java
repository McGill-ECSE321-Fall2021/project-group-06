package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
    public OpeningHourDto createOpeningHour(@RequestParam int id, @RequestParam DayOfWeek DayOfWeek, @RequestParam String start, @RequestParam String end) throws Exception{
        Time startTime = Time.valueOf(start);
		Time endTime = Time.valueOf(end);
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
     * @author Howard Yu
     */
    @GetMapping(value= {"/openingHours/{id}", "/openingHours/{id}/"})
	public OpeningHourDto getOpeningHourById(@PathVariable("id") int id) {
		return Conversion.convertToDto(service.getOpeningHour(id));
	}

    @GetMapping(value= {"/openingHours/", "/openingHours/"})
	public List<OpeningHourDto> getAllOpeningHours() {
        List<OpeningHourDto> openingHourDtos = new ArrayList<>();
        for (OpeningHour openingHour : service.getAllOpeningHours()) {
            openingHourDtos.add(Conversion.convertToDto(openingHour));
        }
        return openingHourDtos;
	}
     /**
	 * Update Opening Hour
	 * @param id
	 * @param newDay
	 * @param newStart
	 * @param newEnd
	 * @return updated Opening Hour Dto */
	 
	@PutMapping(value= {"/updateOpeningHour/{id}"})
	public OpeningHourDto updateOpeningHour(@PathVariable("id") int aId, @RequestParam DayOfWeek newDay, @RequestParam String newStart,
    @RequestParam String newEnd) {
        Time startTime = Time.valueOf(newStart);
		Time endTime = Time.valueOf(newEnd);
		OpeningHour oH=service.updateOpeningHours(aId, newDay, startTime, endTime);
		return Conversion.convertToDto(oH);
	}
    /**
	 * Delete Opening Hour of corresponding parameter
	 * @param id
	 * @return deleted Opening Hour Dto */
	 
	@PutMapping(value= {"/deleteOpeningHour/{id}", "/deleteOpeningHour/{id}/"})
	public void deleteOpeningHour(int id) {
		service.deleteOpeningHour(id);
	}
}
