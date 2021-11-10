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
import ca.mcgill.ecse321.librarysystem.models.CheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.models.Librarian;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.NonCheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Online;
import ca.mcgill.ecse321.librarysystem.models.Shift;

import java.util.ArrayList;
import java.util.List;
//David Hu
@Service
public class OnlineService {
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
     * @param events
     * @param medias
     * @return the head librarian created
     */

    @Transactional
    public Online createOnline(int aId, String aAddress, String aName, 
    		AccountCategory accountCategory, boolean local, int itemsChecked, String username, String password, String email) {
    	if (aId==0) {
            throw new IllegalArgumentException("Online Account id cannot be 0.");
        }
    	if (aAddress==null) {
    		throw new IllegalArgumentException("Online Account must have an address.");
    	}
    	if (aName==null) {
    		throw new IllegalArgumentException("Online Account must have a name.");
    	}
    	if (accountCategory==AccountCategory.Offline) {
    		throw new IllegalArgumentException("Online account must be of type online.");
    	}
    	if (local==false) {
    		throw new IllegalArgumentException("Online Account must be a local");
    	}
        if (username==null){
            throw new IllegalArgumentException("Online Account must have a username");
        }
        if (password==null){
            throw new IllegalArgumentException("Online Account must have a password");
        }
        if (email==null){
            throw new IllegalArgumentException("Online Account must have an email");
        }
        usernameIsValid(username);

    	Online online=new Online();
    	online.setId(aId);
    	online.setAddress(aAddress);
    	online.setName(aName);
    	online.setAccountCategory(accountCategory);
    	online.setIsLocal(local);
    	online.setNumChecked(itemsChecked);
        online.setUsername(username);
        online.setPassword(password);
        online.setEmail(email);
		return online;
    }

    /**
     * Find offline with given parameter
     * @param onlineUsername
     * @return the offline
     */
    @Transactional
    public Online getOnline(int id) {
    	return (Online) accountRepository.findAccountById(id);
    }

    /**
     * Find a head librarian and change their info with given parameters
     * @param aId
     * @param aAddress
     * @param aName
     * @param itemsChecked
     */
    @Transactional
    public Online updateOnline(int aId, String aAddress, String aName, int itemsChecked, String username, String password, String email) {
    	Online online=(Online) accountRepository.findAccountById(aId);
    	online.setAddress(aAddress);
    	online.setName(aName);
        online.setNumChecked(itemsChecked);
        online.setUsername(username);
        online.setPassword(password);
        online.setEmail(email);
        usernameIsValid(username);
		return online;
    }

    /**
     * Delete head librarian corresponding to the given parameter
     * @param aId
     */
    @Transactional
    public Online deleteOnline(int aId) {
    	Online online=(Online) accountRepository.findAccountById(aId);
    	//mediaRepository.deleteAll(mediaRepository.findByAccount(offline));
    	//eventRepository.deleteAll(eventRepository.findByAccount(offline));
    	accountRepository.delete(online);
		return online;
    }

    @Transactional
	public List<Online> getAllOnlines(){
		List<Account> allAccounts=(List<Account>) accountRepository.findAll();
		List<Online> allOnlines=new ArrayList<>();
		for (Account a : allAccounts) {
			if (a instanceof Online){
				allOnlines.add((Online) a);
			}
		}
		return allOnlines;
	}

    @Transactional
    public Media checkoutAnItem(int mediaId, int id){
        Online online = (Online) accountRepository.findAccountById(id);
        if(online == null){
            throw new IllegalArgumentException("This account does not exist!");
        }
        if(online.getNumChecked() >= 15){
            throw new IllegalArgumentException("This account has too many items checked out!");
        } else {
            if(mediaRepository.findMediaByID(mediaId) == null){
                throw new IllegalArgumentException("This media Id is non-existent!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof NonCheckOutItem){
                throw new IllegalArgumentException("This media Id corresponds to an item that you cannot check out!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof CheckOutItem){
                online.getMedias().add(mediaRepository.findMediaByID(mediaId));
                return mediaRepository.findMediaByID(mediaId);
            }
        }
        throw new IllegalArgumentException("System Error");
    }

    @Transactional
    public Media returnAnItem(int mediaId, int id){
        Online online = (Online) accountRepository.findAccountById(id);
        if(online == null){
            throw new IllegalArgumentException("This account does not exist!");
        }
        if(online.getNumChecked() >= 15){
            throw new IllegalArgumentException("This account has too many items checked out!");
        } else {
            if(mediaRepository.findMediaByID(mediaId) == null){
                throw new IllegalArgumentException("This media Id is non-existent!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof NonCheckOutItem){
                throw new IllegalArgumentException("This media Id corresponds to an item that you cannot check out!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof CheckOutItem){
                if (online.getMedias().contains(mediaRepository.findById(mediaId))){
                    online.getMedias().remove(mediaRepository.findMediaByID(mediaId));
                } else {
                    throw new IllegalArgumentException("This user does not have the following item checked out!");
                }
                return mediaRepository.findMediaByID(mediaId);
            }
        }
        throw new IllegalArgumentException("System Error");
    }


    //helper methods

    private boolean usernameIsValid(String username) {
		if(accountRepository.findOnlineByUsername(username)!=null) throw new IllegalArgumentException("Username is already taken");
        return true;
	}

}
