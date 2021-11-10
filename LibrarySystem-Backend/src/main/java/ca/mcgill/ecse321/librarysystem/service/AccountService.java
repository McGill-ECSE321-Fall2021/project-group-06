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

import java.sql.Date;
import java.sql.Time;

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

    @Transactional
    public Event bookEvent(String name, int id, Date date, Time start, Time end){
        if(accountRepository.findAccountById(id) == null) {
            throw new IllegalArgumentException("Invalid Id");
        }
        Event event = new Event();
        event.setDate(date);
        event.setEventEnd(start);
        event.setEventStart(end);
        event.setName(name);
        accountRepository.findAccountById(id).getEvents().add(event);
        //event.setAccount(accountRepository.findAccountById(id));
        throw new IllegalArgumentException("System Error");
    }
}
