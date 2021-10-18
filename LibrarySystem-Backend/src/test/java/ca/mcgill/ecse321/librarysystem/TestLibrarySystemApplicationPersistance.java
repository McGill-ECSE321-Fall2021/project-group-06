package ca.mcgill.ecse321.librarysystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.*;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.dao.AccountRepository;
import ca.mcgill.ecse321.librarysystem.models.*;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;



@ExtendWith(MockitoExtension.class)
public class TestLibrarySystemApplicationPersistance {

    @Mock AccountRepository accountRepository;
    @Mock EventRepository eventRepository;
    @Mock MediaRepository mediaRepository;
    @Mock OpeningHourRepository openingHourRepository;
    @Mock ShiftRepository shiftRepository;

    // @InjectMocks
    // private Account account;
    @InjectMocks
    private Offline offline;
    @InjectMocks
    private Online online;
    //Testing for Account
    private static final int ID_KEY = 1;
    private static final int NONEXISTING_KEY = 0;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(accountRepository.findAccountById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Account offline = new Offline();
				offline.setId(ID_KEY);
				return offline;
			} else {
				return null;
			}
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountRepository.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(eventRepository.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(mediaRepository.save(any(Media.class))).thenAnswer(returnParameterAsAnswer);
	}

    @Test
    public void testSetId(){
        int id = 10;
        offline.setId(id);
        int setId = offline.getId();
        assertEquals(id, setId);
    }

    @Test
    public void testGetId(){
        int id = 0; //Default id is 0;
        assertEquals(id, offline.getId());
    }

    @Test
    public void testSetAddress(){
        String address = "TestAddress";
        offline.setAddress(address);
        assertEquals(address, offline.getAddress());
    }

    @Test
    public void testGetAddress(){
        String address = null;
        assertEquals(address, offline.getAddress());
    }

    @Test
    public void testSetName(){
        String name = "BTS";
        offline.setName(name);
        assertEquals(name, offline.getName());
    }
    
    @Test
    public void testGetName(){
        String name = null;
        assertEquals(name, offline.getName());
    }

    @Test
    public void testSetAccountCategoryOffline(){
        offline.setAccountCategory(Account.AccountCategory.Online); //Setting to the Opposite choice to test wether it sets properly
        assertEquals(Account.AccountCategory.Online, offline.getAccountCategory());
    }

    @Test
    public void testGetAccountCategoryOffline(){
        assertEquals(Account.AccountCategory.Offline, offline.getAccountCategory());
    } 

    @Test
    public void testGetAccountCategoryOnfline(){
        assertEquals(Account.AccountCategory.Online, online.getAccountCategory());
    }

    @Test
    public void testSetIsLocal(){
        Boolean local = true;
        offline.setIsLocal(local);
        assertEquals(local, offline.getIsLocal());
    }

    @Test
    public void testGetIsLocal(){
        Boolean local = false; //Empty is set to false
        assertEquals(local, offline.getIsLocal());
    }

    @Test
    public void testGetCheckedOutItem(){
        assertEquals(null, offline.getCheckedOutItem());
    }

    @Test
    public void testGetNumChecked(){
        assertEquals(0, offline.getNumChecked());
    }

    @Test
    public void testSetNumChecked(){
        int n = 7;
        offline.setNumChecked(n);
        assertEquals(n, offline.getNumChecked());
    }

    @Test
    public void testGetEvents(){
        assertEquals(null, offline.getEvents());
    }

    @Test
    public void testSetEvents(){
        Set<Event> events = new HashSet<Event>();
        Event e = new Event();
        events.add(e);
        offline.setEvents(events);
        assertEquals(events, offline.getEvents());
    }

    @Test
    public void testGetMedias(){
        assertEquals(null, offline.getMedias());
    }

    @Test
    public void testSetMedias(){
        Set<Media> medias = new HashSet<Media>();
        Media m = new NonCheckOutItem();
        medias.add(m);
        offline.setMedias(medias);
        assertEquals(medias, offline.getMedias());
    }

    @AfterEach
    public void clearDatabse(){
        accountRepository.deleteAll();
        eventRepository.deleteAll();
        mediaRepository.deleteAll();
        openingHourRepository.deleteAll();
        shiftRepository.deleteAll();
    }
}
