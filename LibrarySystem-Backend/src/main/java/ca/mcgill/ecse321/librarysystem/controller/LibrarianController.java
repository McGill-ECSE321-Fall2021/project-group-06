package ca.mcgill.ecse321.librarysystem.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.OfflineDto;
import ca.mcgill.ecse321.librarysystem.dto.ShiftDto;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.service.LibrarianService;


@CrossOrigin(origins="*")
@RestController
public class LibrarianController {
	@Autowired
	private LibrarianService librarianService;
	
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l==null) 
			throw new IllegalArgumentException("Librarian not found.");
		LibrarianDto librarian=new LibrarianDto();
		
		return librarian;
	}
	
	private ShiftDto convertToDto(Shift s) {
		if (s==null) 
			throw new IllegalArgumentException("Shift not found.");
		ShiftDto shift=new ShiftDto();
		
		return shift;
	}
	
	private OfflineDto convertToDto(Offline o) {
		if (o==null) 
			throw new IllegalArgumentException("Offline account not found.");
		OfflineDto account=new OfflineDto();
		
		return account;
	}
	
	private List<ShiftDto> convertToDto(Set<Shift> s){
		if(s==null)
			throw new IllegalArgumentException("Shifts not found");
		List<ShiftDto> shifts = new ArrayList<>();
		for (Shift shift : s) {
			shifts.add(convertToDto(shift));
		}
		return shifts;
	}
	
	/**
	 * @author Isabella Hao
	 * Create librarian Dto with given parameters
	 * @param id
	 * @param addr
	 * @param name
	 * @return librarian Dto */
	 
	@PostMapping(value= {"/createLibrarian/{id}/{addr}/{name}", "/createLibrarian/{id}/{addr}/{name}/"})
	public LibrarianDto createLibrarian(@PathVariable("id") int id) {
		Librarian librarian=librarianService.createLibrarian(id);
		return convertToDto(librarian);
	}
	
	/**
	 * Find librarian of given parameter
	 * @param id
	 * @return librarian Dto */
	 
	@GetMapping(value= {"/librarian/{id}", "/librarian/{id}/"})
	public LibrarianDto getLibrarianById(@PathVariable("id") int id) {
		return convertToDto(librarianService.getLibrarian(id));
	}
	
	/**
	 * Update librarian address, name, and items checked out
	 * If not changing something, pass old value
	 * @param aId
	 * @param aAddress
	 * @param aName
	 * @param itemsChecked
	 * @return updated librarian Dto */
	 
	@PutMapping(value= {"/updateLibrarian/{id}/{addr}/{name}/{items}", "/updateLibrarian/{id}/{addr}/{name}/{items}/"})
	public LibrarianDto updateLibrarian(@PathVariable("id") int aId, @PathVariable("newID") int newID) {
		Librarian lib=librarianService.updateLibrarian(aId, newID);
		return convertToDto(lib);
	}
	
	/**
	 * Delete librarian of corresponding parameter
	 * @param id
	 * @return deleted librarian Dto */
	 
	@PutMapping(value= {"/deleteLibrarian/{id}", "/deleteLibrarian/{id}/"})
	public LibrarianDto deleteLibrarian(int id) {
		return convertToDto(librarianService.deleteLibrarian(id));
	}
	
	/**
	 * Create offline account
	 * @param id
	 * @param addr
	 * @param name
	 * @return offline Dto */
	 
	@PostMapping(value= {"/createOfflineAccount/{id}/{addr}/{name}", "/createOfflineAccount/{id}/{addr}/{name}/"})
	public OfflineDto createOfflineAccount(@PathVariable("id") int id, @PathVariable("addr") String addr, 
			@PathVariable("name") String name) {
		return convertToDto(librarianService.createOfflineAccount(id, name, addr,AccountCategory.Offline, true, 0));
	}
	
	/**
	 *  View personal schedule
	 * @param id
	 * @return Shift Dto */
	 
	@GetMapping(value= {"/viewPersonalSchedule/{id}", "/viewPersonalSchedule/{id}/"})
	public ShiftDto viewPersonalSchedule(@PathVariable("id") int id) {
		return (ShiftDto) convertToDto(librarianService.viewPersonalShift(id));
	} 
	
	/**
	 * Find all librarians
	 * @return list of librarians Dto */
	 
	@GetMapping(value= {"/getLibrarians", "/getLibrarians/"})
	public List<LibrarianDto> getLibrarians(){
		List<LibrarianDto> librDtos=new ArrayList<>();
		for (Librarian l : librarianService.getLibrarians()) {
			librDtos.add(convertToDto(l));
		}
		return librDtos;
	} 
	
}
