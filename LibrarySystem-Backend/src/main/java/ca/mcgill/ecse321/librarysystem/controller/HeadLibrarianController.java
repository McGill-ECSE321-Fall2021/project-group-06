package ca.mcgill.ecse321.librarysystem.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.OpeningHourDto;
import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
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
	
	HeadLibrarianDto convertToDto(HeadLibrarian h) {
		if (h==null) {
			throw new IllegalArgumentException("Head Librarian not found.");
		}
		HeadLibrarianDto headDto=new HeadLibrarianDto(createOpHourDtosForHead(h), null);
		//headDto.setShifts(shifts);
		//shifts is hashSet of ShiftDto
		return headDto;
	}
    private HashSet<OpeningHourDto> createOpHourDtosForHead(HeadLibrarian h) {
		HashSet<OpeningHour> opHourForHead=(HashSet<OpeningHour>) hourService.getOpeningHourList(h);
		HashSet<OpeningHourDto> opHour=new HashSet<>();
		for (OpeningHour op : opHourForHead) {
			opHour.add(Conversion.convertToDto(op));
		}
		return opHour;
	}
	
	//This method bases on the assumption that shiftDto will be done first
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
