package ca.mcgill.ecse321.librarysystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@Autowired
	private ShiftService shiftService;
	
	@PostMapping(value= {"/headlibrarian", "/headlibrarian/"})
	public HeadLibrarianDto createHeadLibrarian(@RequestParam int id, @RequestParam String addr,
			@RequestParam String name) throws IllegalArgumentException {
		HeadLibrarian head=headService.createHeadLibrarian(id, addr, name, AccountCategory.Offline, true, 0);
		return convertToDto(head);
	}
	
	@GetMapping(value= {"/headlibrarian/{id}", "/headlibrarian/{id}/"})
	public HeadLibrarianDto getHeadLibrarianById(@PathVariable("id") int id) throws IllegalArgumentException{
		return convertToDto(headService.getHeadLibrarian(id));
	}
	
	@GetMapping(value= {"/librarians", "/librarians/"})
	public List<LibrarianDto> getAllLibrarians(){
		List<LibrarianDto> librDtos=new ArrayList<>();
		for (Librarian l : headService.getLibrarians()) {
			librDtos.add(convertToDto(l));
		}
		return librDtos;
	}
	//TODO
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
	//TODO
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
	//TODO
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
