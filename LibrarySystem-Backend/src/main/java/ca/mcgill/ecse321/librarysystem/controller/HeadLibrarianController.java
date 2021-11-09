package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;
import ca.mcgill.ecse321.librarysystem.service.HeadLibrarianService;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;

@CrossOrigin(origins="*")
@RestController
public class HeadLibrarianController {
    
	@Autowired
	private HeadLibrarianService headService;
	@Autowired
	private OpeningHourService hourService;
	/*@Autowired
	private ShiftService shiftService;*/
	
	/**
	 * Create head librarian Dto with given parameters
	 * @param id
	 * @param addr
	 * @param name
	 * @return head librarian Dto
	 */
	@PostMapping(value= {"/createHeadlibrarian/{id}/{addr}/{name}", "/createHeadlibrarian/{id}/{addr}/{name}/"})
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("id") int id) {
		HeadLibrarian head=headService.createHeadLibrarian(id);
		return convertToDto(head);
	}
	/**
	 * Find head librarian of given parameter
	 * @param id
	 * @return head librarian Dto
	 */
	@GetMapping(value= {"/headlibrarian/{id}", "/headlibrarian/{id}/"})
	public HeadLibrarianDto getHeadLibrarianById(@PathVariable("id") int id) {
		return convertToDto(headService.getHeadLibrarian(id));
	}
	
	/**
	 * Update head librarian address, name, and items checked out
	 * If not changing something, pass old value
	 * @param aId
	 * @param aAddress
	 * @param aName
	 * @param itemsChecked
	 * @return updated head librarian Dto
	 */
	@PutMapping(value= {"/updateHeadlibrarian/{id}/{addr}/{name}/{items}", "/updateHeadlibrarian/{id}/{addr}/{name}/{items}/"})
	public HeadLibrarianDto updateHeadLibrarianInfo(@PathVariable("id") int aId, @PathVariable("newID") int newID) {
		HeadLibrarian head=headService.updateHeadLibrarianInfo(aId, newID);
		return convertToDto(head);
	}
	
	/**
	 * Delete head librarian of corresponding parameter
	 * @param id
	 * @return deleted head librarian Dto
	 */
	@PutMapping(value= {"/deleteHeadlibrarian/{id}", "/deleteHeadlibrarian/{id}/"})
	public HeadLibrarianDto deleteHeadLibrarian(int id) {
		return convertToDto(headService.deleteHeadLibrarian(id));
	}
	
	/**
	 * Hire i.e. create librarian
	 * @param id
	 * @param addr
	 * @param name
	 * @return librarian Dto
	 */
	@PostMapping(value= {"/hireLibrarian/{id}/{addr}/{name}", "/hireLibrarian/{id}/{addr}/{name}/"})
	public LibrarianDto hireLibrarian(@PathVariable("id") int id) {
		return convertToDto(headService.hireLibrarian(id));
	}
	
	/**
	 * Fire i.e. delete librarian of corresponding parameter
	 * @param id
	 * @return deleted librarian Dto
	 */
	@PutMapping(value= {"/fireLibrarian/{id}", "/fireLibrarian/{id}/"})
	public LibrarianDto fireLibrarian(@PathVariable("id") int id) {
		return convertToDto(headService.fireLibrarian(id));
	}
	
	/**
	 * Find all librarians
	 * @return list of librarians Dto
	 */
	@GetMapping(value= {"/getHeadLibrarians", "/getHeadLibrarians/"})
	public List<HeadLibrarianDto> getHeadLibrarians(){
		List<HeadLibrarianDto> librDtos=new ArrayList<>();
		for (HeadLibrarian l : headService.getHeadLibrarians()) {
			librDtos.add(convertToDto(l));
		}
		return librDtos;
	}
	
	//-------------------Helper methods-----------------------------------
	
	private HeadLibrarianDto convertToDto(HeadLibrarian h) {
		if (h==null) {
			throw new IllegalArgumentException("Head Librarian not found.");
		}
		HeadLibrarianDto headDto=new HeadLibrarianDto(createOpHourDtosForHead(h));
		/*either:
		   --> keep above null & headDto.setShifts(shifts) after shiftDto constructor has been established
		 		(shifts is hashSet of ShiftDto)
		or --> constructor for shiftDto is null and this one is not with createShiftDtos(h)
		 		(shiftDto will have to setHeadLibr and setLibrarians
		*/
		return headDto;
	}
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l==null) {
			throw new IllegalArgumentException("Librarian not found.");
		}
		LibrarianDto lib=new LibrarianDto(null);
		/*same issue: either createShiftDtos(l) as argument
		or have shiftDto be established first*/
		return lib;
	}
	
    private HashSet<OpeningHourDto> createOpHourDtosForHead(HeadLibrarian h) {
		HashSet<OpeningHour> opHourForHead=(HashSet<OpeningHour>) hourService.getOpeningHourList(h);
		HashSet<OpeningHourDto> opHour=new HashSet<>();
		for (OpeningHour op : opHourForHead) {
			opHour.add(Conversion.convertToDto(op));
		}
		return opHour;
	}
    
	/*
	private HashSet<Shift> createShiftDtos(HeadLibrarian h) {
		HashSet<Shift> shiftForHead=shiftService.getShifts(h);
		HashSet<ShiftDto> shifts=new HashSet<>();
		for (Shift s : shiftForHead) {
			shifts.add(ShiftDto.convertToDto(s));
		}
		return shifts;
	}
	*/
}
