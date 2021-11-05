package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLibrarianService {
	
	@Autowired
    AccountRepository accountRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    OpeningHourRepository openingHourRepository;
    @Autowired
    ShiftRepository shiftRepository;
    
    /**
     * Create a head librarian with given parameters
     * @param aId
     * @param aAddress
     * @param aName
     * @param accountCategory
     * @param local
     * @param itemsChecked
     * @return the head librarian created
     */
    
    @Transactional
    public HeadLibrarian createHeadLibrarian(int aId, String aAddress, String aName, 
    		AccountCategory accountCategory, boolean local, int itemsChecked) {
    	if (aId==0) {
            throw new IllegalArgumentException("Head Librarian id cannot be 0.");
        }
    	if (aAddress==null) {
    		throw new IllegalArgumentException("Head Librarian must have an address.");
    	}
    	if (aName==null) {
    		throw new IllegalArgumentException("Head Librarian must have a name.");
    	}
    	if (accountCategory==AccountCategory.Online) {
    		throw new IllegalArgumentException("Head Librarian account must be of type offline.");
    	}
    	if (local==false) {
    		throw new IllegalArgumentException("Head Librarian must be a local");
    	}
    	HeadLibrarian head=new HeadLibrarian();
    	head.setId(aId);
    	head.setAddress(aAddress);
    	head.setName(aName);
    	head.setAccountCategory(accountCategory);
    	head.setIsLocal(local);
    	head.setNumChecked(itemsChecked);
		return head;
    }
    /**
     * Find head librarian with given parameter
     * @param aId
     * @return the head librarian
     */
    @Transactional
    public HeadLibrarian getHeadLibrarian(int aId) {
    	return (HeadLibrarian) accountRepository.findAccountById(aId);
    }
    
    /**
     * Find a head librarian and change their info with given parameters
     * @param aId
     * @param aAddress
     * @param aName
     * @param itemsChecked
     */
    @Transactional
    public HeadLibrarian updateHeadLibrarianInfo(int aId, String aAddress, String aName, int itemsChecked) {
    	HeadLibrarian head=(HeadLibrarian) accountRepository.findAccountById(aId);
    	head.setAddress(aAddress);
    	head.setName(aName);
		return head;
    }
    
    /**
     * Delete head librarian corresponding to the given parameter
     * @param aId
     */
    @Transactional
    public HeadLibrarian deleteHeadLibrarian(int aId) {
    	HeadLibrarian head=(HeadLibrarian) accountRepository.findAccountById(aId);
    	shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) head));
    	mediaRepository.deleteAll(mediaRepository.findByAccount(head));
    	eventRepository.deleteAll(eventRepository.findByAccount(head));
    	accountRepository.delete(head);
		return head;
    }
    
    /**
     * Create librarian with given parameters
     * @param aId
     * @param aAddress
     * @param aName
     * @param accountCategory
     * @param local
     * @param itemsChecked
     * @return
     */
    @Transactional
    public Librarian hireLibrarian(int aId, String aAddress, String aName, 
    		AccountCategory accountCategory, boolean local, int itemsChecked) {
    	//i.e. createLibrarian
    	if (aId==0) {
    		throw new IllegalArgumentException("Librarian id cannot be 0.");
    	}
    	if (aAddress==null) {
    		throw new IllegalArgumentException("Librarian must have an address.");
    	}
    	if (aName==null) {
    		throw new IllegalArgumentException("Librarian must have a name.");
    	}
    	if (accountCategory==AccountCategory.Online) {
    		throw new IllegalArgumentException("Librarian account must be of type offline.");
    	}
    	if (local==false) {
    		throw new IllegalArgumentException("Librarian must be a local");
    	}
    	Librarian librarian=new Librarian();
    	librarian.setId(aId);
    	librarian.setAddress(aAddress);
    	librarian.setName(aName);
    	librarian.setAccountCategory(accountCategory);
    	librarian.setIsLocal(local);
    	librarian.setNumChecked(itemsChecked);
		return librarian;
    }
    
    /**
     * Delete librarian corresponding to given parameter
     * @param aId
     */
	@Transactional
    public Librarian fireLibrarian(int aId) {	//i.e. deleteLibrarian
    	if (aId==0) {
    		throw new IllegalArgumentException("There is not an account with id 0.");
    	}
		Account firedLibr=accountRepository.findAccountById(aId);
    	shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) firedLibr));
    	mediaRepository.deleteAll(mediaRepository.findByAccount(firedLibr));
    	eventRepository.deleteAll(eventRepository.findByAccount(firedLibr));
    	accountRepository.delete(firedLibr);
		return (Librarian) firedLibr;
    }
	
	/**
	 * Find all librarians
	 * @return list of all librarians
	 */
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
}
