package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.models.*;
import ca.mcgill.ecse321.librarysystem.models.Account.AccountCategory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarySystemPersistence {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private MediaRepository mediaRepository;
	@Autowired
	private OpeningHourRepository openingHourRepository;
	@Autowired
	private ShiftRepository shiftRepository;
	
	@AfterEach
	public void clearDatabase() {
		// Fisrt, we clear registrations to avoid exceptions due to inconsistencies
		
		
		
//		// Then we can clear the other tables
		accountRepository.deleteAll();
		shiftRepository.deleteAll();
		mediaRepository.deleteAll();
		openingHourRepository.deleteAll();
		eventRepository.deleteAll();
		
	}
	
	@Test
	public void testPersistAndLoadOffline() {
		Account acc = new Offline();
		String address = "ezstt";
		AccountCategory off = Account.AccountCategory.Offline;
		int id = 727;
		boolean local = true;
		String name = "BTS";
		int numChecked = 747;

		acc.setAccountCategory(off);
		acc.setAddress(address);
		acc.setId(id);
		acc.setIsLocal(local);
		acc.setName(name);
		acc.setNumChecked(numChecked);

		// Set<Event> events = new HashSet<Event>();
		// Event e = new Event();
		// events.add(e);

		// Media media = new CheckOutItem();
		// media.setType(Media.Item.Book);
		// int mediaId = 7;
		// media.setID(mediaId);
		// media.setAccount(acc);
		// mediaRepository.save(media);

		// Set<Media> medias = new HashSet<Media>();
       	// Media m = new NonCheckOutItem();
       	// medias.add(media);
		
		// acc.setEvents(events);
		// acc.setMedias(medias);


		accountRepository.save(acc);
		acc = null;
		acc = accountRepository.findAccountById(id);

		assertNotNull(acc);
		assertEquals(off, acc.getAccountCategory());
		assertEquals(address, acc.getAddress());
		assertEquals(id, acc.getId());
		assertEquals(local, acc.getIsLocal());
		assertEquals(name, acc.getName());
		assertEquals(numChecked, acc.getNumChecked());
		// assertEquals(events, acc.getEvents());
		// assertEquals(medias, acc.getMedias());
		
	}
	
	@Test
	public void testPersistAndLoadOnline() {
		String username = "group6";
		String password = "stupidProject";
		String email = "group6@mail.mcgill.ca";
		int id=6;
		
		Online online=new Online();
		online.setUsername(username);
		online.setPassword(password);
		online.setEmail(email);
		online.setId(id);
		
		accountRepository.save(online);
		online=null;
		online=(Online) accountRepository.findAccountById(id);
		
		assertNotNull(online);
		assertEquals(username, online.getUsername());
		assertEquals(password, online.getPassword());
		assertEquals(email, online.getEmail());
		assertEquals(id, online.getId());
	}
	
	@Test
	public void testPersistAndLoadCheckOutItem() {
		
		// Set up test account to perform operations on CheckOutItem / Media
		Account acc = new Online();
		acc.setId(17);
		accountRepository.save(acc);
		
		// Create movie media and set Media details AND CheckOutItem details
		CheckOutItem movieItem = new CheckOutItem();
		movieItem.setType(Media.Item.Movie);		// Also verify that CheckOutItem can inherit methods from Media
		int movieItemID = 71;
		movieItem.setID(movieItemID);
		movieItem.setAccount(acc);
		movieItem.setIsCheckedOut(true);
		movieItem.setIsReserved(false);
		int borrowingPeriod = 21;
		movieItem.setBorrowingPeriod(borrowingPeriod);
		
		mediaRepository.save(movieItem); 	// Save it in Media repository
		
		// Persistence testing
		movieItem = null;
		movieItem = mediaRepository.findMediaByID(movieItemID);
		
		assertNotNull(movieItem);			// Check movieItem was saved in mediaRepository
		assertEquals(true, movieItem.getIsCheckedOut());
		assertEquals(false, movieItem.getIsReserved());
		assertEquals(borrowingPeriod, movieItem.getBorrowingPeriod());
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		// Set up test account to perform operations on Event
		Account acc = new Online();
		acc.setId(18);
		accountRepository.save(acc);
		
		// Create Event and set its details
		Event testEvent = new Event();
		testEvent.setAccount(acc);
		String testEventName = "Event 1";
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 31));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 00));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(12, 00));
		testEvent.setName(testEventName);
		testEvent.setDate(date);
		testEvent.setEventStart(startTime);
		testEvent.setEventEnd(endTime);
		
		eventRepository.save(testEvent);	// Save it in Event repository
		
		// Persistence testing: findEventByName (Event)
		
		testEvent = null;
		testEvent = eventRepository.findEventByName(testEventName);
		
		assertNotNull(testEvent);
		assertEquals(date, testEvent.getDate());
		assertEquals(startTime, testEvent.getEventStart());
		assertEquals(endTime, testEvent.getEventEnd());
		
		// Persistence testing: findByAccount (List<Event>)
		List<Event> listEvent = null;
		listEvent = eventRepository.findByAccount(acc); 
		assertNotNull(listEvent);
		
		for (Event testEvent1: listEvent) {
			assertNotNull(testEvent1);
			assertEquals(date, testEvent1.getDate());
			assertEquals(startTime, testEvent1.getEventStart());
			assertEquals(endTime, testEvent1.getEventEnd());
		}
	}
}
