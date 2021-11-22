package ca.mcgill.ecse321.librarysystem.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.service.LibrarianService;


@CrossOrigin(origins="*")
@RestController
public class LibrarianController {
	@Autowired
	private LibrarianService librarianService;
	
	/**
	 * Librarian Dto login with given parameters
	 * @param id
	 * @param pwd
	 * @return librarian Dto
	 */
	@GetMapping(value= {"/librLogin", "/librLogin/"})
	public LibrarianDto login(@RequestParam int id, @RequestParam String pwd) {
		Librarian l=null;
		l=librarianService.login(id, pwd);
		if (l instanceof Librarian) {
			return Conversion.convertToDto(l);
		}
		else return null;
	}
	/**
	 * Create librarian Dto with given parameters
	 * @param id
	 * @param addr
	 * @param name
	 * @return librarian Dto
	 * @author Howard Yu, David Hu */
	 
	@PostMapping(value= {"/createLibrarian/{id}", "/createLibrarian/{id}/"})
	public LibrarianDto createLibrarian(@PathVariable("id") int id) {
		Librarian librarian=librarianService.createLibrarian(id);
		return Conversion.convertToDto(librarian);
	}
	
	/**
	 * Find librarian of given parameter
	 * @param id
	 * @return librarian Dto 
	 * @author Howard Yu, David Hu*/
	 
	@GetMapping(value= {"/librarians/{id}", "/librarians/{id}/"})
	public LibrarianDto getLibrarianById(@PathVariable("id") int id) {
		return Conversion.convertToDto(librarianService.getLibrarian(id));
	}
	
	/**
	 * Delete librarian of corresponding parameter
	 * @param id
	 * @author Howard Yu*/
	 
	@DeleteMapping(value= {"/deleteLibrarian/{id}", "/deleteLibrarian/{id}/"})
	public LibrarianDto deleteLibrarian(@PathVariable("id") int id) {
		return Conversion.convertToDto(librarianService.deleteLibrarian(id));
	}
	
	/**
	 * Create offline account
	 * @param id
	 * @param addr
	 * @param name
	 * @return offline Dto
	 * @author Howard Yu*/
	 
	@PostMapping(value= {"/createOfflineAccount/{id}/{addr}/{name}", "/createOfflineAccount/{id}/{addr}/{name}/"})
	public OfflineDto createOfflineAccount(@PathVariable("id") int id, @PathVariable("addr") String addr, 
			@PathVariable("name") String name) {
		return Conversion.convertToDto(librarianService.createOfflineAccount(id, name, addr,AccountCategory.Offline, true, 0));
	}
	
	/**
	 *  View personal schedule
	 * @param id
	 * @return Shift Dto
	 * @author Howard Yu*/
	 
	@GetMapping(value= {"/viewPersonalSchedule/{id}", "/viewPersonalSchedule/{id}/"})
	public HashSet<ShiftDto> viewPersonalSchedule(@PathVariable("id") int id) {
		Set<Shift> shifts = librarianService.viewPersonalShift(id);
		HashSet<ShiftDto> shiftDto = new HashSet<ShiftDto>();
		for(Shift shift: shifts){
			shiftDto.add(Conversion.convertToDto(shift));
		}
		return shiftDto;
	} 
	
	/**
	 * Find all librarians
	 * @return list of librarians Dto
	 * @author Howard Yu */
	 
	@GetMapping(value= {"/librarians", "/librarians/"})
	public List<LibrarianDto> getLibrarians(){
		return librarianService.getAllLibrarians().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
	} 
	
}
