package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.models.*;

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
	public void testPersistAndLoadMedia() {
//		Media media = new CheckOutItem();
//		Account acc = new Online();
//		acc.setId(69);
//		accountRepository.save(acc);
//		media.setType(Media.Item.Book);
//		int mediaId = 727;
//		media.setID(mediaId);
//		media.setAccount(acc);
//		mediaRepository.save(media);
//		media = null;
//		media = mediaRepository.findMediaByID(mediaId);
//		assertNotNull(media);
//		assertEquals(mediaId, media.getID());
//		assertEquals(Media.Item.Book, media.getType());
		
//		Account acc = new Online();
//		acc.setId(69);
//		acc.setAccountCategory(Account.AccountCategory.Online);
//		acc.setAddress("123street");
//		acc.setEvents(null);
//		acc.setIsLocal(true);
//		acc.setMedias(null);
//		acc.setName("andy");
//		acc.setNumChecked(727);
//		accountRepository.save(acc);
//		Event event = new Event();
//		String name = "event1";
//		event.setName(name);
//		event.setAccount(acc);
//		eventRepository.save(event);
//		event = null;
//		event = eventRepository.findEventByName(name);
//		assertNotNull(event);
//		assertEquals(name, event.getName());
		
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
		movieItem = mediaRepository.findMediaById(movieItemID);
		
		assertNotNull(movieItem);			// Check movieItem was saved in mediaRepository
		assertEquals(true, getIsCheckedOut());
		assertEquals(false, getIsReserved());
		assertEquals(borrowingPeriod, getBorrowingPeriod());
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
		testEvent = null;
		List<Event> listEvent = eventRepository.findByAccount(acc.getId()); 
		assertNotNull(listEvent);
		
		for (Event testEvent: listEvent) {
			assertNotNull(testEvent);
			assertEquals(date, testEvent.getDate());
			assertEquals(startTime, testEvent.getEventStart());
			assertEquals(endTime, testEvent.getEventEnd());
		}
	}
}
