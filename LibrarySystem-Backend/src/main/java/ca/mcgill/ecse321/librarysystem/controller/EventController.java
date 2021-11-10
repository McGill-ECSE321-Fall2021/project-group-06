package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.librarysystem.service.EventService;
import ca.mcgill.ecse321.librarysystem.dto.EventDto;
import ca.mcgill.ecse321.librarysystem.models.Event;

@CrossOrigin(origins = "*")
@RestController
public class EventController {

	@Autowired
	private EventService eventService;

    /**
     * Creating an Event: Add operation for HTTP request -> @PostMapping
     * @param eventName
     * @param eventDate
     * @param eventStart
     * @param eventEnd
     * @return event converted to Dto
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @PostMapping (value = { "/events/{eventName}", "/events/{eventName}/" })
    public EventDto createEvent(@PathVariable("eventName") String eventName, @RequestParam Date eventDate,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime eventStart,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime eventEnd)
            throws IllegalArgumentException {
        Event event = eventService.createEvent(eventName, eventDate, Time.valueOf(eventStart), Time.valueOf(eventEnd));
        return Conversion.convertToDto(event);
    }

    /**
     * Getting an Event object: Get data for HTTP Get requests -> @GetMapping
     * @param eventName
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @GetMapping(value = { "/events/{eventName}", "/events/{eventName}/" })
    public EventDto getEventByName(@PathVariable("eventName") String eventName) throws IllegalArgumentException {
        return Conversion.convertToDto(eventService.getEventByName(eventName));
    }

    /**
     * Getting all Event objects: Get data for HTTP Get requests -> @GetMapping
     * @return list of Events as DTOs
     * @author Samuel
     */
    @GetMapping (value = { "/events", "/events/" })
    public List<EventDto> getAllEvents() {
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event : eventService.getAllEventsByName()) {
            eventDtos.add(Conversion.convertToDto(event));
        }
        return eventDtos;
    }

    /**
     * Updating Event object (date): Update Operation for HTTP request -> @PutMapping
     * @param eventName
     * @param eventDate
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @PutMapping (value = { "/events/{eventName}", "/events/{eventName}/" })
    public EventDto updateEventDate(@PathVariable("eventName") String eventName, @RequestParam Date eventDate) 
                throws IllegalArgumentException {
        Event event = eventService.updateEventDate(eventName, eventDate);
        return Conversion.convertToDto(event);
    }

    /**
     * Updating Event object (start and end time): Update Operation for HTTP request -> @PutMapping
     * @param eventName
     * @param eventStart
     * @param eventEnd
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @PutMapping (value = { "/events/{eventName}", "/events/{eventName}/" })
    public EventDto updateEventTime(@PathVariable("eventName") String eventName, 
            @RequestParam Time eventStart, @RequestParam Time eventEnd) throws IllegalArgumentException {

        Event event = eventService.updateEventTime(eventName, eventStart, eventEnd);
        return Conversion.convertToDto(event);
    }

    /**
     * Deleting Event object: Delete operation for HTTP Delete Request -> @DeleteMapping
     * @param eventName
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @DeleteMapping (value = {"/events/{eventName}", "/events/{eventName}/"})
    public EventDto deleteEventByName(@PathVariable("eventName") String eventName) throws IllegalArgumentException {
        Event event = eventService.deleteEventByName(eventName);
        return Conversion.convertToDto(event);
    }

    /**
     * Deleting all Event objects: Delete operation for HTTP Delete Request -> @DeleteMapping
     * @return list of Events as DTO
     * @author Samuel
     */
    @DeleteMapping (value = {"/events/{eventName}", "/events/{eventName}/"})
    public List<EventDto> deleteAllEvents() {
        List<Event> eventList = eventService.deleteAllEvents();
        return Conversion.convertToDto(eventList);
    } 


    // @GetMapping(value = { "/events/{account}", "events/{account}/" })
    // public List<EventDto> getEventsByAccount(@PathVariable("account") AccountDto accountDto) {
        
    //     Account account = convertToDomainObject(accountDto);
    //     List<Event> eventsByAccount = eventService.getEventsByAccountID(account.getId());
    //     List<EventDto> events = new ArrayList<>();

    //     for (Event event: eventsByAccount){
    //         events.add(Conversion.convertToDto(event));
    //     }

    //     return events;
    // }

    // private Account convertToDomainObject(AccountDto accountDto){

    //     List<Offline> allOffline = offlineService.getAllOfflines();
    //     List<Online> allOnline = onlineService.getAllOnlines();
    //     boolean hasAccount = false;
    
    //     for (Offline offline: allOffline){
    //         if (offline.getId() == accountDto.getId()){
    //             hasAccount = true;
    //             return (Account)offline;
    //         }
    //     }

    //     if (!hasAccount){
    //         for (Online online: allOnline){
    //             if (online.getId() == accountDto.getId()){
    //                 hasAccount = true;
    //                 return (Account)online;
    //             }
    //         }
    //     }

    //     if (!hasAccount){
    //         return null;
    //     }
    // }
}