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
}
