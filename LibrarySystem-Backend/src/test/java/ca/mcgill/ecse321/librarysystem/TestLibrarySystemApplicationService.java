
package ca.mcgill.ecse321.librarysystem;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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
public class TestLibrarySystemApplicationService {

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
    
    @Test
	public void testGetLibrarians(){
		int id = 0;
		Librarian librarian = new Librarian();
		assertEquals(id, librarian.getId());
	}

	@Test
    public void testSetLibrarians(){
		int id =24601;
       Shift shift = new Shift();
       Librarian librarian = new Librarian();
       librarian.setShift(shift);
       /*
       shift.setShiftID(996);
       Librarian librarian = new Librarian();
       librarian.setShift(shift);
       librarian.setId(id);
       librarian.setName("John");
       accountRepository.save(librarian);
       
       librarian = null;
       
       librarian = accountRepository.save(librarian);*/
       Shift findShift = librarian.getShift();
 //      int ID = accountRepository.findAccountById(id).getId();
 //      assertEquals(id,ID);
       assertEquals(shift,findShift);
       
 //      assertNotNull(librarian);
  //     assertEquals(id, librarian.getId());
    }

    @Test
	public void testGetHeadLibrarians(){
		int id = 95279;
		HeadLibrarian headLibrarian = new HeadLibrarian();
		headLibrarian.setId(id);
		assertEquals(id, headLibrarian.getId());
	}

	@Test
    public void testSetHeadLibrarians(){
        int id = 9521;
        HeadLibrarian head = new HeadLibrarian();
        head.setId(id);
        Shift shift = new Shift();
        shift.setShiftID(89757);
        head.setShift(shift);
 //       accountRepository.save(head);
   //     HeadLibrarian head1 = (HeadLibrarian) accountRepository.findAccountById(id);
     //   int headID = head1.getId();
       // int shiftID = head1.getShift().getShiftID();
   //     assertEquals(id, headID);
     //   assertEquals(89757, shiftID);
        assertEquals(id, head.getId());
    }

    @Test
    public void testGetShifts(){
   		int id =0;
   		Shift shift = new Shift();
    	assertEquals(id, shift.getShiftID());
    }

    @Test
    public void testSetShifts(){
		int id = 9527;
		Shift shift = new Shift();
		shift.setShiftID(id);
		/*
		shift.setStartTime(java.sql.Time.valueOf(LocalTime.of(13, 00)));
		shift.setEndTime(java.sql.Time.valueOf(LocalTime.of(17, 55)));
		HeadLibrarian head = new HeadLibrarian();
		head.setId(id);
		Set<Librarian> librarians = new HashSet<Librarian>();
        Librarian lib = new Librarian();
        librarians.add(lib);
		shift.setHeadLibrarian(head);
		shift.setLibrarians(librarians);
		shift.setDate(java.sql.Date.valueOf(LocalDate.of(2021, Month.JANUARY, 3)));
		shiftRepository.save(shift);
		
		shift = null;
		
		shift = shiftRepository.findShiftById(id);*/
//		Date shiftDate = shiftRepository.findShiftById(id).getDate();
//		Time start = shiftRepository.findShiftById(id).getStartTime();
	//	Time end = shiftRepository.findShiftById(id).getEndTime();
	//	HeadLibrarian hl = shiftRepository.findShiftById(id).getHeadLibrarian();
//		Set<Librarian> libs = shiftRepository.findShiftById(id).getLibrarians();
		assertNotNull(shift);
		assertEquals(id,shift.getShiftID());
//		int shiftID =shift.getShiftID();
//        assertEquals(id,shiftID);
        
		assertEquals(id, shift.getShiftID());
    }

    @AfterEach
    public void clearDatabase(){
        accountRepository.deleteAll();
        eventRepository.deleteAll();
        mediaRepository.deleteAll();
        openingHourRepository.deleteAll();
        shiftRepository.deleteAll();
    }
}

