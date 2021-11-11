package ca.mcgill.ecse321.librarysystem.controller;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;

@CrossOrigin(origins="*")
@RestController
public class ShiftController {

	@Autowired
	private ShiftService shiftService;
	
	/**
	 * @author Isabella Hao
	 * Create shift Dto with given parameters
	 * @param id
	 * @param addr
	 * @param name
	 * @return librarian Dto  */
	 
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
	 * @return shift Dto */
	 
	@GetMapping(value= {"/shift/{id}", "/shift/{id}/"})
	public ShiftDto getShiftById(@PathVariable("id") int id) {
		return Conversion.convertToDto(shiftService.getShift(id));
	}
	
	/**
	 * Update head librarian, librarians, date, start and end time of shift
	 * @param id
	 * @param head
	 * @param librarians
	 * @param date
	 * @param start
	 * @param end
	 * @return updated shift  Dto */
	 
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
	 * Delete shift of corresponding parameter
	 * @param id
	 * @return deleted shift Dto */
	 
	@DeleteMapping(value= {"/deleteShift/{id}"})
	public void deleteShift(@PathVariable("id") int id) {
		shiftService.deleteShift(id);
	}
	
	/**
	 * Assign Schedules
	 * @param id
	 * @param libId
	 * @return offline Dto */
	 
	// @PostMapping(value= {"/assignSchedules/{id}/{libId}/", "/assignSchedules/{id}/{libId}"})
	// public ShiftDto assignSchedule(@PathVariable("id") int id,
	// 		@PathVariable("libId") int libId) {
	// 	return convertToDto(shiftService.assignSchedules(id, libId));
	// }
	
	/**
	 * View schedule
	 * @param id
	 * @return Shift Dto */
	 
	@GetMapping(value= {"/viewSchedule/{id}", "/viewSchedule/{id}/"})
	public ShiftDto viewSchedule(@PathVariable("id") int id) {
		return (ShiftDto) Conversion.convertToDto(shiftService.viewSchedule(id));
	}
	
	/**
	 * Find all shifts
	 * @return list of librarians Dto */
	 
	@GetMapping(value= {"/getShifts", "/getShifts/"})
	public List<ShiftDto> getShifts(){
		return shiftService.getShifts().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
	}
	
}
