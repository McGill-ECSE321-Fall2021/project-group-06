package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Shift;

import java.util.List;
import java.util.Set;

@Service
public class LibrarianService {
	 @Autowired
	 LibrarianRepository librarianRepository;
	 @Autowired
	 ShiftRepository shiftRepository;
	 @Autowired
	 AccountRepository accountRepository;
	 
	 /**
	  * Librarian login with given parameters
	  * @param id
	  * @param pwd
	  * @return the librarian
	  */
	 @Transactional
	 public Librarian login(int id, String pwd) {
		 if (librarianRepository.findLibrarianById(id)==null) {
			 throw new IllegalArgumentException("Invalid Id");
		 }
		 /*if (!(librarianRepository.findLibrarianById(id) instanceof Librarian)) {
			 throw new IllegalArgumentException("Please login through the appropriate place");
		 }*/
		 if (librarianRepository.findLibrarianById(id).getPassword().equals(pwd)) {
			 return librarianRepository.findLibrarianById(id);
		 }
		 else throw new IllegalArgumentException("Incorrect password");
	 }
	 
	/**
	 * @author Isabella Hao
	 * View Personal Shift
	 *  */
	 
	@Transactional
    public Set<Shift> viewPersonalShift(int id) {
		if (librarianRepository.findLibrarianById(id) == null){
			throw new IllegalArgumentException("Librarian id does not exist");
		}
		Librarian librarian = (Librarian) librarianRepository.findLibrarianById(id);
		Set<Shift> shifts = librarian.getShift();

		return shifts;
	}

	@Transactional
	public Librarian setPassword(int id, String password) {
		if (librarianRepository.findLibrarianById(id) == null){
			throw new IllegalArgumentException("Librarian id does not exist");
		}
		Librarian lib = librarianRepository.findLibrarianById(id);
		lib.setPassword(password);
		librarianRepository.save(lib);
		return lib;
	}
    
    /**
	 * @author Isabella Hao
	 * Create Offline Account
	 * */
	 
	@Transactional
    public Offline createOfflineAccount(int id, String name, String address, AccountCategory accountCategory, boolean local, int itemsChecked) {
		if (id==0) {
            throw new IllegalArgumentException("Offline Account id cannot be 0.");
        }
        if (accountRepository.findAccountById(id) != null) {
            throw new IllegalArgumentException("Offline Account id already exists.");
        }
    	if (address==null) {
    		throw new IllegalArgumentException("Offline Account must have an address.");
    	}
    	if (name==null) {
    		throw new IllegalArgumentException("Offline Account must have a name.");
    	}
        if (accountCategory==null){
            throw new IllegalArgumentException("account must have a type");
        }
    	if (accountCategory==AccountCategory.Online) {
    		throw new IllegalArgumentException("Offline account must be of type offline.");
    	}
    	if (local==false) {
    		throw new IllegalArgumentException("Offline Account must be a local");
    	}
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
		accountRepository.save(account);
		return account;
	}
	
	@Transactional
	public Librarian createLibrarian(int id, String password) {
		if (id==0) {
            throw new IllegalArgumentException("Librarian id cannot be 0.");
        }
		if (librarianRepository.findLibrarianById(id) != null){
			throw new IllegalArgumentException("Librarian id already exist");
		}
		Librarian librarian = new Librarian();
		librarian.setId(id);
		librarian.setPassword(password);
		librarianRepository.save(librarian);
		return librarian;
	}
	// @Transactional 
	// public Librarian updateLibrarian(int id, int newID) {
	// 	if (librarianRepository.findLibrarianById(id) == null){
	// 		throw new IllegalArgumentException("Librarian id does not exist");
	// 	}
	// 	if (librarianRepository.findLibrarianById(newID) != null && id != newID){
	// 		throw new IllegalArgumentException("Librarian id already exist");
	// 	}
	// 	if (newID == 0){
	// 		throw new IllegalArgumentException("Librarian id cannot be 0");
	// 	}
	// 	Librarian librarian = librarianRepository.findLibrarianById(id);
	// 	librarian.setId(newID);
	// 	librarianRepository.save(librarian);
    // 	return librarian;
  
    // }
	
	@Transactional
	public void deleteAllLibrarian() {
		Iterable<Librarian> allLibrarians= librarianRepository.findAll();
		for(Librarian l : allLibrarians) 
			librarianRepository.delete(l);
	}
	
	@Transactional
	public List<Librarian> getAllLibrarians(){
		List<Librarian> allLibrarians=(List<Librarian>) librarianRepository.findAll();
		return allLibrarians;
	}
	
	@Transactional
    public Librarian deleteLibrarian(int aId) {
		if (librarianRepository.findLibrarianById(aId) == null){
			throw new IllegalArgumentException("Librarian id does not exist");
		}
    	Librarian librarian = librarianRepository.findLibrarianById(aId);
    	librarianRepository.delete(librarian);
    	return librarian;
	}
	
	@Transactional 
	public Librarian getLibrarian(int id) {
		if (librarianRepository.findLibrarianById(id) == null){
			throw new IllegalArgumentException("Librarian id does not exist");
		}
		Librarian lib = librarianRepository.findLibrarianById(id);
		return lib;
	}
}
	
