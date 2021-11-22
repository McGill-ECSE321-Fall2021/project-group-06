package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.*;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.service.HeadLibrarianService;
@CrossOrigin(origins="*")
@RestController
public class HeadLibrarianController {
    
	 @Autowired
	 private HeadLibrarianService headService;
	
	 /**
	  * Create head librarian Dto with given parameters
	  * @param id
	  * @return head librarian Dto
	  *@author Howard Yu
	  */
	 @PostMapping(value= {"/create_headlibrarian", "/create_headlibrarian/"})
	 public HeadLibrarianDto createHeadLibrarian(@RequestParam(name="id") int id, @RequestParam String pwd) {
	 	HeadLibrarian head=headService.createHeadLibrarian(id, pwd);
	 	return Conversion.convertToDto(head);
	 }
	 /**
	  * Find head librarian of given parameter
	  * @param id
	  * @return head librarian Dto
	  *@author Howard Yu
	  */
	 @GetMapping(value= {"/headlibrarians/{id}", "/headlibrarians/{id}/"})
	 public HeadLibrarianDto getHeadLibrarianById(@PathVariable("id") int id) {
	 	return Conversion.convertToDto(headService.getHeadLibrarian(id));
	 }
	 @GetMapping(value= {"/headlibrarians", "/headlibrarians/"})
	 public List<HeadLibrarianDto> getAllHeadLibrarians() {
		return headService.getHeadLibrarians().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
	 }
	
	 /**
	  * Delete head librarian of corresponding parameter
	  * @param id
	  * @return deleted head librarian Dto
	  *@author Howard Yu
	  */
	 @PutMapping(value= {"/deleteHeadlibrarian/{id}", "/deleteHeadlibrarian/{id}/"})
	 public HeadLibrarianDto deleteHeadLibrarian(@PathVariable("id") int id) {
	 	return Conversion.convertToDto(headService.deleteHeadLibrarian(id));
	 }
	
	 /**
	  * Hire i.e. create librarian
	  * @param id
	  * @return librarian Dto
	  *@author Howard Yu
	  */
	 @PostMapping(value= {"/hireLibrarian", "/hireLibrarian/"})
	 public LibrarianDto hireLibrarian(@RequestParam int id, @RequestParam String pwd) {
	 	return Conversion.convertToDto(headService.hireLibrarian(id, pwd));
	 }
	
	 /**
	  * Fire i.e. delete librarian of corresponding parameter
	  * @param id
	  * @return deleted librarian Dto
	  *@author Howard Yu
	  */
	 @PutMapping(value= {"/fireLibrarian/{id}", "/fireLibrarian/{id}/"})
	 public LibrarianDto fireLibrarian(@PathVariable("id") int id) {
	 	return Conversion.convertToDto(headService.fireLibrarian(id));
	 }
	
	 /**
	  * Find all librarians
	  * @return list of librarians Dto
	  *@author Howard Yu
	  */
	 @GetMapping(value= {"/headLibrarians", "/headLibrarians/"})
	 public List<HeadLibrarianDto> getHeadLibrarians(){
		 return headService.getHeadLibrarians().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
	 }
	 
	 /**
	  * Create opening hour Dto of corresponding parameters
	  * @param id
	  * @param dayOfWeek
	  * @param startTime
	  * @param endTime
	  * @return opening hour Dto
	  *@author Howard Yu
	  */
	 @PostMapping(value= {"/create_openingHour/{id}", "/create_openingHour/{id}/"})
	 public OpeningHourDto createOpeningHour(@PathVariable(name="id") int id, @RequestParam DayOfWeek dayOfWeek, 
			 @RequestParam String startTime, 
			 @RequestParam String endTime) {
				Time start = Time.valueOf(startTime);
				Time end = Time.valueOf(endTime);
		 OpeningHour opHr=headService.createOpeningHour(id, dayOfWeek, start, end);
		return Conversion.convertToDto(opHr);
	 }
	 
	 /**
	  * Update a corresponding opening hour dto with given parameters
	  * @param id
	  * @param newDayOfWeek
	  * @param newStartTime
	  * @param newEndTime
	  * @return updated opening hour Dto
	  *@author Howard Yu
	  */
	 @PutMapping(value= {"/update_openingHour/{id}", "update_openingHour/{id}/"})
	 public OpeningHourDto updateOpeningHour(@PathVariable("id") int id, @RequestParam DayOfWeek newDayOfWeek, 
			 @RequestParam String newStartTime, 
			 @RequestParam String newEndTime) {
				Time start = Time.valueOf(newStartTime);
				Time end = Time.valueOf(newEndTime);
		 OpeningHour updatedHrs=headService.updateOpeningHour(id, newDayOfWeek, start, end);
		 return Conversion.convertToDto(updatedHrs);
	 }
	 
	 /**
	  * Create shift with given parameters
	  * @param shiftID
	  * @param dayOfWeek
	  * @param startTime
	  * @param endTime
	  * @return shift Dto
	  *@author Howard Yu
	  */
	 @PostMapping(value= {"/create_shift/{id}", "/create_shift/{id}/"})
	 public ShiftDto createShift(@PathVariable("id") int shiftID, @RequestParam DayOfWeek dayOfWeek, 
			 @RequestParam  String startTime, 
			 @RequestParam String endTime) {
				Time start = Time.valueOf(startTime);
				Time end = Time.valueOf(endTime);
		 Shift s=headService.createShift(shiftID, dayOfWeek, start, end);
		 return Conversion.convertToDto(s);
	 }
	 
	 /**
	  * Update a corresponding shift dto with given parameters
	  * @param shiftID
	  * @param newDayOfWeek
	  * @param newStartTime
	  * @param newEndTime
	  * @return updated shift Dto
	  *@author Howard Yu
	  */
	 @PutMapping(value= {"/headupdateShift/{id}", "/headupdateShift/{id}/"})
	 public ShiftDto updateShift(@PathVariable("id") int shiftID, @RequestParam DayOfWeek newDayOfWeek, 
			 @RequestParam String newStartTime, 
			 @RequestParam String newEndTime) {
				Time start = Time.valueOf(newStartTime);
				Time end = Time.valueOf(newEndTime);
		 Shift updShift=headService.updateShift(shiftID, newDayOfWeek, start, end);
		 return Conversion.convertToDto(updShift);
	 }
	 
	 /**
	  * Assign a corresponding shift to the corresponding librarian
	  * @param id
	  * @param shiftID
	  * @return all assigned shifts Dto of the librarian
	  *@author Howard Yu
	  */
	 @PutMapping(value= {"/assignShift/{id}", "/assignShift/{id}/"})
	 public LibrarianDto assignShift(@PathVariable(name="id") int id, @RequestParam int shiftID) {
		 Librarian lib = headService.assignShift(id, shiftID);
		 return (Conversion.convertToDto(lib));
	 }
}
