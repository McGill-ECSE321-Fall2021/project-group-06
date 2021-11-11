package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.librarysystem.service.HeadLibrarianService;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;

@CrossOrigin(origins="*")
@RestController
public class HeadLibrarianController {
    
	 @Autowired
	 private HeadLibrarianService headService;
	
	 /**
	  * Create head librarian Dto with given parameters
	  * @param id
	  * @param addr
	  * @param name
	  * @return head librarian Dto
	  */
	 @PostMapping(value= {"/headlibrarian", "/headlibrarian/"})
	 public HeadLibrarianDto createHeadLibrarian(@RequestParam(name="id") int id) {
	 	HeadLibrarian head=headService.createHeadLibrarian(id);
	 	return Conversion.convertToDto(head);
	 }
	 /**
	  * Find head librarian of given parameter
	  * @param id
	  * @return head librarian Dto
	  */
	 @GetMapping(value= {"/headlibrarian/{id}", "/headlibrarian/{id}/"})
	 public HeadLibrarianDto getHeadLibrarianById(@PathVariable("id") int id) {
	 	return Conversion.convertToDto(headService.getHeadLibrarian(id));
	 }
	
//	 /**
//	  * Update head librarian address, name, and items checked out
//	  * If not changing something, pass old value
//	  * @param aId
//	  * @param aAddress
//	  * @param aName
//	  * @param itemsChecked
//	  * @return updated head librarian Dto
//	  */
//	 @PutMapping(value= {"/updateHeadlibrarian/{id}/{addr}/{name}/{items}", "/updateHeadlibrarian/{id}/{addr}/{name}/{items}/"})
//	 public HeadLibrarianDto updateHeadLibrarianInfo(@PathVariable("id") int aId, @PathVariable("newID") int newID) {
//	 	HeadLibrarian head=headService.updateHeadLibrarianInfo(aId, newID);
//	 	return convertToDto(head);
//	 }
	
	 /**
	  * Delete head librarian of corresponding parameter
	  * @param id
	  * @return deleted head librarian Dto
	  */
	 @PutMapping(value= {"/deleteHeadlibrarian/{id}", "/deleteHeadlibrarian/{id}/"})
	 public HeadLibrarianDto deleteHeadLibrarian(@PathVariable("id") int id) {
	 	return Conversion.convertToDto(headService.deleteHeadLibrarian(id));
	 }
	
	 /**
	  * Hire i.e. create librarian
	  * @param id
	  * @param addr
	  * @param name
	  * @return librarian Dto
	  */
	 @PostMapping(value= {"/hireLibrarian", "/hireLibrarian/"})
	 public LibrarianDto hireLibrarian(@RequestParam(name="id") int id) {
	 	return Conversion.convertToDto(headService.hireLibrarian(id));
	 }
	
	 /**
	  * Fire i.e. delete librarian of corresponding parameter
	  * @param id
	  * @return deleted librarian Dto
	  */
	 @PutMapping(value= {"/fireLibrarian/{id}", "/fireLibrarian/{id}/"})
	 public LibrarianDto fireLibrarian(@PathVariable("id") int id) {
	 	return Conversion.convertToDto(headService.fireLibrarian(id));
	 }
	
	 /**
	  * Find all librarians
	  * @return list of librarians Dto
	  */
	 @GetMapping(value= {"/headLibrarians", "/headLibrarians/"})
	 public List<HeadLibrarianDto> getHeadLibrarians(){
	 	List<HeadLibrarianDto> librDtos=new ArrayList<>();
	 	for (HeadLibrarian l : headService.getHeadLibrarians()) {
	 		librDtos.add(Conversion.convertToDto(l));
	 	}
	 	return librDtos;
	 }
	 
	 /**
	  * Create opening hour Dto of corresponding parameters
	  * @param id
	  * @param dayOfWeek
	  * @param startTime
	  * @param endTime
	  * @return opening hour Dto
	  */
	 @PostMapping(value= {"/openingHour", "/openingHour/"})
	 public OpeningHourDto createOpeningHour(@RequestParam(name="id") int id, @RequestParam(name="dayOfWeek") DayOfWeek dayOfWeek, 
			 @RequestParam(name="start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime, 
			 @RequestParam(name="end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time endTime) {
		 OpeningHour opHr=headService.createOpeningHour(id, dayOfWeek, startTime, endTime);
		return Conversion.convertToDto(opHr);
	 }
	 
	 /**
	  * Update a corresponding opening hour dto with given parameters
	  * @param id
	  * @param newDayOfWeek
	  * @param newStartTime
	  * @param newEndTime
	  * @return updated opening hour Dto
	  */
	 @PutMapping(value= {"/openingHour/{id}", "openingHour/{id}/"})
	 public OpeningHourDto updateOpeningHour(@PathVariable("id") int id, @RequestParam(name="newDay") DayOfWeek newDayOfWeek, 
			 @RequestParam(name="newStart") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time newStartTime, 
			 @RequestParam(name="newEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time newEndTime) {
		 OpeningHour updatedHrs=headService.updateOpeningHour(id, newDayOfWeek, newStartTime, newEndTime);
		 return Conversion.convertToDto(updatedHrs);
	 }
	 
	 /**
	  * Create shift with given parameters
	  * @param shiftID
	  * @param dayOfWeek
	  * @param startTime
	  * @param endTime
	  * @return shift Dto
	  */
	 @PostMapping(value= {"/shift", "/shift/"})
	 public ShiftDto createShift(@RequestParam(name="id") int shiftID, @RequestParam(name="day") DayOfWeek dayOfWeek, 
			 @RequestParam(name="start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime, 
			 @RequestParam(name="end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time endTime) {
		 Shift s=headService.createShift(shiftID, dayOfWeek, startTime, endTime);
		 return Conversion.convertToDto(s);
	 }
	 
	 /**
	  * Update a corresponding shift dto with given parameters
	  * @param shiftID
	  * @param newDayOfWeek
	  * @param newStartTime
	  * @param newEndTime
	  * @return updated shift Dto
	  */
	 @PutMapping(value= {"/updateShift/{id}", "/updateShift/{id}/"})
	 public ShiftDto updateShift(@PathVariable("id") int shiftID, @RequestParam(name="newDay") DayOfWeek newDayOfWeek, 
			 @RequestParam(name="newStart") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time newStartTime, 
			 @RequestParam(name="newEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time newEndTime) {
		 Shift updShift=headService.updateShift(shiftID, newDayOfWeek, newStartTime, newEndTime);
		 return Conversion.convertToDto(updShift);
	 }
	 
	 /**
	  * Assign a corresponding shift to the corresponding librarian
	  * @param id
	  * @param shiftID
	  * @return all assigned shifts Dto of the librarian
	  */
	 @PostMapping(value= {"/assignShift", "/assignShift/"})
	 public Set<ShiftDto> assignShift(@RequestParam(name="librId") int id, @RequestParam(name="shiftId") int shiftID) {
		 Set<Shift> allAssignedShifts=headService.assignShift(id, shiftID);
		 Set<ShiftDto> allShiftsDto=new HashSet<ShiftDto>();
		 for (Shift s : allAssignedShifts) {
			 allShiftsDto.add(Conversion.convertToDto(s));
		 }
		 return allShiftsDto;
	 }
}
