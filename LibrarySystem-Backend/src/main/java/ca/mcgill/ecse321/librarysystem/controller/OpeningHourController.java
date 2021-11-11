package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
/**
 * @author Niels Mainville
 */
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
    @PostMapping(value = { "/openingHours/{id}", "/openingHours/{id}/"})
    public OpeningHourDto createOpeningHour(@PathVariable("id") int id, @RequestParam DayOfWeek DayOfWeek, @RequestParam String startTime, @RequestParam String endTime) throws Exception{
        try{
            OpeningHour openingHour= service.createOpeningHour(id, DayOfWeek, Conversion.convertStrToTime(startTime), Conversion.convertStrToTime(endTime));
            return Conversion.convertToDto(openingHour);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }        
    }
    /**
     * Find Opening Hour with id
     * @param id
     * @return openingHour Dto
     * @author Howard Yu
     */
    @GetMapping(value= {"/getopeningHours/{id}", "/getopeningHours/{id}/"})
	public OpeningHourDto getOpeningHourById(@PathVariable("id") int id) {
		return Conversion.convertToDto(service.getOpeningHour(id));
	}
    /**
     * Find all OpeningHour
     * @return openingHour Dto
     * @author Howard Yu
     */
    @GetMapping(value= {"/openingHours/", "/openingHours/"})
	public List<OpeningHourDto> getAllOpeningHours() {
        return service.getAllOpeningHours().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
	}
     /**
	 * Update Opening Hour
	 * @param id
	 * @param newDay
	 * @param newStart
	 * @param newEnd
	 * @return updated Opening Hour Dto 
     * @author Howard Yu*/
	 
	@PutMapping(value= {"/updateOpeningHour/{id}"})
	public OpeningHourDto updateOpeningHour(@PathVariable("id") int aId, @RequestParam DayOfWeek newDay, @RequestParam String newStart,
    @RequestParam String newEnd) {
        Time startTime = Time.valueOf(newStart);
		Time endTime = Time.valueOf(newEnd);
		OpeningHour oH=service.updateOpeningHours(aId, newDay, startTime, endTime);
		return Conversion.convertToDto(oH);
	}
    /**
	 * Delete Opening Hour with id
	 * @param id
     * @author Howard Yu
	 */
	 
    @DeleteMapping(value = {"/deleteOpeningHour/{id}"})
    public void deleteOpeningHour(@PathVariable("id") int id){
        service.deleteOpeningHour(id);
    }
}
