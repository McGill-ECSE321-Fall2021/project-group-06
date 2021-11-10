package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.MediaRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.dao.ShiftRepository;
import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
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
    LibrarianRepository librarianRepository;
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
    public HeadLibrarian createHeadLibrarian(int aId) {
    	if (aId==0) {
        throw new IllegalArgumentException("Head Librarian id cannot be 0.");
      }
      if (librarianRepository.findLibrarianById(aId)!=null) {
        throw new IllegalArgumentException("Librarian id already exists");
      }
    	HeadLibrarian head=new HeadLibrarian();
    	head.setId(aId);
      librarianRepository.save(head);
		return head;
    }
    /**
     * Find head librarian with given parameter
     * @param aId
     * @return the head librarian
     */
    @Transactional
    public HeadLibrarian getHeadLibrarian(int aId) {
      if (librarianRepository.findLibrarianById(aId)==null) {
        throw new IllegalArgumentException("Librarian id does not exist");
      }
    	return (HeadLibrarian) librarianRepository.findLibrarianById(aId);
    }
    
    /**
     * Find a head librarian and change their info with given parameters
     * @param aId
     * @param aAddress
     * @param aName
     * @param itemsChecked
     */
    @Transactional
    public HeadLibrarian updateHeadLibrarianInfo(int aId, int newID) {
      if (librarianRepository.findLibrarianById(aId)==null) {
        throw new IllegalArgumentException("Librarian does not exist");
      }
      if (librarianRepository.findLibrarianById(newID)!=null) {
        throw new IllegalArgumentException("Librarian id already exists");
      }
    	HeadLibrarian head=(HeadLibrarian) librarianRepository.findLibrarianById(aId);
		  head.setId(newID);
      librarianRepository.save(head);
		  return head;
    }
    
    /**
     * Delete head librarian corresponding to the given parameter
     * @param aId
     */
    @Transactional
    public HeadLibrarian deleteHeadLibrarian(int aId) {
      if (librarianRepository.findLibrarianById(aId)==null) {
        throw new IllegalArgumentException("Librarian does not exist");
      }
    	HeadLibrarian head=(HeadLibrarian) librarianRepository.findLibrarianById(aId);
    	//shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) head));
    	librarianRepository.delete(head);
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
    public Librarian hireLibrarian(int aId) {
    	//i.e. createLibrarian
    	if (aId==0) {
    		throw new IllegalArgumentException("Librarian id cannot be 0.");
    	}
      if (librarianRepository.findLibrarianById(aId)!=null) {
        throw new IllegalArgumentException("Librarian id exists");
      }
    	Librarian librarian=new Librarian();
    	librarian.setId(aId);
      librarianRepository.save(librarian);
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
      if (librarianRepository.findLibrarianById(aId)==null) {
        throw new IllegalArgumentException("Librarian id does not exist");
      }
		Librarian firedLibr=librarianRepository.findLibrarianById(aId);
    	//shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) firedLibr));
    	librarianRepository.delete(firedLibr);
		return (Librarian) firedLibr;
    }
	
	/**
	 * Find all head librarians
	 * @return list of all head librarians
	 */
	@Transactional
	public List<HeadLibrarian> getHeadLibrarians(){
		List<Librarian> allLibrarians=(List<Librarian>) librarianRepository.findAll();
		List<HeadLibrarian> allHeadLibrarians=new ArrayList<>();
		for (Librarian a : allHeadLibrarians) {
			if (a instanceof HeadLibrarian){
				allLibrarians.add((HeadLibrarian) a);
			}
		}
		return allHeadLibrarians;
	}
  //create opening hour
  //create shift
  //assigns shifts
}
