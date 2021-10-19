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
