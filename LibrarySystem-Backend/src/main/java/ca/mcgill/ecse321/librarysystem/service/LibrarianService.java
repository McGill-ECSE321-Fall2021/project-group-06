package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Shift;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibrarianService {
	 @Autowired
	 LibrarianRepository librarianRepository;
	 @Autowired
	 ShiftRepository shiftRepository;
	
	/**
	 * @author Isabella Hao
	 * View Personal Shift
	 *  */
	 
	@Transactional
    public List<Shift> viewPersonalShift(int id) {
		Librarian librarian = (Librarian) librarianRepository.findLibrarianById(id);
		List<Shift> shifts = shiftRepository.findByLibrarian(librarian);
		return shifts;
	}
    
    /**
	 * @author Isabella Hao
	 * Create Offline Account
	 * */
	 
	@Transactional
    public Offline createOfflineAccount(int id, String name,
    		String address, AccountCategory accountCategory, 
    		boolean local, int itemsChecked) {
		//Librarian librarian = (Librarian) accountRepository.findAccountById(id);
		//if(librarian == null)
		//	throw new IllegalArgumentException("Authorization Denied.");
		Offline account = new Offline();
		account.setAccountCategory(accountCategory);
		account.setAddress(address);
		account.setId(id);
		account.setIsLocal(local);
		account.setNumChecked(itemsChecked);
		account.setName(name);
		return account;
	}
	
	@Transactional
	public Librarian createLibrarian(int id) {
		Librarian librarian = new Librarian();
		librarian.setId(id);
		
		return librarian;
	}
	
	@Transactional 
	public Librarian updateLibrarian(int id, int newID) {
		Librarian librarian = (Librarian) librarianRepository.findLibrarianById(id);
		librarian.setId(newID);
    	return librarian;
  
    }
	
	@Transactional
	public void deleteAllLibrarian() {
		List<Librarian> allLibrarians=(List<Librarian>) librarianRepository.findAll();
		for(Librarian l : allLibrarians) 
			librarianRepository.delete(l);
	}
	
	@Transactional
	public List<Librarian> getLibrarians(){
		List<Librarian> allLibrarians=(List<Librarian>) librarianRepository.findAll();
		return allLibrarians;
	}
	
	@Transactional
    public Librarian deleteLibrarian(int aId) {
    	Librarian librarian = (Librarian) librarianRepository.findLibrarianById(aId);
    	librarianRepository.delete(librarian);
    	return librarian;
	}
	
	@Transactional 
	public Librarian getLibrarian(int id) {
		Librarian lib = (Librarian) librarianRepository.findLibrarianById(id);
		return lib;
	}
}
	
