package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Shift;

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
    
    @Transactional
    public HeadLibrarian createHeadLibrarian(int aId, String aAddress, String aName, 
    		AccountCategory accountCategory, boolean local, int itemsChecked) {
    	/*if (aId==0) {
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
    	}*/
    	HeadLibrarian head=new HeadLibrarian();
    	head.setId(aId);
    	head.setAddress(aAddress);
    	head.setName(aName);
    	head.setAccountCategory(accountCategory);
    	head.setIsLocal(local);
    	head.setNumChecked(itemsChecked);
		return head;
    }
    
    @Transactional
    public HeadLibrarian getHeadLibrarian(int aId) {
    	return (HeadLibrarian) accountRepository.findAccountById(aId);
    }
    
    @Transactional
    public void updateHeadLibrarianInfo(int aId, String aAddress, String aName, int itemsChecked) {
    	HeadLibrarian head=(HeadLibrarian) accountRepository.findAccountById(aId);
    	head.setAddress(aAddress);
    	head.setName(aName);
    }
    
    @Transactional
    public void deleteHeadLibrarian(int aId) {
    	accountRepository.delete(accountRepository.findAccountById(aId));
    }
    
    @Transactional
    public Librarian hireLibrarian(int aId, String aAddress, String aName, 
    		AccountCategory accountCategory, boolean local, int itemsChecked) {
    	//i.e. createLibrarian
    	/*if (aId==0) {
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
    	}*/
    	Librarian librarian=new Librarian();
    	librarian.setId(aId);
    	librarian.setAddress(aAddress);
    	librarian.setName(aName);
    	librarian.setAccountCategory(accountCategory);
    	librarian.setIsLocal(local);
    	librarian.setNumChecked(itemsChecked);
		return librarian;
    }
    
	@Transactional
    public void fireLibrarian(int aId) {	//i.e. deleteLibrarian
    	/*if (aId==0) {
    		throw new IllegalArgumentException("There is not a id 0 account.");
    	}*/
		Account firedLibr=accountRepository.findAccountById(aId);
    	shiftRepository.deleteAll(shiftRepository.findByLibrarian((Librarian) firedLibr));
    	mediaRepository.deleteAll(mediaRepository.findByAccount(firedLibr));
    	eventRepository.deleteAll(eventRepository.findByAccount(firedLibr));
    	accountRepository.delete(firedLibr);
    }
}
