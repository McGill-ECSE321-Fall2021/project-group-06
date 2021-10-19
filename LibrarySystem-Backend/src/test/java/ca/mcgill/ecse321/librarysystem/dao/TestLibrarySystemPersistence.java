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
		
		
		
		// Then we can clear the other tables
		accountRepository.deleteAll();
		shiftRepository.deleteAll();
		mediaRepository.deleteAll();
		openingHourRepository.deleteAll();
		eventRepository.deleteAll();
		
	}
	
	@Test
	public void testPersistAndLoadOffline() {
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
		Account acc = new Offline();
		//eventRepository.save(event);
		acc.setAccountCategory(Account.AccountCategory.Offline);
		acc.setAddress("ezstt");
		acc.setId(727);
		acc.setIsLocal(true);
		acc.setName("Bobster");
		acc.setNumChecked(747);
		accountRepository.save(acc);
		acc = null;
		acc = accountRepository.findAccountById(727);
		assertNotNull(acc);
		assertEquals("Bobster", acc.getName());

		
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
	public void testPersistAndLoadLibrarian() {
		Librarian librarian = new Librarian();
		Shift shift = new Shift();
		shift.setShiftID(9527);
		librarian.setAddress("mars");
		librarian.setId(24601);
		librarian.setName("marius");
		librarian.setShift(shift);
		
		accountRepository.save(librarian);
		
		librarian = null;
		
		librarian = (Librarian) accountRepository.findAccountById(24701);
		assertNotNull(librarian);
		assertEquals("marius", librarian.getName());
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		HeadLibrarian headLibrarian = new HeadLibrarian();
		Shift shift = new Shift();
		shift.setShiftID(9526);
		headLibrarian.setAddress("earth");
		headLibrarian.setId(24602);
		headLibrarian.setName("cosset");
		headLibrarian.setShift(shift);
		
		accountRepository.save(headLibrarian);
		
		headLibrarian = null;
		
		headLibrarian = (HeadLibrarian) accountRepository.findAccountById(24702);
		assertNotNull(headLibrarian);
		assertEquals("cosset", headLibrarian.getName());
	}
	
	@Test
	public void testPersistAndLoadShift() {
		HeadLibrarian headLibrarian = new HeadLibrarian();
		Shift shift = new Shift();
		shift.setShiftID(89757);
		headLibrarian.setAddress("atlantis");
		headLibrarian.setId(10086);
		headLibrarian.setName("aquaman");
		headLibrarian.setShift(shift);
		shift.setDate(java.sql.Date.valueOf(LocalDate.of(2022, Month.JANUARY, 3)));
		shift.setHeadLibrarian(headLibrarian);
		shift.setStartTime(java.sql.Time.valueOf(LocalTime.of(8,05)));
		shift.setEndTime(java.sql.Time.valueOf(LocalTime.of(18, 35)));
		
		shiftRepository.save(shift);
		
		shift = null;
		
		shift = shiftRepository.findShiftById(89757);
		assertNotNull(shift);
		assertEquals(headLibrarian, shift.getHeadLibrarian());
	}
	
}
