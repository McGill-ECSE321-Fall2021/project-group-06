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
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Media;
import ca.mcgill.ecse321.librarysystem.models.NonCheckOutItem;
import ca.mcgill.ecse321.librarysystem.models.Offline;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfflineService {
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
    public Offline createOffline(int aId, String aAddress, String aName, 
    		AccountCategory accountCategory, boolean local, int itemsChecked) {
    	if (aId==0) {
            throw new IllegalArgumentException("Offline Account id cannot be 0.");
        }
    	if (aAddress==null) {
    		throw new IllegalArgumentException("Offline Account must have an address.");
    	}
    	if (aName==null) {
    		throw new IllegalArgumentException("Offline Account must have a name.");
    	}
    	if (accountCategory==AccountCategory.Online) {
    		throw new IllegalArgumentException("Offline account must be of type offline.");
    	}
    	if (local==false) {
    		throw new IllegalArgumentException("Offline Account must be a local");
    	}
    	Offline offline=new Offline();
    	offline.setId(aId);
    	offline.setAddress(aAddress);
    	offline.setName(aName);
    	offline.setAccountCategory(accountCategory);
    	offline.setIsLocal(local);
    	offline.setNumChecked(itemsChecked);
		return offline;
    }

    /**
     * Find offline with given parameter
     * @param aId
     * @return the offline
     */
    @Transactional
    public Offline getOffline(int aId) {
        
    	return (Offline) accountRepository.findAccountById(aId);
    }

    /**
     * Find a head librarian and change their info with given parameters
     * @param aId
     * @param aAddress
     * @param aName
     * @param itemsChecked
     */
    @Transactional
    public Offline updateOffline(int aId, String aAddress, String aName, int itemsChecked) {
    	Offline offline=(Offline) accountRepository.findAccountById(aId);
    	offline.setAddress(aAddress);
    	offline.setName(aName);
        offline.setNumChecked(itemsChecked);
		return offline;
    }

    /**
     * Delete head librarian corresponding to the given parameter
     * @param aId
     */
    @Transactional
    public Offline deleteOffline(int aId) {
    	Offline offline=(Offline) accountRepository.findAccountById(aId);
    	//mediaRepository.deleteAll(mediaRepository.findByAccount(offline));
    	//eventRepository.deleteAll(eventRepository.findByAccount(offline));
    	accountRepository.delete(offline);
		return offline;
    }

    @Transactional
	public List<Offline> getAllOfflines(){
		List<Account> allAccounts=(List<Account>) accountRepository.findAll();
		List<Offline> allOfflines=new ArrayList<>();
		for (Account a : allAccounts) {
			if (a instanceof Offline){
				allOfflines.add((Offline) a);
			}
		}
		return allOfflines;
	}

    @Transactional
    public Media checkoutAnItem(int mediaId, int id){
        Offline offline = (Offline) accountRepository.findAccountById(id);
        if(offline == null){
            throw new IllegalArgumentException("This account does not exist!");
        }
        if(offline.getNumChecked() >= 15){
            throw new IllegalArgumentException("This account has too many items checked out!");
        } else {
            if(mediaRepository.findMediaByID(mediaId) == null){
                throw new IllegalArgumentException("This media Id is non-existent!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof NonCheckOutItem){
                throw new IllegalArgumentException("This media Id corresponds to an item that you cannot check out!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof CheckOutItem){
                offline.getMedias().add(mediaRepository.findMediaByID(mediaId));
                offline.setNumChecked(offline.getNumChecked()+1);
                return mediaRepository.findMediaByID(mediaId);
            }
        }
        throw new IllegalArgumentException("System Error");
    }

    @Transactional
    public Media returnAnItem(int mediaId, int id){
        Offline offline = (Offline) accountRepository.findAccountById(id);
        if(offline == null){
            throw new IllegalArgumentException("This account does not exist!");
        }
        if(offline.getNumChecked() >= 15){
            throw new IllegalArgumentException("This account has too many items checked out!");
        } else {
            if(mediaRepository.findMediaByID(mediaId) == null){
                throw new IllegalArgumentException("This media Id is non-existent!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof NonCheckOutItem){
                throw new IllegalArgumentException("This media Id corresponds to an item that you cannot check out!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof CheckOutItem){
                if (offline.getMedias().contains(mediaRepository.findById(mediaId))){
                    offline.getMedias().remove(mediaRepository.findMediaByID(mediaId));
                    offline.setNumChecked(offline.getNumChecked()-1);
                } else {
                    throw new IllegalArgumentException("This user does not have the following item checked out!");
                }
                return mediaRepository.findMediaByID(mediaId);
            }
        }
        throw new IllegalArgumentException("System Error");
    }
}
