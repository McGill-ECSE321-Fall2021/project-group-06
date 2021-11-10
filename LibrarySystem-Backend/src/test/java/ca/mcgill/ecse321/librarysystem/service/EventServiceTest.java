package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
import ca.mcgill.ecse321.librarysystem.models.OpeningHour;

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

    @BeforeEach
    public void setMockOutput(){

    }

    /*
    When testing the edge cases write ur assertEquals as assertTrue like this:
        assertEquals("borrowingPeriod must be more that 0", error);
        assertTrue(error.contains("borrowingPeriod must be more that 0"));
    So that you can catch multiple error
    */

    @Test
    public void testCreateEvent(){

    }

    @Test
    public void testCreateEventNameTaken(){

    }

    @Test
    public void testCreateEventDateInHoliday(){

    }

    @Test
    public void testCreateEventTimeNotInOpeningHrs(){

    }
    
    @Test
    public void testUpdateEventDate(){

    }

    @Test
    public void testUpdateEventTime(){

    }

    @Test
    public void testDeleteEvent(){

    }

    @Test
    public void testDeleteAllEvents(){

    }

    @Test
    public void testGetEventByName(){

    }

    @Test
    public void testGetAllEvents(){

    }
}
