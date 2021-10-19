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
		// String ename = "BTSConcert";
		// // int eventId = 7;
		// e.setName(ename);
		// events.add(e);

		Media media = new NonCheckOutItem();
		media.setType(Media.Item.Book);
		int mediaId = 7;
		media.setID(mediaId);
		media.setAccount(acc);

		Set<Media> medias = new HashSet<Media>();
       	medias.add(media);
		// mediaRepository.save(media);


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
		// Set<Media> mtest = acc.getMedias();
		// int mtestsize = mtest.size();
		// int mediasize = ((Set<Media>) media).size();
		// assertEquals(mediasize, mtestsize);
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
	public void testPersistAndLoadEvent(){

	}
	
	@Test
	public void testPersistAndLoadLibrarian() {
		Librarian librarian = new Librarian();
		Set<Shift> shifts = new HashSet<Shift>();
		Shift shift = new Shift();
		shift.setShiftID(7);
		shifts.add(shift);
		int id = 2;
/*		String address = "mars";
		AccountCategory offline = Account.AccountCategory.Offline;
		boolean local = true;
		String name = "Marius";
		
				
		librarian.setAddress(address);
		librarian.setName(name);
		librarian.setAccountCategory(offline);
		librarian.setIsLocal(local);*/
		librarian.setShift(shifts);
		librarian.setId(id);
		
		accountRepository.save(librarian);
		
		librarian = null;
		
		librarian = (Librarian) accountRepository.findAccountById(id);
		assertNotNull(librarian);
		assertEquals(id, librarian.getId());
		assertEquals(shifts, librarian.getShift());
//		assertEquals(shifts, librarian.getShift());
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		Account headLibrarian = new HeadLibrarian();
		Set<Shift> shifts = new HashSet<Shift>();
		Shift shift = new Shift();
		shifts.add(shift);
		shift.setShiftID(9526);
		headLibrarian.setAddress("earth");
		headLibrarian.setId(24602);
		headLibrarian.setName("cosset");
	//	headLibrarian.setShift(shifts);
		
		accountRepository.save(headLibrarian);
		
		headLibrarian = null;
		
		headLibrarian = (HeadLibrarian) accountRepository.findAccountById(24702);
		assertNotNull(headLibrarian);
		assertEquals("cosset", headLibrarian.getName());
	}
	
	@Test
	public void testPersistAndLoadShift() {
		HeadLibrarian headLibrarian = new HeadLibrarian();
		Librarian librarian = new Librarian();
		Set<Librarian> librarians = new HashSet<Librarian>();
		librarian.setName("pto");
		librarians.add(librarian);
		Set<Shift> shifts = new HashSet<Shift>();
		Shift shift = new Shift();
		shift.setShiftID(89757);
		shifts.add(shift);
		String address = "Atlantis";
		int id = 10086;
		String name = "aquaman";
		Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.JANUARY, 3));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8,05));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(18,05));
				
		
		headLibrarian.setAddress(address);
		headLibrarian.setId(id);
		headLibrarian.setName(name);
		headLibrarian.setShift(shifts);
		shift.setDate(date);
		shift.setHeadLibrarian(headLibrarian);
		shift.setStartTime(startTime);
		shift.setEndTime(endTime);
		shift.setLibrarian(librarians);
		
		shiftRepository.save(shift);
		
		shift = null;
		
		shift = shiftRepository.findShiftByShiftID(id);
		assertNotNull(shift);
		assertEquals(headLibrarian, shift.getHeadLibrarian());
		assertEquals(librarians, shift.getLibrarian());
		assertEquals(startTime, shift.getStartTime());
		assertEquals(endTime, shift.getEndTime());
		assertEquals(id, shift.getShiftID());
	}
}
