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
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.service.HeadLibrarianService;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;
import ca.mcgill.ecse321.librarysystem.service.ShiftService;

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
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("id") int id, @PathVariable("addr") String addr,
			@PathVariable("name") String name) {
		HeadLibrarian head=headService.createHeadLibrarian(id, addr, name, AccountCategory.Offline, true, 0);
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
	public HeadLibrarianDto updateHeadLibrarianInfo(@PathVariable("id") int aId, @PathVariable("addr") String aAddress, 
			@PathVariable("name") String aName, @PathVariable("items") int itemsChecked) {
		HeadLibrarian head=headService.updateHeadLibrarianInfo(aId, aAddress, aName, itemsChecked);
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
	public LibrarianDto hireLibrarian(@PathVariable("id") int id, @PathVariable("addr") String addr, 
			@PathVariable("name") String name) {
		return convertToDto(headService.hireLibrarian(id, addr, name, AccountCategory.Offline, true, 0));
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
	@GetMapping(value= {"/getLibrarians", "/getLibrarians/"})
	public List<LibrarianDto> getLibrarians(){
		List<LibrarianDto> librDtos=new ArrayList<>();
		for (Librarian l : headService.getLibrarians()) {
			librDtos.add(convertToDto(l));
		}
		return librDtos;
	}
	
	//-------------------Helper methods-----------------------------------
	
	private HeadLibrarianDto convertToDto(HeadLibrarian h) {
		if (h==null) {
			throw new IllegalArgumentException("Head Librarian not found.");
		}
		HeadLibrarianDto headDto=new HeadLibrarianDto(createOpHourDtosForHead(h), null);
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
