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
}
