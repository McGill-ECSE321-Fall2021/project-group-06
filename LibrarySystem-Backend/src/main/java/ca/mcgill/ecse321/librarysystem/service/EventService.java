package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.EventRepository;
import ca.mcgill.ecse321.librarysystem.dao.OpeningHourRepository;
import ca.mcgill.ecse321.librarysystem.models.Event;

@Service
public class EventService {

    // @Autowired
    // private AccountRepository accountRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OpeningHourRepository openingHourRepository;

    /**
     * @param name
     * @param date
     * @param eventStart
     * @param eventEnd
     * @return event
     * @author Samuel
     */
    @Transactional
    public Event createEvent(String name, Date date, Time eventStart, Time eventEnd){

        // Input validation (methods from tutorial notes 2.6.1)
        String error ="";

        if (name == null || name.trim().length() == 0) {
            error = error + "Event name cannot be empty!";
        }
        if (date == null) {
            error = error + "Event date cannot be empty!";
        }
        if (eventStart == null) {
            error = error + "Event start time cannot be empty!";
        }
        if (eventEnd == null) {
            error = error + "Event end time cannot be empty!";
        }
        if (eventEnd != null && eventStart != null && eventEnd.before(eventStart)) {
            error = error + "Event end time cannot be before event start time!";
        }
        if (eventRepository.findEventByName(name) != null){
            error = error + "Event name already exists!";
        }
        // if (eventStart.isbefore(openingHourRepository.findOpeningHourByDay(date.toLocalDate().getDayOfWeek()))){
        //     error = error + "Event start time is before opening hour!";
        // }

        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        Event event = new Event();
        event.setName(name);        // Where the name is the event's "ID", as done in tutorial (see Event.java)
        event.setDate(date);
        event.setEventStart(eventStart);
        event.setEventEnd(eventEnd);
        eventRepository.save(event);
        return event;
    }

    /**
     * @param name
     * @return event
     * @author Samuel
     */
    @Transactional
    public Event getEvent(String name){
        
        // Input validation
        String error = "";
        if (name == null || name.trim().length() == 0) {
            error = error + "Event name cannot be empty!";
            throw new IllegalArgumentException(error);
        }

        Event event = eventRepository.findEventByName(name);
        return event;
    }

    /**
     * @param id (accountID)
     * @return eventList
     * @author Samuel
     * @throws Exception
     */
    // @Transactional
    // public List<Event> getEventsByAccountID(int id) {

    //     // Input validation
    //     String error = "";

    //     if (accountRepository.findAccountById(id) == null){
    //         error = error + "Account not found! ";
    //     }
    //     error = error.trim();

    //     if (error.length() > 0){
    //         throw new IllegalArgumentException(error);
    //     }

    //     Account account = accountRepository.findAccountById(id);
    //     List<Event> eventList = eventRepository.findByAccount(account);
    //     return eventList;
    // }

    /**
     * @return events (as a list)
     * @author Samuel
     */
    @Transactional
    public List<Event> getAllEventsByName(){

        Iterable<Event> events = eventRepository.findAll();
        return toList(events);
    }

    /**
     * @param date
     * @param eventStart
     * @param eventEnd
     * @return event
     * @author Samuel
     */
    @Transactional
    public Event updateEventDate(String name, Date date){

        // Input validation
        String error = "";

        if (eventRepository.findEventByName(name) == null || name.trim().length() == 0){
            error = error + "Event name not found";
        }
        if (date == null) {
            error = error + "Event date cannot be empty!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        Event event = eventRepository.findEventByName(name);
        event.setDate(date);
        eventRepository.save(event);
        return event;
    }

    /**
     * @param name
     * @param eventStart
     * @param eventEnd
     * @return event
     * @author Samuel
     */
    @Transactional
    public Event updateEventTime(String name, Time eventStart, Time eventEnd){

        // Input validation
        String error = "";

        if (eventRepository.findEventByName(name) == null || name.trim().length() == 0){
            error = error + "Event name not found";
        }
        if (eventStart == null) {
            error = error + "Event start time cannot be empty";
        }
        if (eventEnd == null){
            error = error + "Event end time cannot be empty";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        Event event = eventRepository.findEventByName(name);
        event.setEventStart(eventStart);
        event.setEventEnd(eventEnd);
        eventRepository.save(event);
        return event;
    }

    /**
     * *** Double check if return type is Event or void (and remove "return event" if possible) 
     * 
     * @param name (ID of event)
     * @author Samuel
     */
    @Transactional
    public Event deleteEvent(String name) {
        
        // Input validation
        String error = "";
        
        if (name == null || name.trim().length() == 0) {
            error = error + "Event name cannot be empty!";
            error = error.trim();
            throw new IllegalArgumentException(error);
        }
        
        else if (eventRepository.findEventByName(name) == null){
            error = error + "Event name not found";
            error = error.trim();
            throw new IllegalArgumentException(error);
        }

        else{
            Event event = eventRepository.findEventByName(name);
            eventRepository.delete(event);
            return event;
        }
    }

    /**
     * *** Same as deleteEvent: double check return type (void or List<Event>)
     * 
     * @param id
     * @author Samuel
     */
    // @Transactional
    // public List<Event> deleteAllEventsByAccountID(int id){

    //     // Input validation
    //     String error = "";

    //     if (accountRepository.findAccountById(id) == null){
    //         error = error + "Account ID not found";
    //         error = error.trim();
    //         throw new IllegalArgumentException(error);
    //     }

    //     Account account = accountRepository.findAccountById(id);
    //     List<Event> eventList = eventRepository.findByAccount(account);

    //     for (Event event : eventList){
    //         eventRepository.delete(event);
    //     }

    //     return eventList;
    // }

    /**
     * *** Ibidem
     * 
     * @return eventList 
     * @author Samuel
     */
    @Transactional
    public List<Event> deleteAllEvents(){

        Iterable<Event> eventList = eventRepository.findAll();
        eventRepository.deleteAll();
        return toList(eventList);
    }

    // Helper method that converts Iterables to Lists

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
