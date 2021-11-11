package ca.mcgill.ecse321.librarysystem.controller;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;

@CrossOrigin(origins="*")
@RestController
public class ShiftController {

	@Autowired
	private ShiftService shiftService;
	
	/**
	 * Create shift Dto with given parameters
	 * @param id
	 * @param DayOfWeek
	 * @param start
	 * @param end
	 * @return librarian Dto
	 * @author Howard  */
	 
	@PostMapping(value= {"/createShift/{id}", "/createShift/{id}/"})
	public ShiftDto createShift(@PathVariable("id") int id, @RequestParam DayOfWeek DayOfWeek, @RequestParam String start, @RequestParam String end) {
		Time startTime = Time.valueOf(start);
		Time endTime = Time.valueOf(end);
		Shift shift=shiftService.createShift(id, DayOfWeek, startTime, endTime);
		return Conversion.convertToDto(shift);
	}
	/**
	 * Find shift of given parameter
	 * @param id
	 * @return shift Dto 
	 * @author Howard  */
	 
	@GetMapping(value= {"/shift/{id}", "/shift/{id}/"})
	public ShiftDto getShiftById(@PathVariable("id") int id) {
		return Conversion.convertToDto(shiftService.getShift(id));
	}
	
	/**
	 * Update head librarian, librarians, date, start and end time of shift
	 * @param id
	 * @param DayOfWeek
	 * @param start
	 * @param end
	 * @return updated shift  Dto
	 * @author Howard   */
	 
	@PutMapping(value= {"/updateShift/{id}"})
	public ShiftDto updateShift(@PathVariable("id") int aId,
	@RequestParam DayOfWeek DayOfWeek, @RequestParam String start,
	@RequestParam String end) {
		Time startTime = Time.valueOf(start);
		Time endTime = Time.valueOf(end);
		Shift shift=shiftService.updateShift(aId, DayOfWeek, startTime, endTime);
		return Conversion.convertToDto(shift);
	}
	
	/**
	 * Delete shift with id
	 * @param id
	 * @author Howard  */
	 
	@DeleteMapping(value= {"/deleteShift/{id}"})
	public void deleteShift(@PathVariable("id") int id) {
		shiftService.deleteShift(id);
	}

	
	/**
	 * View schedule
	 * @param id
	 * @return Shift Dto
	 * @author Howard   */
	
	 
	@GetMapping(value= {"/viewSchedule/{id}", "/viewSchedule/{id}/"})
	public ShiftDto viewSchedule(@PathVariable("id") int id) {
		return (ShiftDto) Conversion.convertToDto(shiftService.viewSchedule(id));
	}
	
	/**
	 * Find all shifts
	 * @return list of librarians Dto
	 * @author Howard   */
	 
	@GetMapping(value= {"/getShifts", "/getShifts/"})
	public List<ShiftDto> getShifts(){
		return shiftService.getShifts().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
	}
	
}
