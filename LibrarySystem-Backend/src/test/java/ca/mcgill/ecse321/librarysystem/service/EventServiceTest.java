package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    
    @Mock
    private EventRepository eventDao;

    @InjectMocks
    private EventService eventService;

    // Event constants (for setMockOutput() )
    private static final String STRING_DATE = "2021-12-05";
    private static final Date EVENT_DATE = Date.valueOf(STRING_DATE);
    private static final Time EVENT_STARTTIME = Time.valueOf(LocalTime.parse("13:00:00"));
    private static final Time EVENT_ENDTIME = Time.valueOf("15:00:00");
    private static final String EVENT_NAME = "Group Reading"; 

    @BeforeEach
    public void setMockOutput(){

        lenient().when(eventDao.findEventByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EVENT_NAME)) {

                Event event = new Event();
                event.setDate(EVENT_DATE);
                event.setEventEnd(EVENT_ENDTIME);
                event.setEventStart(EVENT_STARTTIME);
                event.setName(EVENT_NAME);

                return event;
            } 
            
            else {
                return null;
            }
        });

        // Return parameter object when any event object is saved
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateEvent(){
        assertEquals(0, eventService.getAllEventsByName().size());

        String eventName = "Gardening Tutorial";

        LocalDate localDate = LocalDate.of(2021, Month.DECEMBER, 14);
        Date eventDate = Date.valueOf(localDate);

        String eventStartString = "12:00:00";
        String eventEndString = "15:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            // Use fail() since an event object couldn't even be created
            fail("Event could not be created: event is null");
        }

        // Check result of Event
        assertNotNull(event);
        assertEquals(eventName, event.getName());
        assertEquals(eventDate, event.getDate());
        assertEquals(eventStart, event.getEventStart());
        assertEquals(eventEnd, event.getEventEnd());
    }

    @Test
    public void testCreateEventNameTaken(){

        String eventName = "Group Reading";     // Same eventName strings as the one in the mock Repo: everything else different

        LocalDate localDate = LocalDate.of(2021, Month.DECEMBER, 15);   // Date is changed from previous test
        Date eventDate = Date.valueOf(localDate);

        String eventStartString = "10:00:00";                              // Event times are also changed
        String eventEndString = "11:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event name already exists!");
    }

    @Test
    public void testCreateEventEndTimeBeforeStartTime(){

        String eventName = "Programming tutorial";

        LocalDate localDate = LocalDate.of(2021, Month.NOVEMBER, 20); 
        Date eventDate = Date.valueOf(localDate);

        String eventStartString = "13:00:00";                              // EventStart is after EventEnd
        String eventEndString = "12:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event end time cannot be before event start time!");
    }

    @Test
    public void testCreateEventDateEmpty(){

        String eventName = "Programming tutorial";

        Date eventDate = null;

        String eventStartString = "14:00:00";                           
        String eventEndString = "15:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event date cannot be empty!");
    }

    @Test
    public void testCreateEventEmpty(){
        String eventName = " "; // Empty string or spaces will trigger the error still (because of .trim())

        LocalDate localDate = LocalDate.of(2021, Month.NOVEMBER, 24); 
        Date eventDate = Date.valueOf(localDate);

        String eventStartString = "11:00:00";                       
        String eventEndString = "13:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event name cannot be empty!");
    }

    @Test
    public void testCreateEventStartTimeEmpty(){

        String eventName = "Programming tutorial";

        LocalDate localDate = LocalDate.of(2021, Month.NOVEMBER, 20); 
        Date eventDate = Date.valueOf(localDate);
                             
        String eventEndString = "12:00:00";
        Time eventStart = null;
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event start time cannot be empty!");
    }

    @Test
    public void testCreateEventEndTimeEmpty(){

        String eventName = "Programming tutorial";

        LocalDate localDate = LocalDate.of(2021, Month.NOVEMBER, 20); 
        Date eventDate = Date.valueOf(localDate);
                             
        String eventStartString = "12:00:00";
        Time eventEnd = null;
        Time eventStart = Time.valueOf(eventStartString);

        Event event = null;
        String error = "";

        try{
            event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event end time cannot be empty!");
    }

    // @Test
    // public void testCreateEventTimeNotInOpeningHrs(){
    //     String eventName = "Boots And Cats";

    //     LocalDate localDate = LocalDate.of(2021, Month.NOVEMBER, 30); 
    //     Date eventDate = Date.valueOf(localDate);

    //     String eventStartString = "2:00";                       
    //     String eventEndString = "13:00";
    //     Time eventStart = Time.valueOf(eventStartString);
    //     Time eventEnd = Time.valueOf(eventEndString);

    //     // Add Opening Hours here
    //     int id = OH_ID;
    //     OpeningHour openingHour = openingHourDao.findOpeningHourById(OH_ID);

    //     // int id = 13;
    //     // DayOfWeek dayOfWeek = DayOfWeek.Monday;
    //     // String openHourString = "8:00";
    //     // String closeHourString = "18:00";
    //     // Time openHour = Time.valueOf(openHourString);
    //     // Time closeHour = Time.valueOf(closeHourString);
    //     // OpeningHour openingHour = openingHourService.createOpeningHour(id, dayOfWeek, openHour, closeHour);

    //     Event event = null;
    //     String error = "";

    //     try{
    //         event = eventService.createEvent(eventName, eventDate, eventStart, eventEnd);
    //         // ... now what (openingHour wtf does it do)
    //     }
    //     catch (IllegalArgumentException e){
    //         error = e.getMessage();
    //     }

    //     // Check result of Event
    //     assertNull(event);
    //     assertEquals(error, "Event start time is before opening hour!");        
    // }
    
    @Test
    public void testUpdateEventDate(){

        String eventName = EVENT_NAME;
        LocalDate localDate = LocalDate.of(2021, Month.APRIL, 1); 
        Date eventDate = Date.valueOf(localDate);
        Time eventStart = EVENT_STARTTIME;
        Time eventEnd = EVENT_ENDTIME;

        Event event = null;

        try{
            event = eventService.updateEventDate(eventName, eventDate);
        }
        catch (IllegalArgumentException e){
            fail("Event date could not be updated");
        }

        // Check result of Event
        assertNotNull(event);
        assertEquals(eventName, event.getName());
        assertEquals(eventDate, event.getDate());
        assertEquals(eventStart, event.getEventStart());
        assertEquals(eventEnd, event.getEventEnd());
    }

    @Test
    public void testUpdateEventDateEmpty(){

        String eventName = EVENT_NAME; 
        Date eventDate = null;

        Event event = null;
        String error = "";

        try{
            event = eventService.updateEventDate(eventName, eventDate);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event date cannot be empty!");
    }

    @Test
    public void testUpdateEventDateNameEmpty(){

        String eventName = "";
        LocalDate localDate = LocalDate.of(2021, Month.APRIL, 1); 
        Date eventDate = Date.valueOf(localDate);

        Event event = null;
        String error = "";

        try{
            event = eventService.updateEventDate(eventName, eventDate);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event name not found");
    }

    @Test
    public void testUpdateEventTime(){
        String eventName = EVENT_NAME;
        Date eventDate = EVENT_DATE;
        String eventStartString = "9:00:00";                              // Event times are also changed
        String eventEndString = "10:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;

        try{
            event = eventService.updateEventTime(eventName, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            fail("Event startTime and endTime couldn't be updated");
        }

        // Check result of Event
        assertNotNull(event);
        assertEquals(eventName, event.getName());
        assertEquals(eventDate, event.getDate());
        assertEquals(eventStart, event.getEventStart());
        assertEquals(eventEnd, event.getEventEnd());
    }

    @Test
    public void testUpdateEventTimeNameEmpty(){
        String eventName = "";
        String eventStartString = "9:00:00";                              // Event times are also changed
        String eventEndString = "10:00:00";
        Time eventStart = Time.valueOf(eventStartString);
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.updateEventTime(eventName, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event name not found");
    }

    @Test
    public void testUpdateEventTimeStartTimeEmpty(){
        String eventName = EVENT_NAME;
        String eventEndString = "10:00:00";
        Time eventStart = null;
        Time eventEnd = Time.valueOf(eventEndString);

        Event event = null;
        String error = "";

        try{
            event = eventService.updateEventTime(eventName, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event start time cannot be empty");
    }

    @Test
    public void testUpdateEventTimeEndTimeEmpty(){
        String eventName = EVENT_NAME;
        String eventStartString = "9:00:00";
        Time eventEnd = null;
        Time eventStart = Time.valueOf(eventStartString);

        Event event = null;
        String error = "";

        try{
            event = eventService.updateEventTime(eventName, eventStart, eventEnd);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }

        // Check result of Event
        assertNull(event);
        assertEquals(error, "Event end time cannot be empty");
    }

    @Test
    public void testDeleteEvent(){
        
        String eventName = EVENT_NAME;
        boolean isDeleted = true;

        try{
            eventService.deleteEvent(eventName);
        }
        catch (IllegalArgumentException e){
            fail("Event could not be deleted");
        }
        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteEventNameEmpty(){
        
        String eventName = "";
        String error = "";

        try{
            eventService.deleteEvent(eventName);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Event name cannot be empty!");
    }

    @Test
    public void testDeleteEventNameNotFound(){
        
        String eventName = "Random Event";
        String error = "";

        try{
            eventService.deleteEvent(eventName);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Event name not found");
    }


    @Test
    public void testDeleteAllEvents(){

        boolean areDeleted = true;

        try{
            eventService.deleteAllEvents(); // Both of the 2 events
        }
        catch (IllegalArgumentException e){
            fail("Not all events could be deleted");
        }
        assertTrue(areDeleted);
    }

    @Test
    public void testGetEventByName(){
        
        String eventName = EVENT_NAME;
        Event event = null;

        try{
            event = eventService.getEvent(eventName);
        }
        catch (IllegalArgumentException e){
            fail("Event could not be found");
        }
        assertNotNull(event);
        assertEquals(EVENT_NAME, event.getName());
        assertEquals(EVENT_DATE, event.getDate());
        assertEquals(EVENT_STARTTIME, event.getEventStart());
        assertEquals(EVENT_ENDTIME, event.getEventEnd());
    }

    @Test
    public void testGetEventByNameEmpty(){

        String eventName = "";
        Event event = null;
        String error = "";

        try{
            event = eventService.getEvent(eventName);
        }
        catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(event);
        assertEquals(error, "Event name cannot be empty!");
    }

    @Test
    public void testGetAllEvents(){

        List<Event> eventList = new ArrayList<>();

        try{
            for (Event event : eventService.getAllEventsByName()){
                eventList.add(event);
            }
        }
        catch (IllegalArgumentException e){
            fail("Not all events could be retrieved");
        }
        assertNotNull(eventList);
    }
}
