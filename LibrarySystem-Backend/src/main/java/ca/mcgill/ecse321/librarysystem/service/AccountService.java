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
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Offline;
import ca.mcgill.ecse321.librarysystem.models.Online;
//author David Hu
@Service
public class AccountService {

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
     * 
     * @param id
     * @param password
     * @return Account
     * @author David
     */
    @Transactional
    public Account login(int id, String password) {

        if(accountRepository.findAccountById(id) == null) {
            throw new IllegalArgumentException("Invalid Id");
        }

        if(accountRepository.findAccountById(id) instanceof Offline){
            return accountRepository.findAccountById(id);
        }

        if(accountRepository.findAccountById(id) instanceof Online){
            if(((Online) accountRepository.findAccountById(id)).getPassword().equals(password)){
                return accountRepository.findAccountById(id);
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        }

        //Detaching HL and L from account because a figure of authority told us to do it.

        throw new IllegalArgumentException("System Error");
    }

    /**
     * 
     * @param name
     * @param id
     * @return Account
     * @author David
     */

    @Transactional
    public Account bookEvent(String name, int id){
        Account account = accountRepository.findAccountById(id);
        if(accountRepository.findAccountById(id) == null) {
            throw new IllegalArgumentException("Invalid Id");
        }
        if(eventRepository.findEventByName(name) == null) {
            throw new IllegalArgumentException("Invalid Name");
        }
        Event event = eventRepository.findEventByName(name);
        if (account instanceof Offline){
            account = (Offline) accountRepository.findAccountById(id);
        }
        if (account instanceof Online){
            account = (Online) accountRepository.findAccountById(id);
        }
        account.getEvents().add(event);
        accountRepository.save(account);
        eventRepository.save(event);
        return account;
        //event.setAccount(accountRepository.findAccountById(id));
        //throw new IllegalArgumentException("System Error");
    }
    
}
