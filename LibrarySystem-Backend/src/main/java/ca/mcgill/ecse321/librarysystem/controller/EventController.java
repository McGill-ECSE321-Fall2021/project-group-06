package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

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
    @PostMapping(value = { "/create_event/{eventName}", "/create_event/{eventName}/" })
    public EventDto createEvent(@PathVariable("eventName") String eventName, @RequestParam String eventDate,
                                @RequestParam String eventStart,
                                @RequestParam String eventEnd)
            throws IllegalArgumentException {
        Date date = Date.valueOf(eventDate);
        Time start = Time.valueOf(eventStart);
        Time end = Time.valueOf(eventEnd);
        Event event = eventService.createEvent(eventName, date, start, end);
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
        return eventService.getAllEventsByName().stream().map(p -> Conversion.convertToDto(p)).collect(Collectors.toList());
    }

    /**
     * Updating Event object (date): Update Operation for HTTP request -> @PutMapping
     * @param eventName
     * @param eventDate
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @PutMapping (value = { "/update_events/{eventName}", "/update_events/{eventName}/" })
    public EventDto updateEvent(@PathVariable("eventName") String eventName, @RequestParam String eventDate, @RequestParam String eventStart, @RequestParam String eventEnd)
                throws IllegalArgumentException {
                    Date date = Date.valueOf(eventDate);
                    Time start = Time.valueOf(eventStart);
                    Time end = Time.valueOf(eventEnd);
                    Event event = eventService.updateEventDate(eventName, date);
                    event = eventService.updateEventTime(eventName, start, end);
                    return Conversion.convertToDto(event);
    }
    /**
     * Deleting Event object: Delete operation for HTTP Delete Request -> @DeleteMapping
     * @param eventName
     * @return event as DTO
     * @throws IllegalArgumentException
     * @author Samuel
     */
    @DeleteMapping (value = {"/update_events/{eventName}", "/update_events/{eventName}/"})
    public EventDto deleteEventByName(@PathVariable("eventName") String eventName) throws IllegalArgumentException {
        Event event = eventService.deleteEvent(eventName);
        return Conversion.convertToDto(event);
    }

    /**
     * Deleting all Event objects: Delete operation for HTTP Delete Request -> @DeleteMapping
     * @return list of Events as DTO
     * @author Samuel
     */
    @DeleteMapping (value = {"/delete_events/all", "/delete_events/all/"})
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