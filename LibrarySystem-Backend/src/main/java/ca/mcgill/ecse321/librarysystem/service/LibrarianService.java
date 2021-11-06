package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Shift;

import java.util.ArrayList;
import java.util.List;

public class LibrarianService {
	 @Autowired
	 AccountRepository accountRepository;
	 @Autowired
	 ShiftRepository shiftRepository;
	
	/**
	 * @author Isabella Hao
	 * View Personal Shift
	 * 
	 */
	@Transactional
    public List<Shift> viewPersonalShift(int id) {
		Librarian librarian = (Librarian) accountRepository.findAccountById(id);
		String name = librarian.getName();
		List<Shift> shifts = shiftRepository.findByLibrarian(librarian);
		return shifts;
	}
    
    /**
	 * @author Isabella Hao
	 * Create Offline Account
	 * 
	 */
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
	public Librarian createLibrarian(int id, String name, String address,
			AccountCategory accountCategory, boolean local, int itemsChecked) {
		Librarian librarian = new Librarian();
		librarian.setAccountCategory(accountCategory);
		librarian.setAddress(address);
		librarian.setId(id);
		librarian.setName(name);
		librarian.setIsLocal(local);
		librarian.setNumChecked(itemsChecked);
		
		return librarian;
	}
	
	@Transactional 
	public Librarian updateLibrarian(int id, String name, String address, int itemsChecked) {
		Librarian librarian = (Librarian) accountRepository.findAccountById(id);
    	librarian.setAddress(address);
    	librarian.setName(name);
    	librarian.setNumChecked(itemsChecked);
    	return librarian;
  
    }
	
	@Transactional
	public void deleteAllLibrarian() {
		List<Account> allAccounts=(List<Account>) accountRepository.findAll();
		List<Librarian> allLibrarians = new ArrayList<>();
		for (Account a : allAccounts) {
			if (a instanceof Librarian)
				allLibrarians.add((Librarian) a);
		}
		for(Librarian l : allLibrarians) 
			accountRepository.delete(l);
	}
	
	@Transactional
	public List<Librarian> getLibrarians(){
		List<Account> allAccounts=(List<Account>) accountRepository.findAll();
		List<Librarian> allLibrarians=new ArrayList<>();
		for (Account a : allAccounts) {
			if (a instanceof Librarian){
				allLibrarians.add((Librarian) a);
			}
		}
		return allLibrarians;
	}
	
	@Transactional
    public Librarian deleteLibrarian(int aId) {
    	Librarian librarian = (Librarian) accountRepository.findAccountById(aId);
    	accountRepository.delete(librarian);
    	return librarian;
	}
	
	@Transactional 
	public Librarian getLibrarian(int id) {
		Librarian lib = (Librarian) accountRepository.findAccountById(id);
		return lib;
	}
}
	
