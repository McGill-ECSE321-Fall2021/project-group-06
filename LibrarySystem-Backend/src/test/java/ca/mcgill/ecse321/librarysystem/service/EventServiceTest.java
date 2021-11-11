package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
// import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.Module.Open;
import org.assertj.core.internal.bytebuddy.asm.Advice.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.models.Event;
import ca.mcgill.ecse321.librarysystem.models.Account;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;
import ca.mcgill.ecse321.librarysystem.models.Shift.DayOfWeek;
import net.minidev.asm.ConvertDate;
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    
    @Mock
    private AccountRepository accountDao;
    @Mock
    private EventRepository eventDao;
    @Mock 
    private OpeningHourRepository openingHourDao; 

    @InjectMocks
    private AccountService accountService;
    @InjectMocks
    private EventService eventService;
    @InjectMocks
    private OpeningHourService openingHourService;

    // Event constants (for setMockOutput() )
    private static final String STRING_DATE = "2021-12-05";
    private static final Date EVENT_DATE = Date.valueOf(STRING_DATE);
    private static final Time EVENT_STARTTIME = Time.valueOf(LocalTime.parse("13:00:00"));
    private static final Time EVENT_ENDTIME = Time.valueOf("15:00:00");
    private static final String EVENT_NAME = "Group Reading"; 

    // Account constants
    private static final int OFFLINE_ID = 240;
    private static final String OFFLINE_NAME = "Bob Stones";
    private static final AccountCategory OFFLINE_ACCOUNTCATEGORY = AccountCategory.Offline;

    // Opening Hours constants
    private static final int OH_ID = 6;
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.Friday;
    private static final Time OH_START_TIME = Time.valueOf("8:00:00");
    private static final Time OH_END_TIME = Time.valueOf("18:00:00");

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

        lenient().when(openingHourDao.findOpeningHourById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(OH_ID)) {

                OpeningHour openingHour = new OpeningHour();
                openingHour.setId(OH_ID);
                openingHour.setDayOfWeek(DAY_OF_WEEK);
                openingHour.setStartTime(OH_START_TIME);
                openingHour.setEndTime(OH_END_TIME);
                return openingHour;
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

    /*
    When testing the edge cases write ur assertEquals as assertTrue like this:
        assertEquals("borrowingPeriod must be more that 0", error);
        assertTrue(error.contains("borrowingPeriod must be more that 0"));
    So that you can catch multiple error
    */

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
    
    // @Test
    // public void testUpdateEventDate(){
        
    // }

    // @Test
    // public void testUpdateEventTime(){

    // }

    // @Test
    // public void testDeleteEvent(){

    // }

    // @Test
    // public void testDeleteAllEvents(){

    // }

    // @Test
    // public void testGetEventByName(){

    // }

    // @Test
    // public void testGetAllEvents(){

    // }
}
