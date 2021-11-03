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

		eventRepository.deleteAll();
		shiftRepository.deleteAll();
		openingHourRepository.deleteAll();
		mediaRepository.deleteAll();
		accountRepository.deleteAll();
		
	}
	@Test
	public void testPersistAndLoadCheckOutItem(){
		Media something = new CheckOutItem();
		int DN = 123;
		something.setType(Media.Item.Archive);
		something.setID(DN);
		mediaRepository.save(something);
		something = null;
		something = mediaRepository.findMediaByID(DN);
		assertEquals(DN, something.getID());
		assertEquals(Media.Item.Archive, something.getType());
	}
	@Test
	public void testPersistAndLoadNonCheckOutItem(){

		Media something = new NonCheckOutItem();
		int DN = 123;
		something.setType(Media.Item.Book);
		something.setID(DN);

		mediaRepository.save(something);
		something = null;
		something = mediaRepository.findMediaByID(DN);

		assertEquals(DN, something.getID());
		assertEquals(Media.Item.Book, something.getType());

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
		int id=7;
		
		Online online=new Online();
		online.setUsername(username);
		online.setPassword(password);
		online.setEmail(email);
		online.setId(id);
		
		accountRepository.save((Account)online);
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
		Event something = new Event();
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
		accountRepository.save(acc);

		String DN = "helpme";
		Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.JANUARY, 3));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8,05));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(18,05));

		something.setAccount(acc);
		something.setEventStart(startTime);
		something.setEventEnd(endTime);
		something.setDate(date);
		something.setName(DN);

		eventRepository.save(something);
		something=null;
		something=eventRepository.findEventByName(DN);

		assertEquals(startTime, something.getEventStart());
		assertEquals(endTime, something.getEventEnd());
		assertEquals(id, something.getAccount().getId());
		assertEquals(DN, something.getName());
		assertEquals(date, something.getDate());
	}
	
	@Test
	public void testPersistAndLoadLibrarian() {
		Account lib = new Librarian();
		String address = "earth";
		AccountCategory off = Account.AccountCategory.Offline;
		int id = 666;
		boolean local = true;
		String name = "batman";
		int numChecked = 6;

		lib.setAccountCategory(off);
		lib.setAddress(address);
		lib.setId(id);
		lib.setIsLocal(local);
		lib.setName(name);
		lib.setNumChecked(numChecked);


		accountRepository.save(lib);
		lib = null;
		lib = accountRepository.findAccountById(id);

		assertNotNull(lib);
		assertEquals(off, lib.getAccountCategory());
		assertEquals(address, lib.getAddress());
		assertEquals(id, lib.getId());
		assertEquals(local, lib.getIsLocal());
		assertEquals(name, lib.getName());
		assertEquals(numChecked, lib.getNumChecked());
<<<<<<< HEAD

		
=======
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		Account lib = new HeadLibrarian();
		String address = "earth";
		AccountCategory off = Account.AccountCategory.Offline;
		int id = 666;
		boolean local = true;
		String name = "batman";
		int numChecked = 6;

		lib.setAccountCategory(off);
		lib.setAddress(address);
		lib.setId(id);
		lib.setIsLocal(local);
		lib.setName(name);
		lib.setNumChecked(numChecked);


		accountRepository.save(lib);
		lib = null;
		lib = accountRepository.findAccountById(id);

		assertNotNull(lib);
		assertEquals(off, lib.getAccountCategory());
		assertEquals(address, lib.getAddress());
		assertEquals(id, lib.getId());
		assertEquals(local, lib.getIsLocal());
		assertEquals(name, lib.getName());
		assertEquals(numChecked, lib.getNumChecked());
>>>>>>> 1327d7cfb9d6c3a050b94d02e44d1dbcfbe5654c
	}
	
	@Test
	public void testPersistAndLoadShift() {
<<<<<<< HEAD
		
		Shift shift = new Shift();
		int id = 89757;
		String d = "2021-12-25";
		String st = "15:55";
		String et = "8:07";
		Date date = Date.valueOf(d);
		Time startTime = Time.valueOf(st);
		Time endTime = Time.valueOf(et);
		
		shift.setShiftID(id);
		shift.setStartTime(startTime);
		shift.setEndTime(endTime);
		shift.setDate(date);
		
=======
		HeadLibrarian headLibrarian = new HeadLibrarian();
		int id1 = 2;
		String address = "mars";
		AccountCategory offline = Account.AccountCategory.Offline;
		boolean local = true;
		String name = "Marius";
				
		headLibrarian.setAddress(address);
		headLibrarian.setName(name);
		headLibrarian.setAccountCategory(offline);
		headLibrarian.setIsLocal(local);
		headLibrarian.setId(id1);
		
		accountRepository.save(headLibrarian);

		Shift shift = new Shift();
		int id = 10086;
		Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.JANUARY, 3));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8,05));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(18,05));
		
		shift.setShiftID(id);
		shift.setHeadLibrarian(headLibrarian);
		shift.setDate(date);
		shift.setStartTime(startTime);
		shift.setEndTime(endTime);
>>>>>>> 1327d7cfb9d6c3a050b94d02e44d1dbcfbe5654c
		shiftRepository.save(shift);
		shift=null;
		shift = shiftRepository.findShiftByShiftID(id);
<<<<<<< HEAD
		
		assertNotNull(shift);
		assertEquals(id, shift.getShiftID());
		assertEquals(date, shift.getDate());
=======
		assertEquals(date,shift.getDate());
		assertEquals(id1, shift.getHeadLibrarian().getId());
>>>>>>> 1327d7cfb9d6c3a050b94d02e44d1dbcfbe5654c
		assertEquals(startTime, shift.getStartTime());
		assertEquals(endTime, shift.getEndTime());
		
		
	}

	@Test
	public void testPersistAndLoadOpeningHour(){
		Account lib = new HeadLibrarian();
		String address = "earth";
		AccountCategory off = Account.AccountCategory.Offline;
		int id1 = 666;
		boolean local = true;
		String name = "batman";
		int numChecked = 6;

		lib.setAccountCategory(off);
		lib.setAddress(address);
		lib.setId(id1);
		lib.setIsLocal(local);
		lib.setName(name);
		lib.setNumChecked(numChecked);


		accountRepository.save(lib);

		int id = 7;
		Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.JANUARY, 3));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8,05));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(18,05));
		OpeningHour oh = new OpeningHour();
		oh.setHeadLibrarian((HeadLibrarian)lib);
		oh.setId(id);
		oh.setDate(date);
		oh.setStartTime(startTime);
		oh.setEndTime(endTime);
		openingHourRepository.save(oh);
		oh = null;
		oh = openingHourRepository.findOpeningHourById(id);
		assertNotNull(oh);
		assertEquals(lib.getId(),oh.getHeadLibrarian().getId());
		assertEquals(id, oh.getId());
		assertEquals(date, oh.getDate());
		assertEquals(startTime, oh.getStartTime());
		assertEquals(endTime, oh.getEndTime());
	}

}
