package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

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
    @PostMapping (value = { "/events/{eventName}/createEvent", "/events/{eventName}/createEvent/" })
    public EventDto createEvent(@PathVariable("eventName") String eventName, @RequestParam String eventDateString,
                                @RequestParam String eventStartString, @RequestParam String eventEndString) throws IllegalArgumentException {
        
        Date eventDate = Date.valueOf(eventDateString);
        LocalTime eventStart = LocalTime.parse(eventStartString, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime eventEnd = LocalTime.parse(eventEndString, DateTimeFormatter.ofPattern("HH:mm"));
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
    @GetMapping(value = { "/events/{eventName}/getEvent", "/events/{eventName}/getEvent/" })
    public EventDto getEventByName(@PathVariable("eventName") String eventName) throws IllegalArgumentException {
        return Conversion.convertToDto(eventService.getEvent(eventName));
    }

    /**
     * Getting all Event objects: Get data for HTTP Get requests -> @GetMapping
     * @return list of Events as DTOs
     * @author Samuel
     */
    @GetMapping (value = { "/events/getAllEvents", "/events/getAllEvents/" })
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
    @PutMapping (value = { "/events/{eventName}/updateDate", "/events/{eventName}/updateDate/" })
    public EventDto updateEventDate(@PathVariable("eventName") String eventName, @RequestParam String eventDateString) 
                throws IllegalArgumentException {
        
        Date eventDate = Date.valueOf(eventDateString);
        Event event = eventService.updateEventDate(eventName, eventDate);
        return Conversion.convertToDto(event);
    }

    /**
     * Updating Event object (start and end time): Update Operation for HTTP request -> @PutMapping
     * (Using @PathVariable since @RequestParam for eventStart and eventEnd didn't work for some reason even though implementation
     * with createEvent is basically the same...)
     * @param eventName
     * @param eventStart
     * @param eventEnd
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @PutMapping (value = { "/events/{eventName}/updateTime", "/events/{eventName}/updateTime/" })
    public EventDto updateEventTime(@PathVariable("eventName") String eventName, 
            @RequestParam String eventStartString, @RequestParam String eventEndString)
            throws IllegalArgumentException {
        
        LocalTime eventStart = LocalTime.parse(eventStartString, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime eventEnd = LocalTime.parse(eventEndString, DateTimeFormatter.ofPattern("HH:mm"));
        Event event = eventService.updateEventTime(eventName, Time.valueOf(eventStart), Time.valueOf(eventEnd));
        return Conversion.convertToDto(event);
    }

    /**
     * Deleting Event object: Delete operation for HTTP Delete Request -> @DeleteMapping
     * @param eventName
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @DeleteMapping (value = {"/events/{eventName}/delete", "/events/{eventName}/delete/"})
    public EventDto deleteEventByName(@PathVariable("eventName") String eventName) throws IllegalArgumentException {
        Event event = eventService.deleteEvent(eventName);
        return Conversion.convertToDto(event);
    }

    /**
     * Deleting all Event objects: Delete operation for HTTP Delete Request -> @DeleteMapping
     * @return list of Events as DTO
     * @author Samuel
     */
    @DeleteMapping (value = {"/events/deleteAll", "/events/deleteAll/"})
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