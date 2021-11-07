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

import ca.mcgill.ecse321.librarysystem.service.AccountService;
import ca.mcgill.ecse321.librarysystem.service.EventService;
import ca.mcgill.ecse321.librarysystem.service.OpeningHourService;
import ca.mcgill.ecse321.librarysystem.dto.EventDto;
import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.dto.AccountDto;

@CrossOrigin(origins = "*")
@RestController
public class EventController {

	@Autowired
	private EventService eventService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OpeningHourService openingHourService;

    @PostMapping (value = { "/events/{eventName}", "/events/{eventName}/" })
    public EventDto createEvent(@PathVariable("eventName") String eventName, @RequestParam Date eventDate,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime eventStart,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime eventEnd)
            throws IllegalArgumentException {
        Event event = eventService.createEvent(eventName, eventDate, Time.valueOf(eventStart), Time.valueOf(eventEnd));
        return convertToDto(event);
    }

    @GetMapping(value = { "/events/{eventName}", "/events/{eventName}/" })
    public EventDto getEventByName(@PathVariable("eventName") String eventName) throws IllegalArgumentException {
        return convertToDto(eventService.getEventByName(eventName));
    }

    @GetMapping(value = { "/getEventsByAccount/{account}", "/getEventsByAccount/{account}/" })
    public List<EventDto> getEventsByAccount(@PathVariable("account") AccountDto accountDto) {
        
        Account account = accountService.getAccount();
        List<Event> eventsByAccount = eventService.getEventsByAccountID(account.getId());
        List<EventDto> events = new ArrayList<>();

        for (Event event: eventsByAccount){
            events.add(convertToDto(event));
        }

        return events;
        
        // Account account = convertToDomainObject(accountDto);
        // return createEventDtosForAccount(account);
    }

    @GetMapping (value = { "/events", "/events/" })
    public List<EventDto> getAllEvents() {
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event : eventService.getAllEventsByName()) {
            eventDtos.add(convertToDto(event));
        }
        return eventDtos;
    }


    // DTO convert methods
    private EventDto convertToDto(Event e) {
        if (e == null) {
            throw new IllegalArgumentException("Event not found");
        }
        EventDto eventDto = new EventDto(e.getName(),e.getDate(),e.getEventStart(),e.getEventEnd());
        return eventDto;
    }

    private AccountDto convertToDto(AccountDto acc) {
        if (acc == null) {
            throw new IllegalArgumentException("No such account available");
        }
        AccountDto accountDto = new AccountDto(acc.getId(), acc.getAccountCategory());
        return accountDto;
    }
}