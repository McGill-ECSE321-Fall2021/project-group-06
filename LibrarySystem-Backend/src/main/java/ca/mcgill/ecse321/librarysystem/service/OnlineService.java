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
import ca.mcgill.ecse321.librarysystem.models.Online;

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
        if (accountRepository.findAccountById(aId) != null) {
            throw new IllegalArgumentException("Online Account id already exists.");
        }
    	if (aAddress==null || aAddress.length()==0) {
    		throw new IllegalArgumentException("Online Account must have an address.");
    	}
    	if (aName==null || aName.length() == 0) {
    		throw new IllegalArgumentException("Online Account must have a name.");
    	}
    	if (accountCategory==AccountCategory.Offline || accountCategory == null) {
    		throw new IllegalArgumentException("Online account must be of type online.");
    	}
    	if (local==false) {
    		throw new IllegalArgumentException("Online Account must be a local");
    	}
        if (username==null || username.length()==0){
            throw new IllegalArgumentException("Online Account must have a username");
        }
        if (password==null || password.length()==0){
            throw new IllegalArgumentException("Online Account must have a password");
        }
        if (email==null || email.length()==0){
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
        accountRepository.save(online);
		return online;
    }

    /**
     * Find offline with given parameter
     * @param onlineUsername
     * @return the offline
     */
    @Transactional
    public Online getOnline(int id) {
        if (accountRepository.findAccountById(id) == null) {
            throw new IllegalArgumentException("Offline Account id does not exist.");
        }
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
    public Online updateOnline(int aId, String aAddress, String aName, String password) {
        //usernameIsValid(username);
        if (accountRepository.findAccountById(aId) == null) {
            throw new IllegalArgumentException("Offline Account id does not exist.");
        }
        if (aAddress.length()==0 || aAddress == null){
            throw new IllegalArgumentException("address cannot be empty");
        }
        if(aName.length()==0 || aName == null){
            throw new IllegalArgumentException("name cannot be empty");
        }
        // if(itemsChecked < 0){
        //     throw new IllegalArgumentException("items checked cannot be less than 0");
        // }
        // if(!usernameIsValid(username) || username.length()==0){
        //     throw new IllegalArgumentException("username is not valid");
        // }
        if(password.length() == 0 || password == null){
            throw new IllegalArgumentException("must have a password");
        }
        // if(email.length() == 0 || password == null){
        //     throw new IllegalArgumentException("email must not be empty");
        // }
        Online online=(Online) accountRepository.findAccountById(aId);
    	online.setAddress(aAddress);
    	online.setName(aName);
        //online.setNumChecked(itemsChecked);
        //online.setUsername(username);
        online.setPassword(password);
        //online.setEmail(email);
        
        accountRepository.save(online);
		return online;
    }

    /**
     * Delete head librarian corresponding to the given parameter
     * @param aId
     */
    @Transactional
    public Online deleteOnline(int aId) {
        if(accountRepository.findAccountById(aId) == null){
            throw new IllegalArgumentException("account does not exist");
        }
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
    public Online checkoutAnItem(int mediaId, int id){
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
            } else if (((CheckOutItem)mediaRepository.findMediaByID(mediaId)).getIsCheckedOut()){
                throw new IllegalArgumentException("This media is already checked out!");
            } else if (mediaRepository.findMediaByID(mediaId) instanceof CheckOutItem){
                online.getMedias().add(mediaRepository.findMediaByID(mediaId));
                online.setNumChecked(online.getNumChecked()+1);

                return online;
            }
        }
        throw new IllegalArgumentException("System Error");
    }

    @Transactional
    public Online returnAnItem(int mediaId, int id){
        Online online = (Online) accountRepository.findAccountById(id);
        Media mediaTest = new CheckOutItem();
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
            }  else if (mediaRepository.findMediaByID(mediaId) instanceof CheckOutItem){
                mediaTest=null;
                for (Media media : online.getMedias()){
                    if (media.getID()==mediaId){
                        mediaTest = media;
                    }
                }
                if (mediaTest!=null){
                    online.getMedias().remove(mediaTest);
                    online.setNumChecked(online.getNumChecked()-1);
                    //offline.setNumChecked(id);
                    accountRepository.save(online);
                    mediaRepository.save(mediaRepository.findMediaByID(mediaId));
                    return online;
                } else {
                    throw new IllegalArgumentException("This user does not have the following item checked out!");
                }
            }
        }
        throw new IllegalArgumentException("System Error");
    }

    @Transactional
    public Media reserveAnItem(int mediaId){
        if(mediaRepository.findMediaByID(mediaId) == null){
            throw new IllegalArgumentException("This media Id is non-existent!");
        }
        if (mediaRepository.findMediaByID(mediaId) instanceof NonCheckOutItem){
            throw new IllegalArgumentException("This media Id corresponds to an item that you cannot reserve!");
        }
        CheckOutItem mediaTest = (CheckOutItem) mediaRepository.findMediaByID(mediaId);
        if(mediaTest.getIsReserved()){
            throw new IllegalArgumentException("This media is already reserved!");
        }
        mediaTest.setIsReserved(true);
        return mediaTest;
    }

    @Transactional
    public Media checkAnItem(int mediaId){
        if(mediaRepository.findMediaByID(mediaId) == null){
            throw new IllegalArgumentException("This media Id is non-existent!");
        }
        CheckOutItem mediaTest = (CheckOutItem) mediaRepository.findMediaByID(mediaId);
        if(mediaTest.getIsCheckedOut()){
            throw new IllegalArgumentException("This media is already CheckedOut!");
        }
        mediaTest.setIsCheckedOut(true);
        return mediaTest;
    }


    //helper methods

    private boolean usernameIsValid(String username) {
		if(accountRepository.findOnlineByUsername(username)!=null) throw new IllegalArgumentException("Username is already taken");
        return true;
	}

}
